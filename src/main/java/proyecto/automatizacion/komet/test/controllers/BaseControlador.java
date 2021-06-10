package proyecto.automatizacion.komet.test.controllers;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static proyecto.automatizacion.komet.test.helps.Diccionario.URL;

public class BaseControlador {

    WebDriver webDriver;

    public BaseControlador(WebDriver webDriver){
        this.webDriver= webDriver;
    }

    public void abrirDriver() {
        try {
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get(URL);
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible abrir el navegador",e);
        }
    }

    public void cerrarDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            throw new IllegalArgumentException("No fue posible cerrar el navegador",e);
        }
    }

}
