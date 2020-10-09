# CVDS-LAB08
Laboratorio 8 - MyBatis-Guice-PrimeFaces - 2020-2
<h1 class="title">Laboratorio</h1>

<h3 id="escuela-colombiana-de-ingeniería">Escuela Colombiana de Ingeniería</h3>
<h3 id="procesos-de-desarrollo-de-software---pdsw">Ciclos de Vida del Desarrollo de Software - CVDS</h3>
<h4 id="integración-de-capas-con-google-guice">Integración de Capas con Google Guice</h4>
<p><img src="https://github.com/Rincon10/CVDS-LAB08/blob/master/img/modeloBasesDeDatos.png" width="739" height="567"></p>
<h1><img src="https://github.com/Rincon10/CVDS-LAB08/blob/master/img/modeloPOO.png" width="837" height="720"></h1>
<h1 id="parte-i.-inicio-en-clase">Parte I. Inicio en clase</h1>
<ol type="1">
<li>
<p>Actualice el proyecto del taller realizados en el ejercicio anterior. Agregue las clases y excepciones no descritas de ser necesario.</p>
</li>
<li>
<p>Cree la interface <code>ItemDAO</code> y utilicela como referencia para realizar los <em>Objetos de Acceso a los Datos</em> (DAO) para las demas entidades:</p>
<div class="sourceCode" id="cb1">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb1-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.sampleprj.dao;</span>
<a class="sourceLine" id="cb1-2" data-line-number="2"></a>
<a class="sourceLine" id="cb1-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Item;</span>
<a class="sourceLine" id="cb1-4" data-line-number="4"></a>
<a class="sourceLine" id="cb1-5" data-line-number="5"></a><span class="kw">public</span> <span class="kw">interface</span> ItemDAO {
<a class="sourceLine" id="cb1-6" data-line-number="6"></a>
<a class="sourceLine" id="cb1-7" data-line-number="7"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">save</span>(Item it) <span class="kw">throws</span> PersistenceException;
<a class="sourceLine" id="cb1-8" data-line-number="8"></a>
<a class="sourceLine" id="cb1-9" data-line-number="9"></a>   <span class="kw">public</span> Item <span class="fu">load</span>(<span class="dt">int</span> id) <span class="kw">throws</span> PersistenceException;
<a class="sourceLine" id="cb1-10" data-line-number="10"></a>
<a class="sourceLine" id="cb1-11" data-line-number="11"></a>}</code></pre>
</div>
</li>
<li>
<p>Usando como referencia la implementación de <code>ItemDAO</code>: <code>MyBATISItemDao</code>, cree el DAO MyBATIS de las demas entidades:</p>
<div class="sourceCode" id="cb2">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb2-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis;</span>
<a class="sourceLine" id="cb2-2" data-line-number="2"></a>
<a class="sourceLine" id="cb2-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> com.google.inject.Inject;</span>
<a class="sourceLine" id="cb2-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> com.google.inject.Singleton;</span>
<a class="sourceLine" id="cb2-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.ItemDAO;</span>
<a class="sourceLine" id="cb2-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.PersistenceException;</span>
<a class="sourceLine" id="cb2-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;</span>
<a class="sourceLine" id="cb2-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Item;</span>
<a class="sourceLine" id="cb2-9" data-line-number="9"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;</span>
<a class="sourceLine" id="cb2-10" data-line-number="10"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.TipoItem;</span>
<a class="sourceLine" id="cb2-11" data-line-number="11"></a><span class="kw">import</span><span class="im"> java.sql.SQLException;</span>
<a class="sourceLine" id="cb2-12" data-line-number="12"></a>
<a class="sourceLine" id="cb2-13" data-line-number="13"></a><span class="kw">public</span> <span class="kw">class</span> MyBATISItemDAO <span class="kw">implements</span> ItemDAO{
<a class="sourceLine" id="cb2-14" data-line-number="14"></a>
<a class="sourceLine" id="cb2-15" data-line-number="15"></a>  <span class="at">@Inject</span>
<a class="sourceLine" id="cb2-16" data-line-number="16"></a>  <span class="kw">private</span> ItemMapper itemMapper;    
<a class="sourceLine" id="cb2-17" data-line-number="17"></a>
<a class="sourceLine" id="cb2-18" data-line-number="18"></a>  <span class="at">@Override</span>
<a class="sourceLine" id="cb2-19" data-line-number="19"></a>  <span class="kw">public</span> <span class="dt">void</span> <span class="fu">save</span>(Item it) <span class="kw">throws</span> PersistenceException{
<a class="sourceLine" id="cb2-20" data-line-number="20"></a>  <span class="kw">try</span>{
<a class="sourceLine" id="cb2-21" data-line-number="21"></a>      itemMapper.<span class="fu">insertarItem</span>(it);
<a class="sourceLine" id="cb2-22" data-line-number="22"></a>  }
<a class="sourceLine" id="cb2-23" data-line-number="23"></a>  <span class="kw">catch</span>(org.<span class="fu">apache</span>.<span class="fu">ibatis</span>.<span class="fu">exceptions</span>.<span class="fu">PersistenceException</span> e){
<a class="sourceLine" id="cb2-24" data-line-number="24"></a>      <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">PersistenceException</span>(<span class="st">"Error al registrar el item "</span>+it.<span class="fu">toString</span>(),e);
<a class="sourceLine" id="cb2-25" data-line-number="25"></a>  }        
<a class="sourceLine" id="cb2-26" data-line-number="26"></a>
<a class="sourceLine" id="cb2-27" data-line-number="27"></a>  }
<a class="sourceLine" id="cb2-28" data-line-number="28"></a>
<a class="sourceLine" id="cb2-29" data-line-number="29"></a>  <span class="at">@Override</span>
<a class="sourceLine" id="cb2-30" data-line-number="30"></a>  <span class="kw">public</span> Item <span class="fu">load</span>(<span class="dt">int</span> id) <span class="kw">throws</span> PersistenceException {
<a class="sourceLine" id="cb2-31" data-line-number="31"></a>  <span class="kw">try</span>{
<a class="sourceLine" id="cb2-32" data-line-number="32"></a>      <span class="kw">return</span> itemMapper.<span class="fu">consultarItem</span>(id);
<a class="sourceLine" id="cb2-33" data-line-number="33"></a>  }
<a class="sourceLine" id="cb2-34" data-line-number="34"></a>  <span class="kw">catch</span>(org.<span class="fu">apache</span>.<span class="fu">ibatis</span>.<span class="fu">exceptions</span>.<span class="fu">PersistenceException</span> e){
<a class="sourceLine" id="cb2-35" data-line-number="35"></a>      <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">PersistenceException</span>(<span class="st">"Error al consultar el item "</span>+id,e);
<a class="sourceLine" id="cb2-36" data-line-number="36"></a>  }
<a class="sourceLine" id="cb2-37" data-line-number="37"></a>
<a class="sourceLine" id="cb2-38" data-line-number="38"></a>
<a class="sourceLine" id="cb2-39" data-line-number="39"></a>  }
<a class="sourceLine" id="cb2-40" data-line-number="40"></a>
<a class="sourceLine" id="cb2-41" data-line-number="41"></a>  }</code></pre>
</div>
</li>
<li>
<p>Cree la interface <code>ServiciosAlquiler</code> para los servicios de la capa lógica:</p>
<div class="sourceCode" id="cb3">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb3-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.samples.services;</span>
<a class="sourceLine" id="cb3-2" data-line-number="2"></a>
<a class="sourceLine" id="cb3-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Cliente;</span>
<a class="sourceLine" id="cb3-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Item;</span>
<a class="sourceLine" id="cb3-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.ItemRentado;</span>
<a class="sourceLine" id="cb3-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.TipoItem;</span>
<a class="sourceLine" id="cb3-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> java.sql.Date;</span>
<a class="sourceLine" id="cb3-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> java.util.List;</span>
<a class="sourceLine" id="cb3-9" data-line-number="9"></a>
<a class="sourceLine" id="cb3-10" data-line-number="10"></a><span class="kw">public</span> <span class="kw">interface</span> ServiciosAlquiler {
<a class="sourceLine" id="cb3-11" data-line-number="11"></a>
<a class="sourceLine" id="cb3-12" data-line-number="12"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">int</span> <span class="fu">valorMultaRetrasoxDia</span>(<span class="dt">int</span> itemId);
<a class="sourceLine" id="cb3-13" data-line-number="13"></a>
<a class="sourceLine" id="cb3-14" data-line-number="14"></a>   <span class="kw">public</span> <span class="kw">abstract</span> Cliente <span class="fu">consultarCliente</span>(<span class="dt">long</span> docu) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-15" data-line-number="15"></a>
<a class="sourceLine" id="cb3-16" data-line-number="16"></a>   <span class="co">/**</span>
<a class="sourceLine" id="cb3-17" data-line-number="17"></a>   <span class="co">*</span> <span class="co">@</span>obj Consultar los items que tenga en su poder un cliente
<a class="sourceLine" id="cb3-18" data-line-number="18"></a>   <span class="co">* </span><span class="kw">@param idcliente </span>identificador del cliente
<a class="sourceLine" id="cb3-19" data-line-number="19"></a>   <span class="co">*</span> <span class="kw">@return </span>el litado de detalle de los items rentados por el cliente
<a class="sourceLine" id="cb3-20" data-line-number="20"></a>   <span class="co">*</span> identificado con <span class="co">'</span>idcliente<span class="co">'</span>
<a class="sourceLine" id="cb3-21" data-line-number="21"></a>   <span class="co">*</span> <span class="kw">@throws ExcepcionServiciosAlquiler </span>si el cliente no esta registrado
<a class="sourceLine" id="cb3-22" data-line-number="22"></a>   <span class="co">*/</span>
<a class="sourceLine" id="cb3-23" data-line-number="23"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="bu">List</span>&lt;ItemRentado&gt; <span class="fu">consultarItemsCliente</span>(<span class="dt">long</span> idcliente) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-24" data-line-number="24"></a>
<a class="sourceLine" id="cb3-25" data-line-number="25"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="bu">List</span>&lt;Cliente&gt; <span class="fu">consultarClientes</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-26" data-line-number="26"></a>
<a class="sourceLine" id="cb3-27" data-line-number="27"></a>   <span class="kw">public</span> <span class="kw">abstract</span> Item <span class="fu">consultarItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-28" data-line-number="28"></a>
<a class="sourceLine" id="cb3-29" data-line-number="29"></a>   <span class="co">/**</span>
<a class="sourceLine" id="cb3-30" data-line-number="30"></a>   <span class="co">*</span> <span class="co">@</span>obj consultar los items que estan disponibles para alquiler
<a class="sourceLine" id="cb3-31" data-line-number="31"></a>   <span class="co">* </span><span class="kw">@return </span>el listado de items disponibles
<a class="sourceLine" id="cb3-32" data-line-number="32"></a>   <span class="co">*/</span>
<a class="sourceLine" id="cb3-33" data-line-number="33"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="bu">List</span>&lt;Item&gt; <span class="fu">consultarItemsDisponibles</span>();
<a class="sourceLine" id="cb3-34" data-line-number="34"></a>
<a class="sourceLine" id="cb3-35" data-line-number="35"></a>   <span class="co">/**</span>
<a class="sourceLine" id="cb3-36" data-line-number="36"></a>   <span class="co">*</span> <span class="co">@</span>obj consultar el valor de la multa del alquiler<span class="co">,</span> dado el id del item
<a class="sourceLine" id="cb3-37" data-line-number="37"></a>   <span class="co">*</span> alquilado hasta la fecha dada como parametro
<a class="sourceLine" id="cb3-38" data-line-number="38"></a>   <span class="co">* </span><span class="kw">@param iditem </span>el codigo del item alquilado
<a class="sourceLine" id="cb3-39" data-line-number="39"></a>   <span class="co">*</span> <span class="kw">@param fechaDevolucion </span>la fecha de devolucion del item
<a class="sourceLine" id="cb3-40" data-line-number="40"></a>   <span class="co">*</span> <span class="kw">@return </span>la multa en funcion del numero de dias de retraso<span class="co">.</span> Si el item se
<a class="sourceLine" id="cb3-41" data-line-number="41"></a>   <span class="co">*</span> entrega en la fecha exacta de entrega<span class="co">,</span> o antes<span class="co">,</span> la multa sera cero<span class="co">.</span>
<a class="sourceLine" id="cb3-42" data-line-number="42"></a>   <span class="co">*</span> <span class="kw">@throws ExcepcionServiciosAlquiler </span>si el item no existe o no esta
<a class="sourceLine" id="cb3-43" data-line-number="43"></a>   <span class="co">*</span> actualmente alquilado
<a class="sourceLine" id="cb3-44" data-line-number="44"></a>   <span class="co">*/</span>
<a class="sourceLine" id="cb3-45" data-line-number="45"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">long</span> <span class="fu">consultarMultaAlquiler</span>(<span class="dt">int</span> iditem, <span class="bu">Date</span> fechaDevolucion) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-46" data-line-number="46"></a>
<a class="sourceLine" id="cb3-47" data-line-number="47"></a>   <span class="kw">public</span> <span class="kw">abstract</span> TipoItem <span class="fu">consultarTipoItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-48" data-line-number="48"></a>
<a class="sourceLine" id="cb3-49" data-line-number="49"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="bu">List</span>&lt;TipoItem&gt; <span class="fu">consultarTiposItem</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-50" data-line-number="50"></a>
<a class="sourceLine" id="cb3-51" data-line-number="51"></a>   <span class="co">/**</span>
<a class="sourceLine" id="cb3-52" data-line-number="52"></a>   <span class="co">*</span> <span class="co">@</span>obj rejistrar el alkiler de un item
<a class="sourceLine" id="cb3-53" data-line-number="53"></a>   <span class="co">*</span> <span class="co">@</span>pre numdias <span class="co">&gt;=1</span>
<a class="sourceLine" id="cb3-54" data-line-number="54"></a>   <span class="co">* </span><span class="kw">@param date </span>fecha de rejistro del alquiler
<a class="sourceLine" id="cb3-55" data-line-number="55"></a>   <span class="co">*</span> <span class="kw">@param docu </span>identificacion de a quien se le cargara el alquiler
<a class="sourceLine" id="cb3-56" data-line-number="56"></a>   <span class="co">*</span> <span class="kw">@param item </span>el identificador del item a alquilar
<a class="sourceLine" id="cb3-57" data-line-number="57"></a>   <span class="co">*</span> <span class="kw">@param numdias </span>el numero de dias que se le prestara el item
<a class="sourceLine" id="cb3-58" data-line-number="58"></a>   <span class="co">*</span> <span class="co">@</span>pos el item ya no debe estar disponible<span class="co">,</span> y debe estar asignado al
<a class="sourceLine" id="cb3-59" data-line-number="59"></a>   <span class="co">*</span> cliente
<a class="sourceLine" id="cb3-60" data-line-number="60"></a>   <span class="co">*</span> <span class="kw">@throws ExcepcionXX </span>si el identificador no corresponde con un item<span class="co">,</span> o si
<a class="sourceLine" id="cb3-61" data-line-number="61"></a>   <span class="co">*</span> el mismo ya esta alquilado
<a class="sourceLine" id="cb3-62" data-line-number="62"></a>   <span class="co">*/</span>
<a class="sourceLine" id="cb3-63" data-line-number="63"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">void</span> <span class="fu">registrarAlquilerCliente</span>(<span class="bu">Date</span> date, <span class="dt">long</span> docu, Item item, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-64" data-line-number="64"></a>
<a class="sourceLine" id="cb3-65" data-line-number="65"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">void</span> <span class="fu">registrarCliente</span>(Cliente p) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-66" data-line-number="66"></a>
<a class="sourceLine" id="cb3-67" data-line-number="67"></a>   <span class="co">/**</span>
<a class="sourceLine" id="cb3-68" data-line-number="68"></a>   <span class="co">*</span> <span class="co">@</span>obj consultar el costo del alquiler de un item
<a class="sourceLine" id="cb3-69" data-line-number="69"></a>   <span class="co">*</span> <span class="co">@</span>pre numdias <span class="co">&gt;=1</span>
<a class="sourceLine" id="cb3-70" data-line-number="70"></a>   <span class="co">* </span><span class="kw">@param iditem </span>el codigo del item
<a class="sourceLine" id="cb3-71" data-line-number="71"></a>   <span class="co">*</span> <span class="kw">@param numdias </span>el numero de dias que se va a alquilar
<a class="sourceLine" id="cb3-72" data-line-number="72"></a>   <span class="co">*</span> <span class="kw">@return </span>el costo total del alquiler<span class="co">,</span> teniendo en cuesta el costo diario y
<a class="sourceLine" id="cb3-73" data-line-number="73"></a>   <span class="co">*</span> el numeo de dias del alquiler
<a class="sourceLine" id="cb3-74" data-line-number="74"></a>   <span class="co">*</span> <span class="kw">@throws ExcepcionServiciosAlquiler </span>si el identificador del item no existe
<a class="sourceLine" id="cb3-75" data-line-number="75"></a>   <span class="co">*/</span>
<a class="sourceLine" id="cb3-76" data-line-number="76"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">long</span> <span class="fu">consultarCostoAlquiler</span>(<span class="dt">int</span> iditem, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-77" data-line-number="77"></a>
<a class="sourceLine" id="cb3-78" data-line-number="78"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">void</span> <span class="fu">actualizarTarifaItem</span>(<span class="dt">int</span> id, <span class="dt">long</span> tarifa) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-79" data-line-number="79"></a>
<a class="sourceLine" id="cb3-80" data-line-number="80"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">void</span> <span class="fu">registrarItem</span>(Item i) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-81" data-line-number="81"></a>
<a class="sourceLine" id="cb3-82" data-line-number="82"></a>   <span class="kw">public</span> <span class="kw">abstract</span> <span class="dt">void</span> <span class="fu">vetarCliente</span>(<span class="dt">long</span> docu, <span class="dt">boolean</span> estado) <span class="kw">throws</span> ExcepcionServiciosAlquiler;
<a class="sourceLine" id="cb3-83" data-line-number="83"></a>
<a class="sourceLine" id="cb3-84" data-line-number="84"></a>}</code></pre>
</div>
</li>
<li>
<p>Realice en la implementación de la capa lógica (ServiciosAlquilerImpl), inyecte los DAO que sean necesarios. Tome de ejemplo el ItemDAO.</p>
<div class="sourceCode" id="cb4">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb4-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.samples.services.impl;</span>
<a class="sourceLine" id="cb4-2" data-line-number="2"></a>
<a class="sourceLine" id="cb4-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> com.google.inject.Inject;</span>
<a class="sourceLine" id="cb4-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> com.google.inject.Singleton;</span>
<a class="sourceLine" id="cb4-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.ClienteDAO;</span>
<a class="sourceLine" id="cb4-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.ItemDAO;</span>
<a class="sourceLine" id="cb4-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.PersistenceException;</span>
<a class="sourceLine" id="cb4-8" data-line-number="8"></a>
<a class="sourceLine" id="cb4-9" data-line-number="9"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Cliente;</span>
<a class="sourceLine" id="cb4-10" data-line-number="10"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Item;</span>
<a class="sourceLine" id="cb4-11" data-line-number="11"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.ItemRentado;</span>
<a class="sourceLine" id="cb4-12" data-line-number="12"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.TipoItem;</span>
<a class="sourceLine" id="cb4-13" data-line-number="13"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;</span>
<a class="sourceLine" id="cb4-14" data-line-number="14"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ServiciosAlquiler;</span>
<a class="sourceLine" id="cb4-15" data-line-number="15"></a><span class="kw">import</span><span class="im"> java.sql.Date;</span>
<a class="sourceLine" id="cb4-16" data-line-number="16"></a><span class="kw">import</span><span class="im"> java.util.List;</span>
<a class="sourceLine" id="cb4-17" data-line-number="17"></a>
<a class="sourceLine" id="cb4-18" data-line-number="18"></a><span class="at">@Singleton</span>
<a class="sourceLine" id="cb4-19" data-line-number="19"></a><span class="kw">public</span> <span class="kw">class</span> ServiciosAlquilerImpl <span class="kw">implements</span> ServiciosAlquiler {
<a class="sourceLine" id="cb4-20" data-line-number="20"></a>
<a class="sourceLine" id="cb4-21" data-line-number="21"></a>   <span class="at">@Inject</span>
<a class="sourceLine" id="cb4-22" data-line-number="22"></a>   <span class="kw">private</span> ItemDAO itemDAO;
<a class="sourceLine" id="cb4-23" data-line-number="23"></a>
<a class="sourceLine" id="cb4-24" data-line-number="24"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-25" data-line-number="25"></a>   <span class="kw">public</span> <span class="dt">int</span> <span class="fu">valorMultaRetrasoxDia</span>(<span class="dt">int</span> itemId) {
<a class="sourceLine" id="cb4-26" data-line-number="26"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-27" data-line-number="27"></a>   }
<a class="sourceLine" id="cb4-28" data-line-number="28"></a>
<a class="sourceLine" id="cb4-29" data-line-number="29"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-30" data-line-number="30"></a>   <span class="kw">public</span> Cliente <span class="fu">consultarCliente</span>(<span class="dt">long</span> docu) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-31" data-line-number="31"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-32" data-line-number="32"></a>   }
<a class="sourceLine" id="cb4-33" data-line-number="33"></a>
<a class="sourceLine" id="cb4-34" data-line-number="34"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-35" data-line-number="35"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;ItemRentado&gt; <span class="fu">consultarItemsCliente</span>(<span class="dt">long</span> idcliente) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-36" data-line-number="36"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-37" data-line-number="37"></a>   }
<a class="sourceLine" id="cb4-38" data-line-number="38"></a>
<a class="sourceLine" id="cb4-39" data-line-number="39"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-40" data-line-number="40"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;Cliente&gt; <span class="fu">consultarClientes</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-41" data-line-number="41"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-42" data-line-number="42"></a>   }
<a class="sourceLine" id="cb4-43" data-line-number="43"></a>
<a class="sourceLine" id="cb4-44" data-line-number="44"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-45" data-line-number="45"></a>   <span class="kw">public</span> Item <span class="fu">consultarItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-46" data-line-number="46"></a>       <span class="kw">try</span> {
<a class="sourceLine" id="cb4-47" data-line-number="47"></a>           <span class="kw">return</span> itemDAO.<span class="fu">load</span>(id);
<a class="sourceLine" id="cb4-48" data-line-number="48"></a>       } <span class="kw">catch</span> (PersistenceException ex) {
<a class="sourceLine" id="cb4-49" data-line-number="49"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"Error al consultar el item "</span>+id,ex);
<a class="sourceLine" id="cb4-50" data-line-number="50"></a>       }
<a class="sourceLine" id="cb4-51" data-line-number="51"></a>   }
<a class="sourceLine" id="cb4-52" data-line-number="52"></a>
<a class="sourceLine" id="cb4-53" data-line-number="53"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-54" data-line-number="54"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;Item&gt; <span class="fu">consultarItemsDisponibles</span>() {
<a class="sourceLine" id="cb4-55" data-line-number="55"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-56" data-line-number="56"></a>   }
<a class="sourceLine" id="cb4-57" data-line-number="57"></a>
<a class="sourceLine" id="cb4-58" data-line-number="58"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-59" data-line-number="59"></a>   <span class="kw">public</span> <span class="dt">long</span> <span class="fu">consultarMultaAlquiler</span>(<span class="dt">int</span> iditem, <span class="bu">Date</span> fechaDevolucion) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-60" data-line-number="60"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-61" data-line-number="61"></a>   }
<a class="sourceLine" id="cb4-62" data-line-number="62"></a>
<a class="sourceLine" id="cb4-63" data-line-number="63"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-64" data-line-number="64"></a>   <span class="kw">public</span> TipoItem <span class="fu">consultarTipoItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-65" data-line-number="65"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-66" data-line-number="66"></a>   }
<a class="sourceLine" id="cb4-67" data-line-number="67"></a>
<a class="sourceLine" id="cb4-68" data-line-number="68"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-69" data-line-number="69"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;TipoItem&gt; <span class="fu">consultarTiposItem</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-70" data-line-number="70"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-71" data-line-number="71"></a>   }
<a class="sourceLine" id="cb4-72" data-line-number="72"></a>
<a class="sourceLine" id="cb4-73" data-line-number="73"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-74" data-line-number="74"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarAlquilerCliente</span>(<span class="bu">Date</span> date, <span class="dt">long</span> docu, Item item, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-75" data-line-number="75"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-76" data-line-number="76"></a>   }
<a class="sourceLine" id="cb4-77" data-line-number="77"></a>
<a class="sourceLine" id="cb4-78" data-line-number="78"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-79" data-line-number="79"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarCliente</span>(Cliente c) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-80" data-line-number="80"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-81" data-line-number="81"></a>   }
<a class="sourceLine" id="cb4-82" data-line-number="82"></a>
<a class="sourceLine" id="cb4-83" data-line-number="83"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-84" data-line-number="84"></a>   <span class="kw">public</span> <span class="dt">long</span> <span class="fu">consultarCostoAlquiler</span>(<span class="dt">int</span> iditem, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-85" data-line-number="85"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-86" data-line-number="86"></a>   }
<a class="sourceLine" id="cb4-87" data-line-number="87"></a>
<a class="sourceLine" id="cb4-88" data-line-number="88"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-89" data-line-number="89"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">actualizarTarifaItem</span>(<span class="dt">int</span> id, <span class="dt">long</span> tarifa) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-90" data-line-number="90"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>);
<a class="sourceLine" id="cb4-91" data-line-number="91"></a>   }
<a class="sourceLine" id="cb4-92" data-line-number="92"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-93" data-line-number="93"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarItem</span>(Item i) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-94" data-line-number="94"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>); <span class="co">//To change body of generated methods, choose Tools | Templates.</span>
<a class="sourceLine" id="cb4-95" data-line-number="95"></a>   }
<a class="sourceLine" id="cb4-96" data-line-number="96"></a>
<a class="sourceLine" id="cb4-97" data-line-number="97"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb4-98" data-line-number="98"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">vetarCliente</span>(<span class="dt">long</span> docu, <span class="dt">boolean</span> estado) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb4-99" data-line-number="99"></a>       <span class="kw">throw</span> <span class="kw">new</span> <span class="bu">UnsupportedOperationException</span>(<span class="st">"Not supported yet."</span>); <span class="co">//To change body of generated methods, choose Tools | Templates.</span>
<a class="sourceLine" id="cb4-100" data-line-number="100"></a>   }
<a class="sourceLine" id="cb4-101" data-line-number="101"></a>}</code></pre>
</div>
</li>
<li>
<p>Realice la implementación de un servicio Stub (ServiciosAlquilerItemsStub) para que se pueda probar la lógica facilmente sin la capa de persistencia:</p>
<div class="sourceCode" id="cb5">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb5-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.samples.services.impl;</span>
<a class="sourceLine" id="cb5-2" data-line-number="2"></a>
<a class="sourceLine" id="cb5-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Cliente;</span>
<a class="sourceLine" id="cb5-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Item;</span>
<a class="sourceLine" id="cb5-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.ItemRentado;</span>
<a class="sourceLine" id="cb5-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.TipoItem;</span>
<a class="sourceLine" id="cb5-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;</span>
<a class="sourceLine" id="cb5-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ServiciosAlquiler;</span>
<a class="sourceLine" id="cb5-9" data-line-number="9"></a>
<a class="sourceLine" id="cb5-10" data-line-number="10"></a><span class="kw">import</span><span class="im"> java.io.Serializable;</span>
<a class="sourceLine" id="cb5-11" data-line-number="11"></a><span class="kw">import</span><span class="im"> java.sql.Date;</span>
<a class="sourceLine" id="cb5-12" data-line-number="12"></a><span class="kw">import</span><span class="im"> java.time.LocalDate;</span>
<a class="sourceLine" id="cb5-13" data-line-number="13"></a><span class="kw">import</span><span class="im"> java.time.temporal.ChronoUnit;</span>
<a class="sourceLine" id="cb5-14" data-line-number="14"></a><span class="kw">import</span><span class="im"> java.util.ArrayList;</span>
<a class="sourceLine" id="cb5-15" data-line-number="15"></a><span class="kw">import</span><span class="im"> java.util.HashMap;</span>
<a class="sourceLine" id="cb5-16" data-line-number="16"></a><span class="kw">import</span><span class="im"> java.util.LinkedList;</span>
<a class="sourceLine" id="cb5-17" data-line-number="17"></a><span class="kw">import</span><span class="im"> java.util.List;</span>
<a class="sourceLine" id="cb5-18" data-line-number="18"></a><span class="kw">import</span><span class="im"> java.util.Map;</span>
<a class="sourceLine" id="cb5-19" data-line-number="19"></a>
<a class="sourceLine" id="cb5-20" data-line-number="20"></a><span class="kw">public</span> <span class="kw">class</span> ServiciosAlquilerItemsStub <span class="kw">implements</span> ServiciosAlquiler {
<a class="sourceLine" id="cb5-21" data-line-number="21"></a>
<a class="sourceLine" id="cb5-22" data-line-number="22"></a>   <span class="kw">private</span> <span class="dt">static</span> <span class="dt">final</span> <span class="dt">int</span> MULTA_DIARIA=<span class="dv">5000</span>;
<a class="sourceLine" id="cb5-23" data-line-number="23"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="dt">static</span> <span class="dt">long</span> MILLISECONDS_IN_DAY = <span class="dv">24</span> * <span class="dv">60</span> * <span class="dv">60</span> * <span class="dv">1000</span>;
<a class="sourceLine" id="cb5-24" data-line-number="24"></a>
<a class="sourceLine" id="cb5-25" data-line-number="25"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="bu">Map</span>&lt;<span class="bu">Long</span>,Cliente&gt; clientes;
<a class="sourceLine" id="cb5-26" data-line-number="26"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="bu">Map</span>&lt;<span class="bu">Integer</span>,Item&gt; itemsDisponibles;
<a class="sourceLine" id="cb5-27" data-line-number="27"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="bu">Map</span>&lt;<span class="bu">Integer</span>,ItemRentado&gt; itemsrentados;
<a class="sourceLine" id="cb5-28" data-line-number="28"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="bu">Map</span>&lt;<span class="bu">Integer</span>,TipoItem&gt; tipositems;
<a class="sourceLine" id="cb5-29" data-line-number="29"></a>
<a class="sourceLine" id="cb5-30" data-line-number="30"></a>   <span class="kw">private</span> <span class="dt">final</span> <span class="bu">Map</span>&lt;<span class="bu">Integer</span>,<span class="bu">Long</span>&gt; mapaPrestamosPorIdCliente;
<a class="sourceLine" id="cb5-31" data-line-number="31"></a>
<a class="sourceLine" id="cb5-32" data-line-number="32"></a>   <span class="kw">public</span> <span class="fu">ServiciosAlquilerItemsStub</span>() {
<a class="sourceLine" id="cb5-33" data-line-number="33"></a>       clientes = <span class="kw">new</span> <span class="bu">HashMap</span>&lt;&gt;();
<a class="sourceLine" id="cb5-34" data-line-number="34"></a>       itemsDisponibles = <span class="kw">new</span> <span class="bu">HashMap</span>&lt;&gt;();
<a class="sourceLine" id="cb5-35" data-line-number="35"></a>       itemsrentados = <span class="kw">new</span> <span class="bu">HashMap</span>&lt;&gt;();
<a class="sourceLine" id="cb5-36" data-line-number="36"></a>       tipositems = <span class="kw">new</span> <span class="bu">HashMap</span>&lt;&gt;();
<a class="sourceLine" id="cb5-37" data-line-number="37"></a>       mapaPrestamosPorIdCliente=<span class="kw">new</span> <span class="bu">HashMap</span>&lt;&gt;();
<a class="sourceLine" id="cb5-38" data-line-number="38"></a>       <span class="co">//poblar();</span>
<a class="sourceLine" id="cb5-39" data-line-number="39"></a>   }
<a class="sourceLine" id="cb5-40" data-line-number="40"></a>
<a class="sourceLine" id="cb5-41" data-line-number="41"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-42" data-line-number="42"></a>   <span class="kw">public</span> <span class="dt">int</span> <span class="fu">valorMultaRetrasoxDia</span>(<span class="dt">int</span> itemId) {
<a class="sourceLine" id="cb5-43" data-line-number="43"></a>       <span class="kw">return</span> MULTA_DIARIA;
<a class="sourceLine" id="cb5-44" data-line-number="44"></a>   }
<a class="sourceLine" id="cb5-45" data-line-number="45"></a>
<a class="sourceLine" id="cb5-46" data-line-number="46"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-47" data-line-number="47"></a>   <span class="kw">public</span> Cliente <span class="fu">consultarCliente</span>(<span class="dt">long</span> docu) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-48" data-line-number="48"></a>       Cliente c=<span class="kw">null</span>;
<a class="sourceLine" id="cb5-49" data-line-number="49"></a>       <span class="kw">if</span>(clientes.<span class="fu">containsKey</span>(docu)){
<a class="sourceLine" id="cb5-50" data-line-number="50"></a>           c=clientes.<span class="fu">get</span>(docu);
<a class="sourceLine" id="cb5-51" data-line-number="51"></a>       }
<a class="sourceLine" id="cb5-52" data-line-number="52"></a>       <span class="kw">return</span> c;
<a class="sourceLine" id="cb5-53" data-line-number="53"></a>   }
<a class="sourceLine" id="cb5-54" data-line-number="54"></a>
<a class="sourceLine" id="cb5-55" data-line-number="55"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-56" data-line-number="56"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;Cliente&gt; <span class="fu">consultarClientes</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-57" data-line-number="57"></a>       <span class="kw">return</span>  <span class="kw">new</span> <span class="bu">LinkedList</span>&lt;&gt;(clientes.<span class="fu">values</span>());
<a class="sourceLine" id="cb5-58" data-line-number="58"></a>   }
<a class="sourceLine" id="cb5-59" data-line-number="59"></a>
<a class="sourceLine" id="cb5-60" data-line-number="60"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-61" data-line-number="61"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarCliente</span>(Cliente p) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-62" data-line-number="62"></a>       <span class="kw">if</span> (!clientes.<span class="fu">containsKey</span>(p.<span class="fu">getDocumento</span>())) {
<a class="sourceLine" id="cb5-63" data-line-number="63"></a>           clientes.<span class="fu">put</span>(p.<span class="fu">getDocumento</span>(), p);
<a class="sourceLine" id="cb5-64" data-line-number="64"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-65" data-line-number="65"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El cliente con documento "</span>+p+<span class="st">" ya esta registrado."</span>);
<a class="sourceLine" id="cb5-66" data-line-number="66"></a>       }
<a class="sourceLine" id="cb5-67" data-line-number="67"></a>   }
<a class="sourceLine" id="cb5-68" data-line-number="68"></a>
<a class="sourceLine" id="cb5-69" data-line-number="69"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-70" data-line-number="70"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">vetarCliente</span>(<span class="dt">long</span> docu, <span class="dt">boolean</span> estado) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-71" data-line-number="71"></a>       <span class="kw">if</span>(clientes.<span class="fu">containsKey</span>(docu)){
<a class="sourceLine" id="cb5-72" data-line-number="72"></a>           Cliente c=clientes.<span class="fu">get</span>(docu);
<a class="sourceLine" id="cb5-73" data-line-number="73"></a>           c.<span class="fu">setVetado</span>(estado);            
<a class="sourceLine" id="cb5-74" data-line-number="74"></a>       }
<a class="sourceLine" id="cb5-75" data-line-number="75"></a>       <span class="kw">else</span>{<span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"Cliente no registrado:"</span>+docu);}
<a class="sourceLine" id="cb5-76" data-line-number="76"></a>   }
<a class="sourceLine" id="cb5-77" data-line-number="77"></a>
<a class="sourceLine" id="cb5-78" data-line-number="78"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-79" data-line-number="79"></a>   <span class="kw">public</span> Item <span class="fu">consultarItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-80" data-line-number="80"></a>       Item i = <span class="kw">null</span>;
<a class="sourceLine" id="cb5-81" data-line-number="81"></a>       <span class="kw">if</span>(itemsDisponibles.<span class="fu">containsKey</span>(id)){
<a class="sourceLine" id="cb5-82" data-line-number="82"></a>           i=itemsDisponibles.<span class="fu">get</span>(id);
<a class="sourceLine" id="cb5-83" data-line-number="83"></a>       }
<a class="sourceLine" id="cb5-84" data-line-number="84"></a>       <span class="kw">else</span>{
<a class="sourceLine" id="cb5-85" data-line-number="85"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"Item no registrado:"</span>+id);
<a class="sourceLine" id="cb5-86" data-line-number="86"></a>       }                
<a class="sourceLine" id="cb5-87" data-line-number="87"></a>       <span class="kw">return</span> i;
<a class="sourceLine" id="cb5-88" data-line-number="88"></a>   }
<a class="sourceLine" id="cb5-89" data-line-number="89"></a>
<a class="sourceLine" id="cb5-90" data-line-number="90"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-91" data-line-number="91"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;Item&gt; <span class="fu">consultarItemsDisponibles</span>()  {
<a class="sourceLine" id="cb5-92" data-line-number="92"></a>       <span class="kw">return</span>  <span class="kw">new</span> <span class="bu">LinkedList</span>&lt;&gt;(itemsDisponibles.<span class="fu">values</span>());
<a class="sourceLine" id="cb5-93" data-line-number="93"></a>   }
<a class="sourceLine" id="cb5-94" data-line-number="94"></a>
<a class="sourceLine" id="cb5-95" data-line-number="95"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-96" data-line-number="96"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarItem</span>(Item i) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-97" data-line-number="97"></a>       <span class="kw">if</span> (!itemsDisponibles.<span class="fu">containsKey</span>(i.<span class="fu">getId</span>())) {
<a class="sourceLine" id="cb5-98" data-line-number="98"></a>           itemsDisponibles.<span class="fu">put</span>(i.<span class="fu">getId</span>(), i);
<a class="sourceLine" id="cb5-99" data-line-number="99"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-100" data-line-number="100"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El item "</span> + i.<span class="fu">getId</span>() + <span class="st">" ya esta registrado."</span>);
<a class="sourceLine" id="cb5-101" data-line-number="101"></a>       }
<a class="sourceLine" id="cb5-102" data-line-number="102"></a>   }
<a class="sourceLine" id="cb5-103" data-line-number="103"></a>
<a class="sourceLine" id="cb5-104" data-line-number="104"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-105" data-line-number="105"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">actualizarTarifaItem</span>(<span class="dt">int</span> id, <span class="dt">long</span> tarifa) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-106" data-line-number="106"></a>       <span class="kw">if</span> (!itemsDisponibles.<span class="fu">containsKey</span>(id)) {
<a class="sourceLine" id="cb5-107" data-line-number="107"></a>           Item c = itemsDisponibles.<span class="fu">get</span>(id);
<a class="sourceLine" id="cb5-108" data-line-number="108"></a>           c.<span class="fu">setTarifaxDia</span>(tarifa);
<a class="sourceLine" id="cb5-109" data-line-number="109"></a>           itemsDisponibles.<span class="fu">put</span>(id, c);
<a class="sourceLine" id="cb5-110" data-line-number="110"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-111" data-line-number="111"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El item "</span> + id + <span class="st">" no esta registrado."</span>);
<a class="sourceLine" id="cb5-112" data-line-number="112"></a>       }
<a class="sourceLine" id="cb5-113" data-line-number="113"></a>   }
<a class="sourceLine" id="cb5-114" data-line-number="114"></a>
<a class="sourceLine" id="cb5-115" data-line-number="115"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-116" data-line-number="116"></a>   <span class="kw">public</span> TipoItem <span class="fu">consultarTipoItem</span>(<span class="dt">int</span> id) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-117" data-line-number="117"></a>       TipoItem i = <span class="kw">null</span>;
<a class="sourceLine" id="cb5-118" data-line-number="118"></a>       <span class="kw">if</span>(!tipositems.<span class="fu">containsKey</span>(id)){
<a class="sourceLine" id="cb5-119" data-line-number="119"></a>           i=tipositems.<span class="fu">get</span>(id);
<a class="sourceLine" id="cb5-120" data-line-number="120"></a>       }
<a class="sourceLine" id="cb5-121" data-line-number="121"></a>       <span class="kw">return</span> i;
<a class="sourceLine" id="cb5-122" data-line-number="122"></a>
<a class="sourceLine" id="cb5-123" data-line-number="123"></a>   }
<a class="sourceLine" id="cb5-124" data-line-number="124"></a>
<a class="sourceLine" id="cb5-125" data-line-number="125"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-126" data-line-number="126"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;TipoItem&gt; <span class="fu">consultarTiposItem</span>() <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-127" data-line-number="127"></a>       <span class="kw">return</span>  <span class="kw">new</span> <span class="bu">LinkedList</span>&lt;&gt;(tipositems.<span class="fu">values</span>());
<a class="sourceLine" id="cb5-128" data-line-number="128"></a>   }
<a class="sourceLine" id="cb5-129" data-line-number="129"></a>
<a class="sourceLine" id="cb5-130" data-line-number="130"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-131" data-line-number="131"></a>   <span class="kw">public</span> <span class="dt">void</span> <span class="fu">registrarAlquilerCliente</span>(<span class="bu">Date</span> date,<span class="dt">long</span> docu, Item item, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-132" data-line-number="132"></a>
<a class="sourceLine" id="cb5-133" data-line-number="133"></a>       LocalDate ld=date.<span class="fu">toLocalDate</span>();
<a class="sourceLine" id="cb5-134" data-line-number="134"></a>       LocalDate ld2=ld.<span class="fu">plusDays</span>(numdias);
<a class="sourceLine" id="cb5-135" data-line-number="135"></a>
<a class="sourceLine" id="cb5-136" data-line-number="136"></a>       ItemRentado ir=<span class="kw">new</span> <span class="fu">ItemRentado</span>(<span class="dv">0</span>,item,date,java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(ld2));
<a class="sourceLine" id="cb5-137" data-line-number="137"></a>
<a class="sourceLine" id="cb5-138" data-line-number="138"></a>       <span class="kw">if</span> (clientes.<span class="fu">containsKey</span>(docu)) {
<a class="sourceLine" id="cb5-139" data-line-number="139"></a>           Cliente c = clientes.<span class="fu">get</span>(docu);
<a class="sourceLine" id="cb5-140" data-line-number="140"></a>           c.<span class="fu">getRentados</span>().<span class="fu">add</span>(ir);
<a class="sourceLine" id="cb5-141" data-line-number="141"></a>           itemsDisponibles.<span class="fu">remove</span>(ir.<span class="fu">getItem</span>().<span class="fu">getId</span>());
<a class="sourceLine" id="cb5-142" data-line-number="142"></a>           itemsrentados.<span class="fu">put</span>(item.<span class="fu">getId</span>(), ir);
<a class="sourceLine" id="cb5-143" data-line-number="143"></a>           mapaPrestamosPorIdCliente.<span class="fu">put</span>(item.<span class="fu">getId</span>(),docu);
<a class="sourceLine" id="cb5-144" data-line-number="144"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-145" data-line-number="145"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"No existe el cliente con el documento "</span> + docu);
<a class="sourceLine" id="cb5-146" data-line-number="146"></a>       }
<a class="sourceLine" id="cb5-147" data-line-number="147"></a>   }
<a class="sourceLine" id="cb5-148" data-line-number="148"></a>
<a class="sourceLine" id="cb5-149" data-line-number="149"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-150" data-line-number="150"></a>   <span class="kw">public</span> <span class="bu">List</span>&lt;ItemRentado&gt; <span class="fu">consultarItemsCliente</span>(<span class="dt">long</span> idcliente) <span class="kw">throws</span> ExcepcionServiciosAlquiler{        
<a class="sourceLine" id="cb5-151" data-line-number="151"></a>       <span class="kw">if</span> (clientes.<span class="fu">containsKey</span>(idcliente)) {
<a class="sourceLine" id="cb5-152" data-line-number="152"></a>           Cliente c = clientes.<span class="fu">get</span>(idcliente);
<a class="sourceLine" id="cb5-153" data-line-number="153"></a>           <span class="kw">return</span> c.<span class="fu">getRentados</span>();            
<a class="sourceLine" id="cb5-154" data-line-number="154"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-155" data-line-number="155"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"Cliente no registrado:"</span> + idcliente);
<a class="sourceLine" id="cb5-156" data-line-number="156"></a>       }
<a class="sourceLine" id="cb5-157" data-line-number="157"></a>
<a class="sourceLine" id="cb5-158" data-line-number="158"></a>   }
<a class="sourceLine" id="cb5-159" data-line-number="159"></a>
<a class="sourceLine" id="cb5-160" data-line-number="160"></a>   <span class="kw">private</span> Cliente <span class="fu">consultarClienteConItem</span>(<span class="dt">int</span> iditem) <span class="kw">throws</span> ExcepcionServiciosAlquiler{
<a class="sourceLine" id="cb5-161" data-line-number="161"></a>       <span class="kw">if</span> (mapaPrestamosPorIdCliente.<span class="fu">containsKey</span>(iditem)){  
<a class="sourceLine" id="cb5-162" data-line-number="162"></a>           <span class="dt">long</span> idcli=mapaPrestamosPorIdCliente.<span class="fu">get</span>(iditem);
<a class="sourceLine" id="cb5-163" data-line-number="163"></a>           <span class="kw">if</span> (clientes.<span class="fu">containsKey</span>(mapaPrestamosPorIdCliente.<span class="fu">get</span>(iditem))){
<a class="sourceLine" id="cb5-164" data-line-number="164"></a>               <span class="kw">return</span> clientes.<span class="fu">get</span>(idcli);
<a class="sourceLine" id="cb5-165" data-line-number="165"></a>           }
<a class="sourceLine" id="cb5-166" data-line-number="166"></a>           <span class="kw">else</span>{
<a class="sourceLine" id="cb5-167" data-line-number="167"></a>               <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El cliente "</span>+idcli+<span class="st">" asociado al "</span>
<a class="sourceLine" id="cb5-168" data-line-number="168"></a>                       + <span class="st">"alquiler del item "</span>+iditem+<span class="st">" no esta registrado."</span>);
<a class="sourceLine" id="cb5-169" data-line-number="169"></a>           }                        
<a class="sourceLine" id="cb5-170" data-line-number="170"></a>       }
<a class="sourceLine" id="cb5-171" data-line-number="171"></a>       <span class="kw">else</span>{
<a class="sourceLine" id="cb5-172" data-line-number="172"></a>          <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El item "</span>+iditem+ <span class="st">" no esta alquilado."</span>);
<a class="sourceLine" id="cb5-173" data-line-number="173"></a>       }
<a class="sourceLine" id="cb5-174" data-line-number="174"></a>   }
<a class="sourceLine" id="cb5-175" data-line-number="175"></a>
<a class="sourceLine" id="cb5-176" data-line-number="176"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-177" data-line-number="177"></a>   <span class="kw">public</span> <span class="dt">long</span> <span class="fu">consultarMultaAlquiler</span>(<span class="dt">int</span> iditem,<span class="bu">Date</span> fechaDevolucion) <span class="kw">throws</span> ExcepcionServiciosAlquiler{
<a class="sourceLine" id="cb5-178" data-line-number="178"></a>       <span class="kw">if</span> (!itemsrentados.<span class="fu">containsKey</span>(iditem)){
<a class="sourceLine" id="cb5-179" data-line-number="179"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El item "</span>+iditem+<span class="st">"no esta en alquiler"</span>);
<a class="sourceLine" id="cb5-180" data-line-number="180"></a>       }
<a class="sourceLine" id="cb5-181" data-line-number="181"></a>       <span class="kw">else</span>{
<a class="sourceLine" id="cb5-182" data-line-number="182"></a>           ItemRentado ir=itemsrentados.<span class="fu">get</span>(iditem);
<a class="sourceLine" id="cb5-183" data-line-number="183"></a>
<a class="sourceLine" id="cb5-184" data-line-number="184"></a>           LocalDate fechaMinimaEntrega=ir.<span class="fu">getFechafinrenta</span>().<span class="fu">toLocalDate</span>();
<a class="sourceLine" id="cb5-185" data-line-number="185"></a>           LocalDate fechaEntrega=fechaDevolucion.<span class="fu">toLocalDate</span>();
<a class="sourceLine" id="cb5-186" data-line-number="186"></a>           <span class="dt">long</span> diasRetraso = ChronoUnit.<span class="fu">DAYS</span>.<span class="fu">between</span>(fechaMinimaEntrega, fechaEntrega);
<a class="sourceLine" id="cb5-187" data-line-number="187"></a>           <span class="kw">return</span> diasRetraso*MULTA_DIARIA;
<a class="sourceLine" id="cb5-188" data-line-number="188"></a>       }
<a class="sourceLine" id="cb5-189" data-line-number="189"></a>   }
<a class="sourceLine" id="cb5-190" data-line-number="190"></a>
<a class="sourceLine" id="cb5-191" data-line-number="191"></a>   <span class="at">@Override</span>
<a class="sourceLine" id="cb5-192" data-line-number="192"></a>   <span class="kw">public</span> <span class="dt">long</span> <span class="fu">consultarCostoAlquiler</span>(<span class="dt">int</span> iditem, <span class="dt">int</span> numdias) <span class="kw">throws</span> ExcepcionServiciosAlquiler {
<a class="sourceLine" id="cb5-193" data-line-number="193"></a>       <span class="kw">if</span> (!itemsDisponibles.<span class="fu">containsKey</span>(iditem)) {
<a class="sourceLine" id="cb5-194" data-line-number="194"></a>           <span class="kw">throw</span> <span class="kw">new</span> <span class="fu">ExcepcionServiciosAlquiler</span>(<span class="st">"El item "</span> + iditem + <span class="st">" no esta disponible."</span>);
<a class="sourceLine" id="cb5-195" data-line-number="195"></a>       } <span class="kw">else</span> {
<a class="sourceLine" id="cb5-196" data-line-number="196"></a>           <span class="kw">return</span> itemsDisponibles.<span class="fu">get</span>(iditem).<span class="fu">getTarifaxDia</span>()*numdias;
<a class="sourceLine" id="cb5-197" data-line-number="197"></a>       }
<a class="sourceLine" id="cb5-198" data-line-number="198"></a>
<a class="sourceLine" id="cb5-199" data-line-number="199"></a>   }
<a class="sourceLine" id="cb5-200" data-line-number="200"></a>
<a class="sourceLine" id="cb5-201" data-line-number="201"></a>   <span class="kw">private</span> <span class="dt">void</span> <span class="fu">poblar</span>() {
<a class="sourceLine" id="cb5-202" data-line-number="202"></a>
<a class="sourceLine" id="cb5-203" data-line-number="203"></a>       TipoItem ti1=<span class="kw">new</span> <span class="fu">TipoItem</span>(<span class="dv">1</span>,<span class="st">"Video"</span>);
<a class="sourceLine" id="cb5-204" data-line-number="204"></a>       TipoItem ti2=<span class="kw">new</span> <span class="fu">TipoItem</span>(<span class="dv">2</span>,<span class="st">"Juego"</span>);
<a class="sourceLine" id="cb5-205" data-line-number="205"></a>       TipoItem ti3=<span class="kw">new</span> <span class="fu">TipoItem</span>(<span class="dv">3</span>,<span class="st">"Musica"</span>);
<a class="sourceLine" id="cb5-206" data-line-number="206"></a>       tipositems.<span class="fu">put</span>(<span class="dv">1</span>,ti1);
<a class="sourceLine" id="cb5-207" data-line-number="207"></a>       tipositems.<span class="fu">put</span>(<span class="dv">2</span>,ti2);
<a class="sourceLine" id="cb5-208" data-line-number="208"></a>       tipositems.<span class="fu">put</span>(<span class="dv">3</span>,ti3);
<a class="sourceLine" id="cb5-209" data-line-number="209"></a>
<a class="sourceLine" id="cb5-210" data-line-number="210"></a>       Item i1=<span class="kw">new</span> <span class="fu">Item</span>(ti1, <span class="dv">1</span>, <span class="st">"Los 4 Fantasticos"</span>, <span class="st">"Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2005-06-08"</span>), <span class="dv">2000</span>, <span class="st">"DVD"</span>, <span class="st">"Ciencia Ficcion"</span>);
<a class="sourceLine" id="cb5-211" data-line-number="211"></a>       Item i2=<span class="kw">new</span> <span class="fu">Item</span>(ti2, <span class="dv">2</span>, <span class="st">"Halo 3"</span>, <span class="st">"Halo 3 es un videojuego de disparos en primera persona desarrollado por Bungie Studios."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2007-09-08"</span>), <span class="dv">3000</span>, <span class="st">"DVD"</span>, <span class="st">"Shooter"</span>);
<a class="sourceLine" id="cb5-212" data-line-number="212"></a>       Item i3=<span class="kw">new</span> <span class="fu">Item</span>(ti3, <span class="dv">3</span>, <span class="st">"Thriller"</span>, <span class="st">"Thriller es una canción interpretada por el cantante estadounidense Michael Jackson, compuesta por Rod Temperton y producida por Quincy Jones."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"1984-01-11"</span>), <span class="dv">2000</span>, <span class="st">"DVD"</span>, <span class="st">"Pop"</span>);
<a class="sourceLine" id="cb5-213" data-line-number="213"></a>       Item i4=<span class="kw">new</span> <span class="fu">Item</span>(ti1, <span class="dv">4</span>, <span class="st">"Los 4 Fantasticos"</span>, <span class="st">"Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2005-06-08"</span>), <span class="dv">2000</span>, <span class="st">"DVD"</span>, <span class="st">"Ciencia Ficcion"</span>);
<a class="sourceLine" id="cb5-214" data-line-number="214"></a>       Item i5=<span class="kw">new</span> <span class="fu">Item</span>(ti2, <span class="dv">5</span>, <span class="st">"Halo 3"</span>, <span class="st">"Halo 3 es un videojuego de disparos en primera persona desarrollado por Bungie Studios."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2007-09-08"</span>), <span class="dv">3000</span>, <span class="st">"DVD"</span>, <span class="st">"Shooter"</span>);
<a class="sourceLine" id="cb5-215" data-line-number="215"></a>       Item i6=<span class="kw">new</span> <span class="fu">Item</span>(ti3, <span class="dv">6</span>, <span class="st">"Thriller"</span>, <span class="st">"Thriller es una canción interpretada por el cantante estadounidense Michael Jackson, compuesta por Rod Temperton y producida por Quincy Jones."</span>, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"1984-01-11"</span>), <span class="dv">2000</span>, <span class="st">"DVD"</span>, <span class="st">"Pop"</span>);
<a class="sourceLine" id="cb5-216" data-line-number="216"></a>       <span class="co">//items.put(1, i1);</span>
<a class="sourceLine" id="cb5-217" data-line-number="217"></a>       <span class="co">//items.put(2, i2);</span>
<a class="sourceLine" id="cb5-218" data-line-number="218"></a>       <span class="co">//items.put(3, i3);</span>
<a class="sourceLine" id="cb5-219" data-line-number="219"></a>       itemsDisponibles.<span class="fu">put</span>(<span class="dv">4</span>, i4);
<a class="sourceLine" id="cb5-220" data-line-number="220"></a>       itemsDisponibles.<span class="fu">put</span>(<span class="dv">5</span>, i5);
<a class="sourceLine" id="cb5-221" data-line-number="221"></a>       itemsDisponibles.<span class="fu">put</span>(<span class="dv">6</span>, i6);
<a class="sourceLine" id="cb5-222" data-line-number="222"></a>
<a class="sourceLine" id="cb5-223" data-line-number="223"></a>       ItemRentado ir1=<span class="kw">new</span> <span class="fu">ItemRentado</span>(<span class="dv">0</span>,i1, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-01-01"</span>), java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-03-12"</span>));
<a class="sourceLine" id="cb5-224" data-line-number="224"></a>       ItemRentado ir2=<span class="kw">new</span> <span class="fu">ItemRentado</span>(<span class="dv">0</span>,i2, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-01-04"</span>), java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-04-7"</span>));
<a class="sourceLine" id="cb5-225" data-line-number="225"></a>       ItemRentado ir3=<span class="kw">new</span> <span class="fu">ItemRentado</span>(<span class="dv">0</span>,i1, java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-01-07"</span>), java.<span class="fu">sql</span>.<span class="fu">Date</span>.<span class="fu">valueOf</span>(<span class="st">"2017-07-12"</span>));
<a class="sourceLine" id="cb5-226" data-line-number="226"></a>
<a class="sourceLine" id="cb5-227" data-line-number="227"></a>       <span class="bu">ArrayList</span>&lt;ItemRentado&gt; list1 = <span class="kw">new</span> <span class="bu">ArrayList</span>&lt;&gt;();
<a class="sourceLine" id="cb5-228" data-line-number="228"></a>       list1.<span class="fu">add</span>(ir1);
<a class="sourceLine" id="cb5-229" data-line-number="229"></a>       <span class="bu">ArrayList</span>&lt;ItemRentado&gt; list2 = <span class="kw">new</span> <span class="bu">ArrayList</span>&lt;&gt;();
<a class="sourceLine" id="cb5-230" data-line-number="230"></a>       list2.<span class="fu">add</span>(ir2);
<a class="sourceLine" id="cb5-231" data-line-number="231"></a>       <span class="bu">ArrayList</span>&lt;ItemRentado&gt; list3 = <span class="kw">new</span> <span class="bu">ArrayList</span>&lt;&gt;();
<a class="sourceLine" id="cb5-232" data-line-number="232"></a>       list3.<span class="fu">add</span>(ir3);
<a class="sourceLine" id="cb5-233" data-line-number="233"></a>
<a class="sourceLine" id="cb5-234" data-line-number="234"></a>       Cliente c1=<span class="kw">new</span> <span class="fu">Cliente</span>(<span class="st">"Oscar Alba"</span>, <span class="dv">1026585664</span>, <span class="st">"6788952"</span>, <span class="st">"KRA 109#34-C30"</span>, <span class="st">"oscar@hotmail.com"</span>, <span class="kw">false</span>,list1);
<a class="sourceLine" id="cb5-235" data-line-number="235"></a>       Cliente c2=<span class="kw">new</span> <span class="fu">Cliente</span>(<span class="st">"Carlos Ramirez"</span>, <span class="dv">1026585663</span>, <span class="st">"6584562"</span>, <span class="st">"KRA 59#27-a22"</span>, <span class="st">"carlos@hotmail.com"</span>, <span class="kw">false</span>,list2);
<a class="sourceLine" id="cb5-236" data-line-number="236"></a>       Cliente c3=<span class="kw">new</span> <span class="fu">Cliente</span>(<span class="st">"Ricardo Pinto"</span>, <span class="dv">1026585669</span>, <span class="st">"4457863"</span>, <span class="st">"KRA 103#94-a77"</span>, <span class="st">"ricardo@hotmail.com"</span>, <span class="kw">false</span>,list3);
<a class="sourceLine" id="cb5-237" data-line-number="237"></a>       clientes.<span class="fu">put</span>(c1.<span class="fu">getDocumento</span>(), c1);
<a class="sourceLine" id="cb5-238" data-line-number="238"></a>       clientes.<span class="fu">put</span>(c2.<span class="fu">getDocumento</span>(), c2);
<a class="sourceLine" id="cb5-239" data-line-number="239"></a>       clientes.<span class="fu">put</span>(c3.<span class="fu">getDocumento</span>(), c3);
<a class="sourceLine" id="cb5-240" data-line-number="240"></a>
<a class="sourceLine" id="cb5-241" data-line-number="241"></a>   }
<a class="sourceLine" id="cb5-242" data-line-number="242"></a>}</code></pre>
</div>
</li>
<li>
<p>Cree la clase ServiciosAlquilerFactory para que además de hacer ‘bind’ de la interfaz ServiciosAlquier con la implementación ServiciosAlquilerImpl, haga ‘bind’ de las entidades, por ejemplo, para <code>ItemDAO</code> se asocia con <code>MyBATISItemDAO</code>.</p>
<div class="sourceCode" id="cb6">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb6-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.samples.services;</span>
<a class="sourceLine" id="cb6-2" data-line-number="2"></a>
<a class="sourceLine" id="cb6-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> com.google.inject.Injector;</span>
<a class="sourceLine" id="cb6-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.ClienteDAO;</span>
<a class="sourceLine" id="cb6-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.ItemDAO;</span>
<a class="sourceLine" id="cb6-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.TipoItemDAO;</span>
<a class="sourceLine" id="cb6-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis.MyBATISClienteDAO;</span>
<a class="sourceLine" id="cb6-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis.MyBATISItemDAO;</span>
<a class="sourceLine" id="cb6-9" data-line-number="9"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.mybatis.MyBATISTipoItemDAO;</span>
<a class="sourceLine" id="cb6-10" data-line-number="10"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsImpl;</span>
<a class="sourceLine" id="cb6-11" data-line-number="11"></a><span class="kw">import</span><span class="im"> org.apache.ibatis.transaction.TransactionFactory;</span>
<a class="sourceLine" id="cb6-12" data-line-number="12"></a><span class="kw">import</span><span class="im"> org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;</span>
<a class="sourceLine" id="cb6-13" data-line-number="13"></a><span class="kw">import</span><span class="im"> org.mybatis.guice.XMLMyBatisModule;</span>
<a class="sourceLine" id="cb6-14" data-line-number="14"></a>
<a class="sourceLine" id="cb6-15" data-line-number="15"></a><span class="kw">import</span><span class="im"> java.util.Optional;</span>
<a class="sourceLine" id="cb6-16" data-line-number="16"></a>
<a class="sourceLine" id="cb6-17" data-line-number="17"></a><span class="kw">import static</span><span class="im"> com.google.inject.Guice.createInjector;</span>
<a class="sourceLine" id="cb6-18" data-line-number="18"></a>
<a class="sourceLine" id="cb6-19" data-line-number="19"></a><span class="kw">public</span> <span class="kw">class</span> ServiciosAlquilerFactory {
<a class="sourceLine" id="cb6-20" data-line-number="20"></a>
<a class="sourceLine" id="cb6-21" data-line-number="21"></a>   <span class="kw">private</span> <span class="dt">static</span> ServiciosAlquilerFactory instance = <span class="kw">new</span> <span class="fu">ServiciosAlquilerFactory</span>();
<a class="sourceLine" id="cb6-22" data-line-number="22"></a>
<a class="sourceLine" id="cb6-23" data-line-number="23"></a>   <span class="kw">private</span> <span class="dt">static</span> Optional&lt;Injector&gt; optInjector;
<a class="sourceLine" id="cb6-24" data-line-number="24"></a>
<a class="sourceLine" id="cb6-25" data-line-number="25"></a>   <span class="kw">private</span> Injector <span class="fu">myBatisInjector</span>(<span class="bu">String</span> env, <span class="bu">String</span> pathResource) {
<a class="sourceLine" id="cb6-26" data-line-number="26"></a>       <span class="kw">return</span> <span class="fu">createInjector</span>(<span class="kw">new</span> <span class="fu">XMLMyBatisModule</span>() {
<a class="sourceLine" id="cb6-27" data-line-number="27"></a>           <span class="at">@Override</span>
<a class="sourceLine" id="cb6-28" data-line-number="28"></a>           <span class="kw">protected</span> <span class="dt">void</span> <span class="fu">initialize</span>() {
<a class="sourceLine" id="cb6-29" data-line-number="29"></a>               <span class="fu">setEnvironmentId</span>(env);
<a class="sourceLine" id="cb6-30" data-line-number="30"></a>               <span class="fu">setClassPathResource</span>(pathResource);
<a class="sourceLine" id="cb6-31" data-line-number="31"></a>               <span class="fu">bind</span>(ItemDAO.<span class="fu">class</span>).<span class="fu">to</span>(MyBATISItemDAO.<span class="fu">class</span>);
<a class="sourceLine" id="cb6-32" data-line-number="32"></a>               <span class="fu">bind</span>(ServiciosAlquiler.<span class="fu">class</span>).<span class="fu">to</span>(ServiciosAlquilerItemsImpl.<span class="fu">class</span>);
<a class="sourceLine" id="cb6-33" data-line-number="33"></a>           }
<a class="sourceLine" id="cb6-34" data-line-number="34"></a>       });
<a class="sourceLine" id="cb6-35" data-line-number="35"></a>   }
<a class="sourceLine" id="cb6-36" data-line-number="36"></a>
<a class="sourceLine" id="cb6-37" data-line-number="37"></a>   <span class="kw">private</span> <span class="fu">ServiciosAlquilerFactory</span>(){
<a class="sourceLine" id="cb6-38" data-line-number="38"></a>       optInjector = Optional.<span class="fu">empty</span>();
<a class="sourceLine" id="cb6-39" data-line-number="39"></a>   }
<a class="sourceLine" id="cb6-40" data-line-number="40"></a>
<a class="sourceLine" id="cb6-41" data-line-number="41"></a>   <span class="kw">public</span> ServiciosAlquiler <span class="fu">getServiciosAlquiler</span>(){
<a class="sourceLine" id="cb6-42" data-line-number="42"></a>       <span class="kw">if</span> (!optInjector.<span class="fu">isPresent</span>()) {
<a class="sourceLine" id="cb6-43" data-line-number="43"></a>           optInjector = Optional.<span class="fu">of</span>(<span class="fu">myBatisInjector</span>(<span class="st">"development"</span>,<span class="st">"mybatis-config.xml"</span>));
<a class="sourceLine" id="cb6-44" data-line-number="44"></a>       }
<a class="sourceLine" id="cb6-45" data-line-number="45"></a>
<a class="sourceLine" id="cb6-46" data-line-number="46"></a>       <span class="kw">return</span> optInjector.<span class="fu">get</span>().<span class="fu">getInstance</span>(ServiciosAlquiler.<span class="fu">class</span>);
<a class="sourceLine" id="cb6-47" data-line-number="47"></a>   }
<a class="sourceLine" id="cb6-48" data-line-number="48"></a>
<a class="sourceLine" id="cb6-49" data-line-number="49"></a>
<a class="sourceLine" id="cb6-50" data-line-number="50"></a>   <span class="kw">public</span> ServiciosAlquiler <span class="fu">getServiciosAlquilerTesting</span>(){
<a class="sourceLine" id="cb6-51" data-line-number="51"></a>       <span class="kw">if</span> (!optInjector.<span class="fu">isPresent</span>()) {
<a class="sourceLine" id="cb6-52" data-line-number="52"></a>           optInjector = Optional.<span class="fu">of</span>(<span class="fu">myBatisInjector</span>(<span class="st">"test"</span>,<span class="st">"mybatis-config-h2.xml"</span>));
<a class="sourceLine" id="cb6-53" data-line-number="53"></a>       }
<a class="sourceLine" id="cb6-54" data-line-number="54"></a>
<a class="sourceLine" id="cb6-55" data-line-number="55"></a>       <span class="kw">return</span> optInjector.<span class="fu">get</span>().<span class="fu">getInstance</span>(ServiciosAlquiler.<span class="fu">class</span>);
<a class="sourceLine" id="cb6-56" data-line-number="56"></a>   }
<a class="sourceLine" id="cb6-57" data-line-number="57"></a>
<a class="sourceLine" id="cb6-58" data-line-number="58"></a>
<a class="sourceLine" id="cb6-59" data-line-number="59"></a>   <span class="kw">public</span> <span class="dt">static</span> ServiciosAlquilerFactory <span class="fu">getInstance</span>(){
<a class="sourceLine" id="cb6-60" data-line-number="60"></a>       <span class="kw">return</span> instance;
<a class="sourceLine" id="cb6-61" data-line-number="61"></a>   }
<a class="sourceLine" id="cb6-62" data-line-number="62"></a>
<a class="sourceLine" id="cb6-63" data-line-number="63"></a>}</code></pre>
</div>
</li>
<li>
<p>Pruebe el programa ‘Main’ suministrado, y con este rectifique que a través de la capa lógica se pueda consultar un cliente.</p>
</li>
<li>
<p>Implemente los métodos que sean necesarios en las interfaces de las entidades (DAO) y en sus implementaciones haciendo uso del DAO inyectado. Haga un programa para comprobar que la consulta de un cliente se haga correctamente, a través de la capa lógica.</p>
</li>
</ol>
<h1 id="parte-ii---pruebas">Parte II - Pruebas</h1>
<ol type="1">
<li>
<p>Implemente las operaciones de la lógica que hagan falta para satisfacer los requerimientos para la capa de presentación, teniendo en cuenta, que puede requerir agregar más operaciones a los DAOs -y por ende- más mappers de MyBATIS.</p>
</li>
<li>
<p>Tenga en cuenta: las operaciones que impliquen registrar o actualizar registros, demarcar la transaccionalidad con la anotación <span class="citation" data-cites="Transactional">@Transactional</span>.</p>
</li>
<li>
<p>Cree el archivo de configuracion de la base de datos de pruebas que es de tipo <code>h2</code>, en el directorio <code>src/main/resources</code>:</p>
<div class="sourceCode" id="cb7">
<pre class="sourceCode xml"><code class="sourceCode xml"><a class="sourceLine" id="cb7-1" data-line-number="1"></a><span class="kw">&lt;?xml</span> version="1.0" encoding="UTF-8" <span class="kw">?&gt;</span>
<a class="sourceLine" id="cb7-2" data-line-number="2"></a><span class="dt">&lt;!DOCTYPE </span>configuration
<a class="sourceLine" id="cb7-3" data-line-number="3"></a>PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
<a class="sourceLine" id="cb7-4" data-line-number="4"></a>"http://mybatis.org/dtd/mybatis-3-config.dtd"<span class="dt">&gt;</span>
<a class="sourceLine" id="cb7-5" data-line-number="5"></a><span class="kw">&lt;configuration&gt;</span>
<a class="sourceLine" id="cb7-6" data-line-number="6"></a>
<a class="sourceLine" id="cb7-7" data-line-number="7"></a>   <span class="kw">&lt;typeAliases&gt;</span>
<a class="sourceLine" id="cb7-8" data-line-number="8"></a>       <span class="kw">&lt;typeAlias</span><span class="ot"> type=</span><span class="st">'edu.eci.cvds.samples.entities.Cliente'</span><span class="ot"> alias=</span><span class="st">'Cliente'</span><span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-9" data-line-number="9"></a>       <span class="kw">&lt;typeAlias</span><span class="ot"> type=</span><span class="st">'edu.eci.cvds.samples.entities.Item'</span><span class="ot"> alias=</span><span class="st">'Item'</span><span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-10" data-line-number="10"></a>       <span class="kw">&lt;typeAlias</span><span class="ot"> type=</span><span class="st">'edu.eci.cvds.samples.entities.ItemRentado'</span><span class="ot"> alias=</span><span class="st">'ItemRentado'</span><span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-11" data-line-number="11"></a>       <span class="kw">&lt;typeAlias</span><span class="ot"> type=</span><span class="st">'edu.eci.cvds.samples.entities.TipoItem'</span><span class="ot"> alias=</span><span class="st">'TipoItem'</span><span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-12" data-line-number="12"></a>   <span class="kw">&lt;/typeAliases&gt;</span>
<a class="sourceLine" id="cb7-13" data-line-number="13"></a>
<a class="sourceLine" id="cb7-14" data-line-number="14"></a>   <span class="kw">&lt;environments</span><span class="ot"> default=</span><span class="st">"test"</span><span class="kw">&gt;</span>
<a class="sourceLine" id="cb7-15" data-line-number="15"></a>       <span class="kw">&lt;environment</span><span class="ot"> id=</span><span class="st">"test"</span><span class="kw">&gt;</span>
<a class="sourceLine" id="cb7-16" data-line-number="16"></a>           <span class="kw">&lt;transactionManager</span><span class="ot"> type=</span><span class="st">"JDBC"</span> <span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-17" data-line-number="17"></a>           <span class="kw">&lt;dataSource</span><span class="ot"> type=</span><span class="st">"POOLED"</span><span class="kw">&gt;</span>
<a class="sourceLine" id="cb7-18" data-line-number="18"></a>               <span class="kw">&lt;property</span><span class="ot"> name=</span><span class="st">"driver"</span><span class="ot"> value=</span><span class="st">"org.h2.Driver"</span> <span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-19" data-line-number="19"></a>               <span class="kw">&lt;property</span><span class="ot"> name=</span><span class="st">"url"</span><span class="ot"> value=</span><span class="st">"jdbc:h2:file:./target/db/testdb;MODE=MYSQL"</span> <span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-20" data-line-number="20"></a>               <span class="kw">&lt;property</span><span class="ot"> name=</span><span class="st">"username"</span><span class="ot"> value=</span><span class="st">"sa"</span> <span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-21" data-line-number="21"></a>               <span class="kw">&lt;property</span><span class="ot"> name=</span><span class="st">"password"</span><span class="ot"> value=</span><span class="st">""</span> <span class="kw">/&gt;</span>
<a class="sourceLine" id="cb7-22" data-line-number="22"></a>           <span class="kw">&lt;/dataSource&gt;</span>
<a class="sourceLine" id="cb7-23" data-line-number="23"></a>       <span class="kw">&lt;/environment&gt;</span>
<a class="sourceLine" id="cb7-24" data-line-number="24"></a>   <span class="kw">&lt;/environments&gt;</span>
<a class="sourceLine" id="cb7-25" data-line-number="25"></a>
<a class="sourceLine" id="cb7-26" data-line-number="26"></a>   <span class="kw">&lt;mappers&gt;</span>
<a class="sourceLine" id="cb7-27" data-line-number="27"></a>       <span class="kw">&lt;mapper</span><span class="ot"> resource=</span><span class="st">"mappers/ClienteMapper.xml"</span><span class="kw">&gt;&lt;/mapper&gt;</span>
<a class="sourceLine" id="cb7-28" data-line-number="28"></a>       <span class="kw">&lt;mapper</span><span class="ot"> resource=</span><span class="st">"mappers/ItemMapper.xml"</span><span class="kw">&gt;&lt;/mapper&gt;</span>
<a class="sourceLine" id="cb7-29" data-line-number="29"></a>       <span class="kw">&lt;mapper</span><span class="ot"> resource=</span><span class="st">"mappers/ItemRentadoMapper.xml"</span><span class="kw">&gt;&lt;/mapper&gt;</span>
<a class="sourceLine" id="cb7-30" data-line-number="30"></a>       <span class="kw">&lt;mapper</span><span class="ot"> resource=</span><span class="st">"mappers/TipoItemMapper.xml"</span><span class="kw">&gt;&lt;/mapper&gt;</span>
<a class="sourceLine" id="cb7-31" data-line-number="31"></a>   <span class="kw">&lt;/mappers&gt;</span> 
<a class="sourceLine" id="cb7-32" data-line-number="32"></a>
<a class="sourceLine" id="cb7-33" data-line-number="33"></a><span class="kw">&lt;/configuration&gt;</span></code></pre>
</div>
</li>
<li>
<p>Cree un archivo de pruebas</p>
<div class="sourceCode" id="cb8">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb8-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.test;</span>
<a class="sourceLine" id="cb8-2" data-line-number="2"></a>
<a class="sourceLine" id="cb8-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> java.util.ArrayList;</span>
<a class="sourceLine" id="cb8-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> java.util.List;</span>
<a class="sourceLine" id="cb8-5" data-line-number="5"></a>
<a class="sourceLine" id="cb8-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> com.google.inject.Inject;</span>
<a class="sourceLine" id="cb8-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.sampleprj.dao.PersistenceException;</span>
<a class="sourceLine" id="cb8-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.Cliente;</span>
<a class="sourceLine" id="cb8-9" data-line-number="9"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.entities.ItemRentado;</span>
<a class="sourceLine" id="cb8-10" data-line-number="10"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;</span>
<a class="sourceLine" id="cb8-11" data-line-number="11"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ServiciosAlquiler;</span>
<a class="sourceLine" id="cb8-12" data-line-number="12"></a><span class="kw">import</span><span class="im"> edu.eci.cvds.samples.services.ServiciosAlquilerFactory;</span>
<a class="sourceLine" id="cb8-13" data-line-number="13"></a><span class="kw">import</span><span class="im"> org.apache.ibatis.session.SqlSession;</span>
<a class="sourceLine" id="cb8-14" data-line-number="14"></a><span class="kw">import</span><span class="im"> org.junit.Before;</span>
<a class="sourceLine" id="cb8-15" data-line-number="15"></a><span class="kw">import</span><span class="im"> org.junit.Test;</span>
<a class="sourceLine" id="cb8-16" data-line-number="16"></a><span class="kw">import </span><span class="im">org.junit.Assert;</span>
<a class="sourceLine" id="cb8-17" data-line-number="17"></a>
<a class="sourceLine" id="cb8-23" data-line-number="23"></a><span class="kw">public</span> <span class="kw">class</span> ServiciosAlquilerTest {
<a class="sourceLine" id="cb8-24" data-line-number="24"></a>
<a class="sourceLine" id="cb8-25" data-line-number="25"></a>    <span class="at">@Inject</span>
<a class="sourceLine" id="cb8-26" data-line-number="26"></a>    <span class="kw">private</span> SqlSession sqlSession;
<a class="sourceLine" id="cb8-27" data-line-number="27"></a>
<a class="sourceLine" id="cb8-28" data-line-number="28"></a>    ServiciosAlquiler serviciosAlquiler;
<a class="sourceLine" id="cb8-29" data-line-number="29"></a>
<a class="sourceLine" id="cb8-30" data-line-number="30"></a>    <span class="kw">public</span> <span class="fu">ServiciosAlquilerTest</span>() {
<a class="sourceLine" id="cb8-31" data-line-number="31"></a>        serviciosAlquiler = ServiciosAlquilerFactory.<span class="fu">getInstance</span>().<span class="fu">getServiciosAlquilerTesting</span>();
<a class="sourceLine" id="cb8-32" data-line-number="32"></a>    }
<a class="sourceLine" id="cb8-33" data-line-number="33"></a>
<a class="sourceLine" id="cb8-34" data-line-number="34"></a>    <span class="at">@Before</span>
<a class="sourceLine" id="cb8-35" data-line-number="35"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">setUp</span>() {
<a class="sourceLine" id="cb8-36" data-line-number="36"></a>    }
<a class="sourceLine" id="cb8-37" data-line-number="37"></a>
<a class="sourceLine" id="cb8-38" data-line-number="38"></a>    <span class="at">@Test</span>
<a class="sourceLine" id="cb8-39" data-line-number="39"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">emptyDB</span>() {
<a class="sourceLine" id="cb8-40" data-line-number="40"></a>        <span class="fu">for</span>(<span class="fu">int i = 0</span>; <span class="fu">i</span> &lt; <span class="dv">100</span>; <span class="fu">i</span> += <span class="dv">10</span>) {
<a class="sourceLine" id="cb8-41" data-line-number="41"></a>            <span class="dt">boolean</span> r = <span class="kw">false</span>;
<a class="sourceLine" id="cb8-42" data-line-number="42"></a>            <span class="kw">try</span> {
<a class="sourceLine" id="cb8-43" data-line-number="43"></a>                Cliente cliente = serviciosAlquiler.<span class="fu">consultarCliente</span>(documento);
<a class="sourceLine" id="cb8-44" data-line-number="44"></a>            } <span class="kw">catch</span>(ExcepcionServiciosAlquiler e) {
<a class="sourceLine" id="cb8-45" data-line-number="45"></a>                r = <span class="kw">true</span>;
<a class="sourceLine" id="cb8-46" data-line-number="46"></a>            } <span class="kw">catch</span>(<span class="bu">IndexOutOfBoundsException</span> e) {
<a class="sourceLine" id="cb8-47" data-line-number="47"></a>                r = <span class="kw">true</span>;
<a class="sourceLine" id="cb8-48" data-line-number="48"></a>            }
<a class="sourceLine" id="cb8-49" data-line-number="49"></a>            <span class="kw">// Validate no Client was found</span>;
<a class="sourceLine" id="cb8-49" data-line-number="49"></a>            <span class="kw">Assert.assertTrue(r);</span>
<a class="sourceLine" id="cb8-50" data-line-number="50"></a>        };
<a class="sourceLine" id="cb8-51" data-line-number="51"></a>    }
<a class="sourceLine" id="cb8-52" data-line-number="52"></a>}</code></pre>
</div>
</li>
</ol>
<p>Cree diferentes pruebas utilizando las clases de equivalencia necesarias para las diferentes operaciones definidas en los servicios.</p>
<h1 id="parte-iii---capa-presentación">Parte III - Capa Presentación</h1>
<ol type="1">
<li>
<p>Realice los cambios necesarios en el archivo pom.xml de tal forma que el proyecto se construya de manera correcta como una aplicación WEB, incluyendo las dependencias (jstl, jsf-api, jsf-impl, primefaces, etc) y los plugins (maven war, tomcat7 maven, etc.).</p>
</li>
<li>
<p>Agregue el archivo web.xml requerido con la configuración necesaria. Al final del archivo agregue el siguiente listener:</p>
<div class="sourceCode" id="cb9">
<pre class="sourceCode xml"><code class="sourceCode xml"><a class="sourceLine" id="cb9-1" data-line-number="1"></a><span class="kw">&lt;listener&gt;</span>
<a class="sourceLine" id="cb9-2" data-line-number="2"></a>    <span class="kw">&lt;listener-class&gt;</span>edu.eci.cvds.guice.GuiceContextListener<span class="kw">&lt;/listener-class&gt;</span>
<a class="sourceLine" id="cb9-3" data-line-number="3"></a><span class="kw">&lt;/listener&gt;</span></code></pre>
</div>
</li>
<li>
<p>Cree el listener con el paquete y nombre indicados de forma que se asocie la configuración de Guice y MyBatis a la inicialización del contexto de la aplicación en el servidor tomcat7 embebido, con el siguiente contenido inicial, resolviendo el ‘TODO’ (asociando la interfaz del servicio a la implementación Stub):</p>
<div class="sourceCode" id="cb10">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb10-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.guice;</span>
<a class="sourceLine" id="cb10-2" data-line-number="2"></a>
<a class="sourceLine" id="cb10-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> javax.servlet.ServletContext;</span>
<a class="sourceLine" id="cb10-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> javax.servlet.ServletContextEvent;</span>
<a class="sourceLine" id="cb10-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> javax.servlet.ServletContextListener;</span>
<a class="sourceLine" id="cb10-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> org.mybatis.guice.XMLMyBatisModule;</span>
<a class="sourceLine" id="cb10-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> org.mybatis.guice.datasource.helper.JdbcHelper;</span>
<a class="sourceLine" id="cb10-8" data-line-number="8"></a><span class="kw">import</span><span class="im"> com.google.inject.Guice;</span>
<a class="sourceLine" id="cb10-9" data-line-number="9"></a><span class="kw">import</span><span class="im"> com.google.inject.Injector;</span>
<a class="sourceLine" id="cb10-10" data-line-number="10"></a>
<a class="sourceLine" id="cb10-11" data-line-number="11"></a><span class="kw">public</span> <span class="kw">class</span> GuiceContextListener <span class="kw">implements</span> ServletContextListener {
<a class="sourceLine" id="cb10-12" data-line-number="12"></a>
<a class="sourceLine" id="cb10-13" data-line-number="13"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">contextDestroyed</span>(ServletContextEvent servletContextEvent) {
<a class="sourceLine" id="cb10-14" data-line-number="14"></a>        ServletContext servletContext = servletContextEvent.<span class="fu">getServletContext</span>();
<a class="sourceLine" id="cb10-15" data-line-number="15"></a>        servletContext.<span class="fu">removeAttribute</span>(Injector.<span class="fu">class</span>.<span class="fu">getName</span>());
<a class="sourceLine" id="cb10-16" data-line-number="16"></a>    }
<a class="sourceLine" id="cb10-17" data-line-number="17"></a>
<a class="sourceLine" id="cb10-18" data-line-number="18"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">contextInitialized</span>(ServletContextEvent servletContextEvent) {
<a class="sourceLine" id="cb10-19" data-line-number="19"></a>        Injector injector = Guice.<span class="fu">createInjector</span>(<span class="kw">new</span> <span class="fu">XMLMyBatisModule</span>() {
<a class="sourceLine" id="cb10-20" data-line-number="20"></a>            <span class="at">@Override</span>
<a class="sourceLine" id="cb10-21" data-line-number="21"></a>            <span class="kw">protected</span> <span class="dt">void</span> <span class="fu">initialize</span>() {
<a class="sourceLine" id="cb10-22" data-line-number="22"></a>                <span class="fu">install</span>(JdbcHelper.<span class="fu">MySQL</span>);
<a class="sourceLine" id="cb10-23" data-line-number="23"></a>                <span class="fu">setEnvironmentId</span>(<span class="st">"development"</span>);
<a class="sourceLine" id="cb10-24" data-line-number="24"></a>                <span class="fu">setClassPathResource</span>(<span class="st">"mybatis-config.xml"</span>);
<a class="sourceLine" id="cb10-25" data-line-number="25"></a>
<a class="sourceLine" id="cb10-26" data-line-number="26"></a>                <span class="co">// TODO Add service class associated to Stub implementation</span>
<a class="sourceLine" id="cb10-27" data-line-number="27"></a>                <span class="fu">bind</span>(AAA.<span class="fu">class</span>).<span class="fu">to</span>(YYY.<span class="fu">class</span>);
<a class="sourceLine" id="cb10-28" data-line-number="28"></a>                <span class="fu">bind</span>(BBB.<span class="fu">class</span>).<span class="fu">to</span>(ZZZ.<span class="fu">class</span>);
<a class="sourceLine" id="cb10-29" data-line-number="29"></a>            }
<a class="sourceLine" id="cb10-30" data-line-number="30"></a>        });
<a class="sourceLine" id="cb10-31" data-line-number="31"></a>
<a class="sourceLine" id="cb10-32" data-line-number="32"></a>        servletContextEvent.<span class="fu">getServletContext</span>().<span class="fu">setAttribute</span>(Injector.<span class="fu">class</span>.<span class="fu">getName</span>(), injector);
<a class="sourceLine" id="cb10-33" data-line-number="33"></a>    }
<a class="sourceLine" id="cb10-34" data-line-number="34"></a>}</code></pre>
</div>
</li>
<li>
<p>Cree el bean BasePageBean en el paquete “edu.eci.cvds.view” con el siguiente contenido para que se puedan inyectar los componentes necesarios en todas las clases “hijas” que serán los beans de la capa de presentación:</p>
<div class="sourceCode" id="cb11">
<pre class="sourceCode java"><code class="sourceCode java"><a class="sourceLine" id="cb11-1" data-line-number="1"></a><span class="kw">package</span><span class="im"> edu.eci.cvds.view;</span>
<a class="sourceLine" id="cb11-2" data-line-number="2"></a>
<a class="sourceLine" id="cb11-3" data-line-number="3"></a><span class="kw">import</span><span class="im"> java.io.Serializable;</span>
<a class="sourceLine" id="cb11-4" data-line-number="4"></a><span class="kw">import</span><span class="im"> javax.annotation.PostConstruct;</span>
<a class="sourceLine" id="cb11-5" data-line-number="5"></a><span class="kw">import</span><span class="im"> javax.faces.context.FacesContext;</span>
<a class="sourceLine" id="cb11-6" data-line-number="6"></a><span class="kw">import</span><span class="im"> javax.servlet.ServletContext;</span>
<a class="sourceLine" id="cb11-7" data-line-number="7"></a><span class="kw">import</span><span class="im"> com.google.inject.Injector;</span>
<a class="sourceLine" id="cb11-8" data-line-number="8"></a>
<a class="sourceLine" id="cb11-9" data-line-number="9"></a><span class="kw">public</span> <span class="kw">abstract</span> <span class="kw">class</span> BasePageBean <span class="kw">implements</span> <span class="bu">Serializable</span> {
<a class="sourceLine" id="cb11-10" data-line-number="10"></a>
<a class="sourceLine" id="cb11-11" data-line-number="11"></a>    <span class="kw">private</span> Injector injector;
<a class="sourceLine" id="cb11-12" data-line-number="12"></a>
<a class="sourceLine" id="cb11-13" data-line-number="13"></a>    <span class="kw">public</span> Injector <span class="fu">getInjector</span>() {
<a class="sourceLine" id="cb11-14" data-line-number="14"></a>        <span class="kw">if</span> (injector == <span class="kw">null</span>) {
<a class="sourceLine" id="cb11-15" data-line-number="15"></a>            ServletContext servletContext = (ServletContext) FacesContext.<span class="fu">getCurrentInstance</span>().<span class="fu">getExternalContext</span>()
<a class="sourceLine" id="cb11-16" data-line-number="16"></a>                    .<span class="fu">getContext</span>();
<a class="sourceLine" id="cb11-17" data-line-number="17"></a>            injector = (Injector) servletContext.<span class="fu">getAttribute</span>(Injector.<span class="fu">class</span>.<span class="fu">getName</span>());
<a class="sourceLine" id="cb11-18" data-line-number="18"></a>        }
<a class="sourceLine" id="cb11-19" data-line-number="19"></a>        <span class="kw">return</span> injector;
<a class="sourceLine" id="cb11-20" data-line-number="20"></a>    }
<a class="sourceLine" id="cb11-21" data-line-number="21"></a>
<a class="sourceLine" id="cb11-22" data-line-number="22"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">setInjector</span>(Injector injector) {
<a class="sourceLine" id="cb11-23" data-line-number="23"></a>        <span class="kw">this</span>.<span class="fu">injector</span> = injector;
<a class="sourceLine" id="cb11-24" data-line-number="24"></a>    }
<a class="sourceLine" id="cb11-25" data-line-number="25"></a>
<a class="sourceLine" id="cb11-26" data-line-number="26"></a>    <span class="at">@PostConstruct</span>
<a class="sourceLine" id="cb11-27" data-line-number="27"></a>    <span class="kw">public</span> <span class="dt">void</span> <span class="fu">init</span>() {
<a class="sourceLine" id="cb11-28" data-line-number="28"></a>        <span class="fu">getInjector</span>().<span class="fu">injectMembers</span>(<span class="kw">this</span>);
<a class="sourceLine" id="cb11-29" data-line-number="29"></a>    }
<a class="sourceLine" id="cb11-30" data-line-number="30"></a>}</code></pre>
</div>
</li>
<li>
<p>Implementar la aplicación Web que permita agregar nuevos clientes a la videotienda, y registrar alquileres para los mismos. Ambas funcionalidades estarán en dos vistas diferentes (registrocliente.xhtml, registroalquiler.xhtml), de acuerdo con las siguientes especificaciones (tenga en cuenta que, por ahora, la aplicación no maneja ningún esquema de autenticación):</p>
<ol type="1">
<li>La vista de ‘registro de clientes’ debe (1) mostrar el listado paginado de los clientes registrados hasta el momento (con la opción de selecciar de uno de éstos), y (2) debe mostrar los campos para poder registrar un nuevo cliente (con su respectivo botón de registro). Cuando se registre un nuevo cliente, se deberá mostrar automáticamente el nuevo cliente en la parte superior.</li>
<li>Cuando se seleccione uno de los usuarios ya creados, se debe redirigir al usuario a la vista de ‘registro de alquileres’. En esta vista, dado el cliente seleccionado, se debe (1) mostrar los items que no ha regresado, junto con el valor de la multa total asociada a los mismos a la fecha (fecha del sistema), y (2), debe permtir registrar un nuevo alquiler ingresando el código del item (asumiendo que éste se ingresará con un lector de código de barras), el número de días del alquiler, y mostrando el costo del alquiler antes de su confirmación. En el momento que se confirme, se debe volver a la página anterior (registro de clientes).</li>
<li>Ambas vistas se basarán en el ManagedBean de sesión ‘AlquilerItemsBean’ que debe extender ‘BasePageBean’, el cual -a su vez- hace uso de la interfaz ‘ServiciosAlquiler’ (no agregar directamente una implementación concreta, esto se realizará en la configuración de Guice).</li>
<li>
<p>El desarrollo de ambas vistas debe quedar distribuido entre los dos desarrolladores de la siguiente manera:</p>
<ul>
<li>Desarrollador 1: Vista registro de cliente.</li>
<li>Desarrollador 2: Vista registro de alquiler.</li>
<li>Desarrollador 1 y 2: ManagedBean ‘AlquilerItemsBean’.</li>
<li>Cada integrante debe realizar su propio commit pues después se verificarán los cambios de cada uno.</li>
</ul>
</li>
</ol>
<p>Nota. Para ver cómo navegar entre vistas con JSF revise <a href="http://www.tutorialspoint.com/jsf/jsf_page_navigation.htm">este enlace.</a></p>
</li>
<li>
<p>Construya y despliegue la aplicación con el comando <code>mvn tomcat7:run</code> y realice pruebas de la presentación, que debe estar funcionando correctamente, con la implementación ‘Stub’ del servicio de alquiler.</p>
</li>
<li>
<p>Modifique la configuración de Guice para asociar a la interfaz, el servicio concreto de alquileres, de forma que todos los cambios que se realicen en la presentación, se actualicen en base de datos de manera correcta.</p>
</li>
<li>
<p>Realice los ajustes necesarios para que la aplicación funcione de manera correcta y se asegure que todos los métodos están realizando las operaciones sobre la base de datos.</p>
</li>
</ol>
<h1 id="parte-iv---entrega-continua">Parte IV - Entrega Continua</h1>
<ol type="1">
<li>
<p>Realice toda la configuración necesaria de CircleCI y Heroku para que la aplicación se construya y despliegue de manera automática cada que se realice un commit al repositorio.</p>
</li>
<li>
<p>Realice también todas las configuraciones necesarias de Codacy y los ajustes necesarios para obtener una calificación satisfactoria.</p>
</li>
<li>
<p>Verifique que la aplicación se despliegue correctamente en Heroku y sea completamente funcional, tal como se encontraba en local.</p>
</li>
<li>Agregue en el Readme los enlaces necesarios a Heroku, Codacy, etc. para que se pueda verificar el correcto funcionamiento de toda la aplicación.</li>
</ol>
<h1 id="entrega">Entrega:</h1>
<ol type="1">
<li>
<p>En los ‘logs’ de GitHub debe quedar evidencia de los ‘commit’ realizados por cada autor, y en computadores diferentes (esto es verificable). Para esto, no olvide configurar su usuario antes de hacer los commits:</p>
<pre><code>$ git config --global user.name "John Doe"
$ git config --global user.email johndoe@example.com</code></pre>
</li>
<li>
<p>Subir en el espacio de moodle la URL del repositorio en GitHUB.</p>



<h1 id="Autores de la entrega del Lab">Autores De ejecucion del LAB</h1>
<ol type="1">
<li>
<p>Ivan Camilo Rincon Saavedra <a href="https://github.com/Rincon10">Rincon10.</a></p>
</li>
<li>
<p>Leonardo Galeano Garzon <a href="https://github.com/Ersocaut">Ersocaut.</a></p>
</li>
