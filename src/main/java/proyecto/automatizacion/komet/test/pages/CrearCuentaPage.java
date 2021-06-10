package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CrearCuentaPage {

    @FindBy(id = "customer_firstname")
    private WebElement inputNombres;

    @FindBy(id = "customer_lastname")
    private WebElement inputApellidos;

    @FindBy(id = "passwd")
    private WebElement inputContrasena;

    @FindBy(id = "firstname")
    private WebElement inputNombreCompa;

    @FindBy(id = "lastname")
    private WebElement inputApellidoCompa;

    @FindBy(id = "address1")
    private WebElement inputDireccion;

    @FindBy(id = "city")
    private WebElement inputCiudad;

    @FindBy(id = "id_state")
    private WebElement slEstado;

    @FindBy(id = "uniform-id_state")
    private WebElement lblEstado;

    @FindBy(id = "postcode")
    private WebElement inputCodigoPos;

    @FindBy(id = "id_country")
    private WebElement slPais;

    @FindBy(id = "phone_mobile")
    private WebElement inputTelefono;

    @FindBy(id="alias")
    private WebElement inputAlias;

    @FindBy(id = "submitAccount")
    private WebElement btnRegistrar;

    public CrearCuentaPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public WebElement getInputNombres() {
        return inputNombres;
    }

    public WebElement getInputApellidos() {
        return inputApellidos;
    }

    public WebElement getInputContrasena() {
        return inputContrasena;
    }

    public WebElement getInputNombreCompa() {
        return inputNombreCompa;
    }

    public WebElement getInputApellidoCompa() {
        return inputApellidoCompa;
    }

    public WebElement getInputDireccion() {
        return inputDireccion;
    }

    public WebElement getInputCiudad() {
        return inputCiudad;
    }

    public WebElement getSlEstado() {
        return slEstado;
    }

    public WebElement getLblEstado() {
        return lblEstado;
    }

    public WebElement getInputCodigoPos() {
        return inputCodigoPos;
    }

    public WebElement getSlPais() {
        return slPais;
    }

    public WebElement getInputTelefono() {
        return inputTelefono;
    }

    public WebElement getInputAlias() {
        return inputAlias;
    }

    public WebElement getBtnRegistrar() {
        return btnRegistrar;
    }
}
