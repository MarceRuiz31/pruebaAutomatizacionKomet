package proyecto.automatizacion.komet.test.stepdefinition;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import io.github.bonigarcia.wdm.WebDriverManager;
import mx4j.log.Log;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import proyecto.automatizacion.komet.test.controllers.ComprasController;
import proyecto.automatizacion.komet.test.controllers.BaseController;
import proyecto.automatizacion.komet.test.controllers.CuentaController;
import proyecto.automatizacion.komet.test.dto.DatosComprador;

public class CompraStep {

    WebDriver webDriver;
    BaseController navegadorController;
    ComprasController comprasController;
    CuentaController cuentaController;
    DatosComprador datosComprador = new DatosComprador();

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().version("2.40").setup();
        webDriver = new ChromeDriver();
        navegadorController = new BaseController(webDriver);
        comprasController = new ComprasController(webDriver);
        cuentaController = new CuentaController(webDriver);
    }

    @Dado("que un usuario ingresa a la plataforma de compras")
    public void queUnUsuarioIngresaALaPlataformaDeCompras() {
        navegadorController.abrirDriver();
    }

    @Cuando("selecciona un prenda con el descuento de (.*?) %, de talla (.*?), de color (.*?)")
    public void seleccionaUnPrendaConElDescuentoDeTallaDeColor(String descuento,String talla, String color) {
        comprasController.verificarIngresoAplicativo();
        comprasController.seleccionarZonaCompra();
        comprasController.buscarPrenda(descuento,talla,color);
        Log.getLogger("hOLA");
    }

    @Entonces("se verifica que se haya cargado la prenda en el carrito")
    public void seVerificaQueSeHayaCargadoLaPrendaEnElCarrito() {
        comprasController.verificarPrendaCarrito();
    }

    @Y("se verifica la información de la orden de compra")
    public void seVerificaLaInformacionDeLaOrdenDeCompra() {
        comprasController.continuarCompra();
        datosComprador = cuentaController.crearCuenta();
        comprasController.verificarDatosEntrega(datosComprador);
        comprasController.continuarCompra();
    }

    @Y("se confirma la compra")
    public void seConfirmaLaCompra() {
        comprasController.aceptarEnvio();
        int opcionPago = comprasController.seleccionarPago();
        comprasController.verificarSeleccionPago(opcionPago);
        comprasController.terminarCompra(opcionPago);
    }

    @After
    public void tearDown(){
        navegadorController.cerrarDriver();
    }
}
