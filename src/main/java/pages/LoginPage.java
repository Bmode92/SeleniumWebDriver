package pages;

import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;
    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SecurePage login(User user) {
        sendText(userName, user.getLogin());
        sendText(password, user.getPassword());
        loginBtn.click();
        return new SecurePage(driver);
    }

    private void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
