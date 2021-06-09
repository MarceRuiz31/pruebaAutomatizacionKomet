package proyecto.automatizacion.komet.test.controllers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static proyecto.automatizacion.komet.test.helps.Diccionario.URL;

public class BaseController {

    WebDriver webDriver;

    public BaseController(WebDriver webDriver){
        this.webDriver= webDriver;
    }

    public void abrirDriver() {
        try {
//            WebDriverManager.chromedriver().version("2.40").setup();
//            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get(URL);
        }catch (Exception e){
            System.out.println("No fue posible abrir el navegador");
        }
    }

    public void cerrarDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            System.out.println("No fue posible cerrar el navegador");
        }
    }

}
