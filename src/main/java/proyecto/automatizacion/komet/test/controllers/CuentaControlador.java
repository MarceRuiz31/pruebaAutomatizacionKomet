package proyecto.automatizacion.komet.test.controllers;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import proyecto.automatizacion.komet.test.dto.DatosComprador;
import proyecto.automatizacion.komet.test.pages.CrearCuentaPage;
import proyecto.automatizacion.komet.test.pages.CuentaPage;
import proyecto.automatizacion.komet.test.pages.DireccionesPage;

import static proyecto.automatizacion.komet.test.helps.Diccionario.PAIS;

public class CuentaControlador {

    static Faker faker = new Faker();
    WebDriver webDriver;
    WebDriverWait wait;
    CuentaPage cuentaPage;
    CrearCuentaPage crearCuentaPage;
    DireccionesPage direccionesPage;

    public CuentaControlador(WebDriver webDriver){
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        cuentaPage = new CuentaPage(webDriver);
        crearCuentaPage = new CrearCuentaPage(webDriver);
        direccionesPage = new DireccionesPage(webDriver);
    }

    public DatosComprador crearCuenta() {
        try {
            String correo = faker.internet().emailAddress();
            cuentaPage.getInputEmailNuevo().sendKeys(correo);
            cuentaPage.getBtnCrearCuenta().click();

            return ingresarDatosCreacionCuenta(correo);
        }catch (Exception e){
            return new DatosComprador();
        }
    }

    private DatosComprador ingresarDatosCreacionCuenta(String correo) {
        DatosComprador datosComprador = new DatosComprador();
        try {
            String nombres = faker.name().firstName();
            String apellidos = faker.name().lastName();
            String direccion = faker.address().streetAddress();
            String ciudad = faker.address().city();
            String estado = String.valueOf(faker.number().numberBetween(1,50));
            int codigoPostal = faker.number().numberBetween(1,9);
            String codigoPostalFinal = String.format("0000%s",String.valueOf(codigoPostal));
            String telefono = faker.phoneNumber().cellPhone();

            crearCuentaPage.getInputNombres().sendKeys(nombres);
            crearCuentaPage.getInputApellidos().sendKeys(apellidos);
            crearCuentaPage.getInputContrasena().sendKeys(correo);
            crearCuentaPage.getInputDireccion().sendKeys(direccion);
            crearCuentaPage.getInputCiudad().sendKeys(ciudad);
            Select selectEstado = new Select(crearCuentaPage.getSlEstado());
            selectEstado.selectByValue(estado);
            crearCuentaPage.getInputCodigoPos().sendKeys(codigoPostalFinal);
            Select selectPais = new Select(crearCuentaPage.getSlPais());
            selectPais.selectByValue("21");
            crearCuentaPage.getInputTelefono().sendKeys(telefono);
            crearCuentaPage.getInputAlias().clear();
            crearCuentaPage.getInputAlias().sendKeys(correo);

            datosComprador.setNombre(nombres);
            datosComprador.setApellidos(apellidos);
            datosComprador.setDireccion(direccion);
            datosComprador.setCiudad(ciudad);
            datosComprador.setPais(PAIS);
            String estados[] = crearCuentaPage.getLblEstado().getText().split("-");
            datosComprador.setEstado(estados[0].trim());
            datosComprador.setCodigoPostal(codigoPostalFinal);
            datosComprador.setTelefono(telefono);
            datosComprador.setNombresCompa(nombres);
            datosComprador.setApellidosCompa(apellidos);

            crearCuentaPage.getBtnRegistrar().click();

        }catch (Exception e){
            throw new IllegalArgumentException("No se puso ingresar los datos de la creación de cuenta",e);
        }
        return datosComprador;
    }

    public void iniciarSesion(String usuario) {
        try {
            wait.until(ExpectedConditions.visibilityOf(cuentaPage.getInputEmail())).sendKeys(usuario);
            wait.until(ExpectedConditions.visibilityOf(cuentaPage.getInputPassword())).sendKeys(usuario);
            wait.until(ExpectedConditions.visibilityOf(cuentaPage.getBtnInicioSesion())).click();

            boolean stepDirecciones = wait.until(ExpectedConditions.visibilityOf(direccionesPage.getLblDatosEntrega())).isEnabled();
            Assert.assertTrue("No fue posible ingresar al paso de Direcciones",stepDirecciones);
        }catch (Exception e){
            throw new IllegalArgumentException("No fue posible iniciar sesión para continuar con la compra",e);
        }
    }
}
