package proyecto.automatizacion.komet.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ZonaComprasPage {

    @FindBy(xpath = "//*[@id='block_top_menu']/ul/li[1]")
    private WebElement btnWomen;

    @FindBy(id = "group_1")
    private WebElement cmbSize;

    @FindAll(@FindBy(xpath = "//*[@id='color_to_pick_list']//a"))
    private List<WebElement> listColor;

    @FindBy(xpath = "//*[@class='cat-name']")
    private WebElement lbWoman;

    @FindAll(@FindBy(xpath = "//*[@class='product_list grid row']//li//*[@class='right-block']//*[@class='price-percent-reduction']"))
    private List<WebElement> listDescuentos;

    @FindBy(xpath = "//*[@id='add_to_cart']//button")
    private WebElement btnAddCar;

    @FindBy(xpath = "//*[@class='button-container']//a")
    private WebElement btnProceedCheckout;

    @FindBy(id = "layer_cart_product_attributes")
    private WebElement lblCaracte;

    @FindBy(xpath = "//*[@class='shopping_cart']//*[@class='ajax_cart_quantity']")
    private WebElement lblCar;

    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    private WebElement btnCheckout;

    public ZonaComprasPage(WebDriver webDriver){
        PageFactory.initElements(webDriver,this);
    }

    public WebElement getBtnWomen() {
        return btnWomen;
    }

    public WebElement getLbWoman() {
        return lbWoman;
    }

    public List<WebElement> getListDescuentos() {
        return listDescuentos;
    }

    public WebElement getBtnAddCar() {
        return btnAddCar;
    }

    public WebElement getCmbSize() {
        return cmbSize;
    }

    public List<WebElement> getListColor() {
        return listColor;
    }

    public WebElement getBtnProceedCheckout() {
        return btnProceedCheckout;
    }

    public WebElement getLblCaracte() {
        return lblCaracte;
    }

    public WebElement getLblCar() {
        return lblCar;
    }

    public WebElement getBtnCheckout() {
        return btnCheckout;
    }
}
