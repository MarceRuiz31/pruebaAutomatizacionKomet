package proyecto.automatizacion.komet.test.steps;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Y;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import proyecto.automatizacion.komet.test.controllers.ComprasController;
import proyecto.automatizacion.komet.test.controllers.BaseController;

public class CompraStep {

    WebDriver webDriver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().version("2.40").setup();
        webDriver = new ChromeDriver();
    }

    @Dado("que un usuario ingresa a la plataforma de compras")
    public void queUnUsuarioIngresaALaPlataformaDeCompras() {
        BaseController navegadorController = new BaseController(webDriver);
        navegadorController.abrirDriver();
    }

    @Cuando("selecciona un prenda con el descuento de (.*?) %, de talla (.*?), de color (.*?)")
    public void seleccionaUnPrendaConElDescuentoDeTallaDeColor(String descuento,String talla, String color) {
        ComprasController comprasController = new ComprasController(webDriver);
        comprasController.verificarIngresoAplicativo();
        comprasController.seleccionarZonaCompra();
        comprasController.buscarPrenda(descuento,talla,color);
    }
}
