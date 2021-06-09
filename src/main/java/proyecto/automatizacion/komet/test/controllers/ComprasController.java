package proyecto.automatizacion.komet.test.controllers;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import proyecto.automatizacion.komet.test.pages.ZonaComprasPage;

import java.util.List;

import static proyecto.automatizacion.komet.test.helps.Diccionario.URL_INICIO;

public class ComprasController {

    ZonaComprasPage zonaComprasPage;
    WebDriver webDriver;
    WebDriverWait wait;

    public ComprasController(WebDriver webDriver){
        this.webDriver= webDriver;
        wait = new WebDriverWait(webDriver, 10);
        zonaComprasPage = new ZonaComprasPage(webDriver);
    }

    public void verificarIngresoAplicativo() {
        try {
            String url_actual = webDriver.getCurrentUrl();
            Assert.assertEquals(String.format("No fue posible ingresa al aplicativo de compras, url esperada: %s, url obtenida: %s",URL_INICIO,url_actual),URL_INICIO,url_actual);
        }catch (Exception e){
            System.out.println("No fue posible comprar las url de inicio"+e);
        }
    }

    public void seleccionarZonaCompra() {
        try {
            wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getBtnWomen())).click();
        }catch (Exception e){
            System.out.println("No fue dar clic en el botón"+e);
        }
    }

    public void buscarPrenda(String descuento, String talla, String color) {
        try {
            boolean zonaWoman = wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getLbWoman())).isEnabled();
            Assert.assertTrue("No fue posible ingresar a la zona de mujeres",zonaWoman);

            seleccionarPrendaDescuento(descuento);
            seleccionarTalla(talla);
            seleccionarColor(color);

            wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getBtnAddCar())).click();

            String atributoObtenido = wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getLblCaracte())).getText();
            String atributoEsperado = String.format("%s, %s",color,talla);
            Assert.assertEquals("No se han seleccionado correctamente los atributos de talla y color",atributoEsperado,atributoObtenido);

            wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getBtnProceedCheckout())).click();

        }catch (Exception e){

        }
    }

    private void seleccionarPrendaDescuento(String descuento) {
        try {
            List<WebElement> listDesc = zonaComprasPage.getListDescuentos();
            for (WebElement elemento : listDesc){
                String valor = elemento.getText();
                if (valor.contains(descuento)){
                    elemento.click();
                }
            }
        }catch (Exception e){

        }
    }

    private void seleccionarColor(String color) {
        try {
            List<WebElement> listSize = zonaComprasPage.getListColor();
            for (WebElement elemento : listSize){
                String valor = elemento.getAttribute("name");
                if (valor.equals(color)){
                    elemento.click();
                }
            }
        }catch (Exception e){

        }
    }

    private void seleccionarTalla(String talla){
        try {
            Select selectTalla = new Select(zonaComprasPage.getCmbSize());
            selectTalla.selectByVisibleText(talla);
        }catch (Exception e){

        }
    }
}
