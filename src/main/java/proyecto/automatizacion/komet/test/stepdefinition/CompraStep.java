package proyecto.automatizacion.komet.test.stepdefinition;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.github.bonigarcia.wdm.WebDriverManager;
import mx4j.log.Log;
import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import proyecto.automatizacion.komet.test.controllers.ComprasControlador;
import proyecto.automatizacion.komet.test.controllers.BaseControlador;
import proyecto.automatizacion.komet.test.controllers.CuentaControlador;
import proyecto.automatizacion.komet.test.dto.DatosComprador;

public class CompraStep {

    WebDriver webDriver;
    BaseControlador navegadorController;
    ComprasControlador comprasControlador;
    CuentaControlador cuentaControlador;
    DatosComprador datosComprador = new DatosComprador();

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().version("2.40").setup();
        webDriver = new ChromeDriver();
        navegadorController = new BaseControlador(webDriver);
        comprasControlador = new ComprasControlador(webDriver);
        cuentaControlador = new CuentaControlador(webDriver);
    }

    @Dado("que un usuario ingresa a la plataforma de compras")
    public void queUnUsuarioIngresaALaPlataformaDeCompras() {
        navegadorController.abrirDriver();
    }

    @Cuando("selecciona un prenda con el descuento de (.*?) %, de talla (.*?), de color (.*?)")
    public void seleccionaUnPrendaConElDescuentoDeTallaDeColor(String descuento,String talla, String color) {
        comprasControlador.verificarIngresoAplicativo();
        comprasControlador.seleccionarZonaCompra();
        comprasControlador.buscarPrenda(descuento,talla,color);
        Log.getLogger("hOLA");
    }

    @Entonces("se verifica que se haya cargado la prenda en el carrito")
    public void seVerificaQueSeHayaCargadoLaPrendaEnElCarrito() {
        comprasControlador.verificarPrendaCarrito();
    }

    @Y("se verifica la información de la orden de compra")
    public void seVerificaLaInformacionDeLaOrdenDeCompra() {
        comprasControlador.continuarCompra();
        datosComprador = cuentaControlador.crearCuenta();
        comprasControlador.verificarDatosEntrega(datosComprador);
        comprasControlador.continuarCompra();
    }

    @Y("se verifica la información de la orden de compra con el usuario (.*?)")
    public void seVerificaLaInformacionDeLaOrdenDeCompraConUnaCuentaYaCreada(String usuario) {
        comprasControlador.continuarCompra();
        cuentaControlador.iniciarSesion(usuario);
        comprasControlador.continuarCompra();
    }

    @Y("se confirma la compra")
    public void seConfirmaLaCompra() {
        comprasControlador.aceptarEnvio();
        int opcionPago = comprasControlador.seleccionarPago();
        comprasControlador.verificarSeleccionPago(opcionPago);
        comprasControlador.terminarCompra(opcionPago);
    }

    @After
    public void tearDown(){
        navegadorController.cerrarDriver();
    }


}
