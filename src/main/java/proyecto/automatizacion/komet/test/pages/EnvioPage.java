package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnvioPage {

    @FindBy(id = "carrier_area")
    private WebElement pasoEnvio;

    @FindBy(id = "uniform-cgv")
    private WebElement checCondiciones;

    @FindBy(name = "processCarrier")
    private WebElement btnConfirmarEnvio;

    public EnvioPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public WebElement getPasoEnvio() {
        return pasoEnvio;
    }

    public WebElement getChecCondiciones() {
        return checCondiciones;
    }

    public WebElement getBtnConfirmarEnvio() {
        return btnConfirmarEnvio;
    }
}
