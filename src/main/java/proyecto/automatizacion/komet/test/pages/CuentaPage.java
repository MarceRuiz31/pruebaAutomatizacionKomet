package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CuentaPage {

    @FindBy(id = "SubmitCreate")
    private WebElement btnCrearCuenta;

    @FindBy(id = "email_create")
    private WebElement inputEmailNuevo;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "passwd")
    private WebElement inputPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnInicioSesion;


    public CuentaPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public WebElement getBtnCrearCuenta() {
        return btnCrearCuenta;
    }

    public WebElement getInputEmailNuevo() {
        return inputEmailNuevo;
    }

    public WebElement getInputEmail() {
        return inputEmail;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getBtnInicioSesion() {
        return btnInicioSesion;
    }
}
