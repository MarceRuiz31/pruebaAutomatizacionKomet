package proyecto.automatizacion.komet.test.controllers;

import com.github.javafaker.Faker;
import mx4j.log.Log;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import proyecto.automatizacion.komet.test.dto.DatosComprador;
import proyecto.automatizacion.komet.test.pages.DireccionesPage;
import proyecto.automatizacion.komet.test.pages.EnvioPage;
import proyecto.automatizacion.komet.test.pages.PagoPage;
import proyecto.automatizacion.komet.test.pages.ZonaComprasPage;

import java.util.ArrayList;
import java.util.List;

import static proyecto.automatizacion.komet.test.helps.Diccionario.MSG_ERROR;
import static proyecto.automatizacion.komet.test.helps.Diccionario.URL_INICIO;

public class ComprasControlador {

    ZonaComprasPage zonaComprasPage;
    EnvioPage envioPage;
    PagoPage pagoPage;
    DireccionesPage direccionesPage;
    WebDriver webDriver;
    WebDriverWait wait;
    JavascriptExecutor javascriptExecutor;
    Faker faker = new Faker();

    public ComprasControlador(WebDriver webDriver){
        this.webDriver= webDriver;
        wait = new WebDriverWait(webDriver, 10);
        zonaComprasPage = new ZonaComprasPage(webDriver);
        envioPage = new EnvioPage(webDriver);
        pagoPage = new PagoPage(webDriver);
        direccionesPage = new DireccionesPage(webDriver);
        javascriptExecutor = (JavascriptExecutor) webDriver ;
    }

    public void verificarIngresoAplicativo() {
        try {
            String url_actual = webDriver.getCurrentUrl();
            Assert.assertEquals(String.format("No fue posible ingresa al aplicativo de compras, url esperada: %s, url obtenida: %s",URL_INICIO,url_actual),URL_INICIO,url_actual);
            Log.getLogger("Se verifica");
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible comprar las url de inicio",e);
        }
    }

    public void seleccionarZonaCompra() {
        try {
            wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getBtnWomen())).click();
        }catch (Exception e){
            throw new IllegalArgumentException("No fue dar clic en el botón",e);
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
            throw new IllegalArgumentException("No se encuentran prendas con las espeficaciones ingresadas para comprar",e);
        }
    }

    private void seleccionarPrendaDescuento(String descuento) {
        List<WebElement> listDesc = new ArrayList<>();
        int intentos = 3;
        try {
            do{
                listDesc = zonaComprasPage.getListDescuentos();
                intentos= intentos - 1;
            }while (listDesc.isEmpty() && intentos > 0);

            for (WebElement elemento : listDesc){
                String valor = elemento.getText();
                if (valor.contains(descuento)){
                    elemento.click();
                }
            }
        }catch (Exception e){
            throw new IllegalArgumentException(String.format("No se encuentra prenda con el %s de descuento",descuento),e);
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
            throw new IllegalArgumentException(String.format("No se encuentra prenda con el color: %s",color),e);
        }
    }

    private void seleccionarTalla(String talla){
        try {
            Select selectTalla = new Select(zonaComprasPage.getCmbSize());
            selectTalla.selectByVisibleText(talla);
        }catch (Exception e){
            throw new IllegalArgumentException(String.format("No se encuentra prenda con la talla: %s",talla),e);
        }
    }

    public void verificarPrendaCarrito() {
        try {
            String cantidadArticulos = zonaComprasPage.getLblCar().getText();
            Assert.assertEquals("No fue posible agregar la prenda al carrito de compras", "1", cantidadArticulos);
        }catch (Exception e){
            throw new IllegalArgumentException("No se encuentra la prenda cargada en el carrito de compras",e);
        }
    }

    public void continuarCompra() {
        javascriptExecutor.executeScript("window.scrollBy(0,300)");
        wait.until(ExpectedConditions.visibilityOf(zonaComprasPage.getBtnCheckout())).click();
    }

    public void verificarDatosEntrega(DatosComprador datosComprador) {
        try {
            ///// datos front de la orden de compra
            String nombresOrden = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblNombres())).getText();
            String direccionOrden = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblDireccion())).getText();
            String ciudadCodigoOrden = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblCodigo())).getText();
            String paisOrden = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblPais())).getText();
            String telefonoOrden = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblTelefono())).getText();

            ///////datos creación cuenta
            String nombresCuenta = String.format("%s %s",datosComprador.getNombre(),datosComprador.getApellidos());
            String ciudadCodigoCuenta = String.format("%s, %s %s",datosComprador.getCiudad(),datosComprador.getEstado(),datosComprador.getCodigoPostal());

            ///////Comparar datos, entre orden y cuenta creada
            Assert.assertEquals(String.format(MSG_ERROR,nombresCuenta,nombresOrden),nombresOrden,nombresCuenta);
            Assert.assertEquals(String.format(MSG_ERROR,datosComprador.getDireccion(),direccionOrden),direccionOrden,datosComprador.getDireccion());
            Assert.assertEquals(String.format(MSG_ERROR,ciudadCodigoCuenta,ciudadCodigoOrden),ciudadCodigoOrden,ciudadCodigoCuenta);
            Assert.assertEquals(String.format(MSG_ERROR,datosComprador.getPais(),paisOrden),paisOrden,datosComprador.getPais());
            Assert.assertEquals(String.format(MSG_ERROR,datosComprador.getTelefono(),telefonoOrden),telefonoOrden,datosComprador.getTelefono());
        }catch (Exception e){
            Assert.fail("No fue posible comparar los datos de Entrega");
        }
    }

    public void aceptarEnvio() {
        try {
            boolean pasoEnvio = wait.until(ExpectedConditions.visibilityOf(envioPage.getPasoEnvio())).isEnabled();
            Assert.assertTrue("No se logra ingresar al paso de Envío de la compra",pasoEnvio);

            wait.until(ExpectedConditions.visibilityOf(envioPage.getChecCondiciones())).click();
            wait.until(ExpectedConditions.visibilityOf(envioPage.getBtnConfirmarEnvio())).click();
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible aceptar el envío de la prenda",e);
        }
    }

    public int seleccionarPago() {
        int ramdo;
        try {
            boolean pasoPago = wait.until(ExpectedConditions.visibilityOf(pagoPage.getLblFormaPago())).isEnabled();
            Assert.assertTrue("No fue posible ingresar al paso de Pago",pasoPago);
            javascriptExecutor.executeScript("window.scrollBy(0,400)");
            ramdo = faker.number().numberBetween(0,1);
            if (ramdo == 0){
                wait.until(ExpectedConditions.visibilityOf(pagoPage.getListFormaPago().get(0))).click();
            }else {
                wait.until(ExpectedConditions.visibilityOf(pagoPage.getListFormaPago().get(1))).click();
            }
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible seleccionar el tipo de pago",e);
        }
        return ramdo;
    }

    public void verificarSeleccionPago(int opcionPago) {
        try {
            if (opcionPago == 0){
                String texto = wait.until(ExpectedConditions.visibilityOf(pagoPage.getLblTarjeta())).getText();
                Assert.assertTrue("No fue posible seleccionar la forma de pago Tarjeta",texto.contains("BANK-WIRE PAYMENT."));
            }else {
                boolean formCheque = wait.until(ExpectedConditions.visibilityOf(pagoPage.getLblCheque())).isEnabled();
                Assert.assertTrue("No fue posible seleccionar la forma de pago Cheque",formCheque);
            }
            wait.until(ExpectedConditions.visibilityOf(pagoPage.getBtnConfirmaCompra())).click();
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible verificar la selección de pago de la compra",e);
        }
    }

    public void terminarCompra(int opcionPago) {
        try {
            if (opcionPago == 0){
                boolean compraFinalizadaTarjeta = wait.until(ExpectedConditions.visibilityOf(pagoPage.getMsgTerminarCompraTarjeta())).isEnabled();
                Assert.assertTrue("No fue posible finalizar la compra",compraFinalizadaTarjeta);
            }else {
                boolean compraFinalizadaCheque = wait.until(ExpectedConditions.visibilityOf(pagoPage.getMsgTerminarComprarCheque())).isEnabled();
                Assert.assertTrue("No fue posible finalizar la compra",compraFinalizadaCheque);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible terminar la compra",e);
        }
    }
}
