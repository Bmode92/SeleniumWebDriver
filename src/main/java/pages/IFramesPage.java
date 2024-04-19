package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IFramesPage {

    private WebDriver driver;
    @FindBy(id = "tinymce")
    private WebElement inputField;
    @FindBy(id = "mce_0_ifr")
    private WebElement frame;

    public IFramesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void switchToFrame() {
        driver.switchTo().frame(frame);
    }

    public void sendText(String text) {
        inputField.clear();
        inputField.sendKeys(text);
    }

    public String getText() {
        return inputField.getText();
    }
}
