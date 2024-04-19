package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramePage {

    private WebDriver driver;
    @FindBy(xpath = "//a[@href='/iframe']")
    private WebElement iframesLink;

    public FramePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public IFramesPage openIframesLink() {
        iframesLink.click();
        return new IFramesPage(driver);
    }
}
