package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PagoPage {

    @FindAll(@FindBy(className = "payment_module"))
    private List<WebElement> listFormaPago;

    @FindBy(className = "page-heading")
    private WebElement lblFormaPago;

    @FindBy(xpath = "//span[text()='I confirm my order']")
    private WebElement btnConfirmaCompra;

    @FindBy(xpath = "//*[@class='box cheque-box']//h3[text()='Check payment']")
    private WebElement lblCheque;


    @FindBy(xpath = "//*[@class='box cheque-box']//*[@class='page-subheading']")
    private WebElement lblTarjeta;

    @FindBy(xpath = "//*[@class='alert alert-success']")
    private WebElement msgTerminarComprarCheque;

    @FindBy(xpath = "//*[@class='cheque-indent']")
    private WebElement msgTerminarCompraTarjeta;

    public PagoPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public List<WebElement> getListFormaPago() {
        return listFormaPago;
    }

    public WebElement getLblFormaPago() {
        return lblFormaPago;
    }

    public WebElement getBtnConfirmaCompra() {
        return btnConfirmaCompra;
    }

    public WebElement getLblCheque() {
        return lblCheque;
    }

    public WebElement getLblTarjeta() {
        return lblTarjeta;
    }

    public WebElement getMsgTerminarComprarCheque() {
        return msgTerminarComprarCheque;
    }

    public WebElement getMsgTerminarCompraTarjeta() {
        return msgTerminarCompraTarjeta;
    }
}
