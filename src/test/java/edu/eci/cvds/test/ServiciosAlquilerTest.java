package edu.eci.cvds.test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {
    @Inject
    private SqlSession sqlSession;
    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    private long idCliente;
    private long tarifa;
    private int idItem;
    private int numDias;
    private boolean vetado;


    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        for(int i = 1; i < 100; i += 10) {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(6869 + i);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        }
    }


    @Test
    public void consultandoUnCliente() throws ExcepcionServiciosAlquiler {
        try {
            Cliente cliente = serviciosAlquiler.consultarCliente(2160666);
            Assert.assertEquals("Javier López",cliente.getNombre());
        } catch(ExcepcionServiciosAlquiler e) {
            throw new ExcepcionServiciosAlquiler("Error Prueba Consultar Cliente.",e);
        }
    }

    @Test
    public void itemNoFound(){
        boolean found = false;
        try{
            Item item = serviciosAlquiler.consultarItem(526341);
        }
        catch (ExcepcionServiciosAlquiler e){
            found = true;
        }
        catch (IndexOutOfBoundsException e){
            found = true;
        }
        finally{
            Assert.assertTrue(found);
        }
    }

    @Test
    public void insertarItemRentado() throws ExcepcionServiciosAlquiler {
        boolean insert = true;
        try{
            Item item = serviciosAlquiler.consultarItem(102);
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf("2019-02-01"),999,item,5);
        }
        catch(ExcepcionServiciosAlquiler e){
            insert = false;
        }
        finally{
            Assert.assertTrue(insert);
        }
    }

    @Test
    public void consultarMultaAlquilerValido() throws ExcepcionServiciosAlquiler {
        int diasRetraso = 5;
        idItem = 4;
        idCliente = 1004823074;
        Date fechaFinRentaItem = serviciosAlquiler.consultarItemsRentados(idItem,idCliente).getFechafinrenta();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime( fechaFinRentaItem );
        calendar.add( Calendar.DAY_OF_YEAR,diasRetraso);

        java.sql.Date entrega = new java.sql.Date(calendar.getTime().getTime());
        Assert.assertEquals( serviciosAlquiler.consultarMultaAlquiler(idItem,entrega ,idCliente),serviciosAlquiler.valorMultaRetrasoxDia(idItem) * diasRetraso );
    }

    @Test
    public void consultarMultaAlquilerValidoIgualaCero() throws ExcepcionServiciosAlquiler {
        int diasRetraso = 0;
        idItem = 4;
        idCliente = 1004823074;
        Date fechaFinRentaItem = serviciosAlquiler.consultarItemsRentados(idItem,idCliente).getFechafinrenta();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( fechaFinRentaItem );
        calendar.add( Calendar.DAY_OF_YEAR,diasRetraso);

        java.sql.Date entrega = new java.sql.Date(calendar.getTime().getTime());
        Assert.assertEquals( serviciosAlquiler.consultarMultaAlquiler(idItem,entrega ,idCliente),0 );
    }
    @Test
    public void consultarMultaAlquilerValidoExcepcionItem() {
        int diasRetraso = 0;
        idItem = -100;
        idCliente = 1004823074;
        try {
            Date fechaFinRentaItem = serviciosAlquiler.consultarItemsRentados(idItem,idCliente).getFechafinrenta();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( fechaFinRentaItem );
            calendar.add( Calendar.DAY_OF_YEAR,diasRetraso);

            java.sql.Date entrega = new java.sql.Date(calendar.getTime().getTime());
            serviciosAlquiler.consultarMultaAlquiler(idItem,entrega ,idCliente);
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals( excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.NO_ITEM);
        }
    }
    @Test
    public void consultarMultaAlquilerValidoExcepcionItemNoRentado() {
        int diasRetraso = 0;
        idItem = 1001169369;
        idCliente = 1004823074;
        try {
            Date fechaFinRentaItem = serviciosAlquiler.consultarItemsRentados(idItem,idCliente).getFechafinrenta();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( fechaFinRentaItem );
            calendar.add( Calendar.DAY_OF_YEAR,diasRetraso);

            java.sql.Date entrega = new java.sql.Date(calendar.getTime().getTime());
            serviciosAlquiler.consultarMultaAlquiler(idItem,entrega,idCliente );
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals( excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.NO_ALQUILERITEM+idItem);
        }
    }

    @Test
    public void consultarMultaAlquilerValidoExcepcionFechaIncorrecta() {
        int diasRetraso = -1000;
        idItem = 4;
        idCliente = 1004823074;
        try {
            Date fechaFinRentaItem = serviciosAlquiler.consultarItemsRentados(idItem,idCliente).getFechafinrenta();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( fechaFinRentaItem );
            calendar.add( Calendar.DAY_OF_YEAR,diasRetraso);

            java.sql.Date entrega = new java.sql.Date(calendar.getTime().getTime());
            serviciosAlquiler.consultarMultaAlquiler(idItem,entrega,idCliente );
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals( excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.FECHA_LIMITE_INVALIDA);
        }
    }


    @Test
    public void consultarCostoAlquilerValido() throws ExcepcionServiciosAlquiler{
        long answer = -1;
        idItem = 2;
        numDias = 20;
        answer = serviciosAlquiler.consultarCostoAlquiler(idItem, numDias );
        Assert.assertEquals( answer ,serviciosAlquiler.consultarItem(idItem).getTarifaxDia() * numDias );
    }

    @Test
    public void consultarCostoAlquilExcepcionDia(){
        long answer = -1;
        idItem = 2;
        numDias = -20;
        try {
            answer = serviciosAlquiler.consultarCostoAlquiler(idItem, numDias );
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(answer== -1);
            Assert.assertEquals(ExcepcionServiciosAlquiler.DIAS_INVALIDOS,excepcionServiciosAlquiler.getMessage());
        }
    }

    @Test
    public void consultarCostoAlquilExcepcionItem(){
        long answer = -1;
        idItem = -2;
        numDias = 20;
        try {
            answer = serviciosAlquiler.consultarCostoAlquiler(idItem, numDias );
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(answer== -1);
            Assert.assertEquals(ExcepcionServiciosAlquiler.NO_ITEM,excepcionServiciosAlquiler.getMessage());
        }
    }


    @Test
    public void actualizarTarifaItemValido() throws ExcepcionServiciosAlquiler {
        tarifa = 30000;
        idItem = 2;

        long currentTarifa;
        serviciosAlquiler.actualizarTarifaItem( idItem, tarifa);
        currentTarifa = serviciosAlquiler.consultarItem(idItem).getTarifaxDia();
        Assert.assertEquals( tarifa,currentTarifa );
    }

    @Test
    public void actualizarTarifaItemExcepcionItem(){
        tarifa = 30000;
        idItem = -1100;
        try {
            serviciosAlquiler.actualizarTarifaItem( idItem, tarifa);
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals( excepcionServiciosAlquiler.getMessage(),ExcepcionServiciosAlquiler.NO_ITEM);
        }


    }
    @Test
    public void actualizarTarifaItemExcepcionTarifa(){
        tarifa = -151515;
        idItem = 2;
        try {
            serviciosAlquiler.actualizarTarifaItem( idItem, tarifa);
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals( excepcionServiciosAlquiler.getMessage(),ExcepcionServiciosAlquiler.TARIFA_INVALIDA);
        }
    }



    @Test
    public void vetarClienteValido(){
        Cliente cliente = null;
        vetado = true;
        idCliente = 3146879;
        try {
            serviciosAlquiler.vetarCliente(idCliente, vetado );
            cliente = serviciosAlquiler.consultarCliente(idCliente);
            Assert.assertEquals(cliente.isVetado(),vetado );
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
        }
    }


    @Test
    public void vetarClienteExcepcionCliente(){
        vetado = true;
        idCliente = 3146879;
        String error = "Error al vetar al cliente con id: "+idCliente;
        try {
            serviciosAlquiler.vetarCliente(idCliente, vetado );
            Assert.assertFalse(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals(error,excepcionServiciosAlquiler.getMessage());
        }
    }

    @Test
    public void registrarAlquilerClienteValido() throws ExcepcionServiciosAlquiler{
        Date fechaInicioRenta;
        Date fechaFinRenta;
        int size;
        numDias = 5;

        java.sql.Date fechaInicio = java.sql.Date.valueOf("2020-10-07");
        Item item = serviciosAlquiler.consultarItem(1);

        serviciosAlquiler.registrarAlquilerCliente(fechaInicio,1,item,numDias);
        Cliente cliente = serviciosAlquiler.consultarCliente(1);
        size = cliente.getRentados().size() -1 ;

        fechaInicioRenta = cliente.getRentados().get( size ).getFechainiciorenta();
        fechaFinRenta = cliente.getRentados().get( size ).getFechafinrenta();
        java.sql.Date fechaFin = java.sql.Date.valueOf("2020-10-12");

        Assert.assertEquals( fechaInicioRenta, fechaInicio);
        Assert.assertEquals( fechaFinRenta, fechaFin);
    }
    @Test
    public void registrarAlquilerClienteExcepcionCliente() {
        numDias = 5;
        java.sql.Date fechaInicio = java.sql.Date.valueOf("2020-10-07");
        try {
            Item item = serviciosAlquiler.consultarItem(1);
            serviciosAlquiler.registrarAlquilerCliente(fechaInicio,-1564898189,item,numDias);
            Assert.assertTrue( false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals(excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.NO_CLIENTE);
        }
    }
    @Test
    public void registrarAlquilerClienteExcepcionItem() {
        numDias = 5;
        java.sql.Date fechaInicio = java.sql.Date.valueOf("2020-10-07");
        try {
            Item item = new Item();
            item.setId(-10000);
            serviciosAlquiler.registrarAlquilerCliente(fechaInicio,1,item,numDias);
            Assert.assertTrue( false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals(excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.NO_ITEM);
        }
    }
    @Test
    public void registrarAlquilerClienteExcepcionDias() {
        numDias = -1;
        java.sql.Date fechaInicio = java.sql.Date.valueOf("2020-10-07");
        try {
            Item item = serviciosAlquiler.consultarItem(1);
            serviciosAlquiler.registrarAlquilerCliente(fechaInicio,1,item,numDias);
            Assert.assertTrue( false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertEquals(excepcionServiciosAlquiler.getMessage(), ExcepcionServiciosAlquiler.DIAS_INVALIDOS);
        }
    }

    @Test
    public void consultarItemValido(){
        boolean valid = true;

        try{
            serviciosAlquiler.consultarItem(7);
        }
        catch (ExcepcionServiciosAlquiler ex){
            valid = false;
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void consultarItemNoValido(){
        boolean valid = false;
        try{
            Item pru = serviciosAlquiler.consultarItem(69);
        }
        catch(ExcepcionServiciosAlquiler ex){
            valid = true;
        }
        Assert.assertTrue(valid);
    }

    @Test
    public void consultarItemsClienteValido(){
        boolean empty = true;
        try {
            List<ItemRentado> pru = serviciosAlquiler.consultarItemsCliente(2160666);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            empty = false;
        }
        Assert.assertTrue(empty);
    }
    @Test
    public void consultarItemsClienteNoValido() {
        boolean empty = false;
        try{
            List<ItemRentado> pru = serviciosAlquiler.consultarItemsCliente(6969);
        }catch(ExcepcionServiciosAlquiler ex){
            empty = true;
        }
        Assert.assertTrue(empty);
    }

    @Test
    public void consultarTipoItemValido(){
        boolean emp = true;
        try {
            serviciosAlquiler.consultarTipoItem(1);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            emp = false;
        }
        Assert.assertTrue(emp);
    }
    @Test
    public void consultarTipoItemNoValido(){
        boolean emp = false;
        try {
            serviciosAlquiler.consultarTipoItem(65);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            emp = true;
        }
        Assert.assertTrue(emp);
    }

    @Test
    public void registrarCliente(){
        Cliente r = new Cliente("Prueba",2160176,"Prueba","Prueba","Prueba");
        try {
            serviciosAlquiler.registrarCliente(r);
            Assert.assertTrue(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {

        }
    }

    @Test
    public void registrarClienteFail(){
        Cliente r = new Cliente("Prueba",2160176,"Prueba","Prueba","Prueba");
        try {
            serviciosAlquiler.registrarCliente(r);
            Assert.assertTrue(false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {

            Assert.assertTrue(true);
        }
    }

    @Test
    public void registrarItem(){
        try {
            Item r = new Item(serviciosAlquiler.consultarTipoItem(1),69,"Test","ShouldPass", Date.valueOf("2020-10-07"),15000,"ShouldPass","PruebaInsertar");
            Assert.assertTrue(true);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void registrarItemFail(){
        try {
            Item r = new Item(serviciosAlquiler.consultarTipoItem(-89),-69,"Test","ShouldPass", Date.valueOf("2020-10-07"),15000,"ShouldPass","PruebaInsertar");
            Assert.assertTrue(false);
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            Assert.assertTrue(true);
        }
    }
}