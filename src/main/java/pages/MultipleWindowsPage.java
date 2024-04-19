package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MultipleWindowsPage {

    private WebDriver driver;
    @FindBy(xpath = "//a[@href='/windows/new']")
    private WebElement newWindow;
    @FindBy(xpath = "//div/h3")
    private WebElement openWindowText;

    public MultipleWindowsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getOpenWindowText() {
        return openWindowText.getText();
    }

    public void openNewWindow() {
        openWindowText.click();
    }
}
