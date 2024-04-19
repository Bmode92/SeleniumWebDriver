package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePage {

    private final WebDriver driver;
    @FindBy(css = ".icon-signout")
    private WebElement logoutBtn;

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage logout() {
        logoutBtn.click();
        return  new LoginPage(driver);
    }

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }
}
