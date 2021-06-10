package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DireccionesPage {

    @FindBy(xpath = "//*[@id='address_delivery']//h3")
    private WebElement lblDatosEntrega;

    @FindBy(xpath = "//*[@id='address_delivery']//*[@class='address_firstname address_lastname']")
    private WebElement lblNombres;

    @FindBy(xpath = "//*[@id='address_delivery']//*[@class='address_address1 address_address2']")
    private WebElement lblDireccion;

    @FindBy(xpath = "//*[@id='address_delivery']//*[@class='address_city address_state_name address_postcode']")
    private WebElement lblCodigo;

    @FindBy(xpath = "//*[@id='address_delivery']//*[@class='address_country_name']")
    private WebElement lblPais;

    @FindBy(xpath = "//*[@id='address_delivery']//*[@class='address_phone_mobile']")
    private WebElement lblTelefono;

    public DireccionesPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public WebElement getLblDatosEntrega() {
        return lblDatosEntrega;
    }

    public WebElement getLblNombres() {
        return lblNombres;
    }

    public WebElement getLblDireccion() {
        return lblDireccion;
    }

    public WebElement getLblCodigo() {
        return lblCodigo;
    }

    public WebElement getLblPais() {
        return lblPais;
    }

    public WebElement getLblTelefono() {
        return lblTelefono;
    }
}
