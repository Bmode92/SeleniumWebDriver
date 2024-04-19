package Tests;

import data.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTest {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void chainLoginTest() {
        User user = new User("tomsmith", "SuperSecretPassword!");

        assertTrue(new HomePage(driver).open()
                .openLoginPage()
                .login(user)
                .getLogoutBtn().isDisplayed());
    }

    @Test
    public void classicPomLoginTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.openLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(new User("tomsmith", "SuperSecretPassword!"));

        SecurePage securePage = new SecurePage(driver);
        assertTrue(securePage.getLogoutBtn().isDisplayed());

        securePage.logout();
    }

    @Test
    public void tableTest() {
        new HomePage(driver)
                .open()
                .openDataTablePage()
                .clickRowEditDeleteOption("edit", "Smith");
    }

    @Test
    public void multipleWindowsTest() {
        MultipleWindowsPage multiple = new HomePage(driver)
                .open()
                .openMultipleWindowsPage();

        String parentWindow = driver.getWindowHandle();
        multiple.openNewWindow();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.contentEquals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.close();

        driver.switchTo().window(parentWindow);
        assertEquals(driver.getWindowHandle(), parentWindow);
    }

    @Test
    public void iframeTest() {
        IFramesPage iFramesPage = new HomePage(driver)
                .open()
                .openFramePage()
                .openIframesLink();

        iFramesPage.switchToFrame();
        iFramesPage.sendText("text");
        assertEquals("text", iFramesPage.getText());
    }

    @Test
    public void dragAndDropTest() {
        DragDropPage dragDropPage = new HomePage(driver)
                .open()
                .openDragDropPage();

        dragDropPage.dragElementToPosition();
        assertEquals("A", dragDropPage.getTargetText());
    }

    @Test
    public void addRemoveElementsTest() {
        AddRemovePage addRemovePage = new HomePage(driver)
                .open()
                .openAddRemovePage();

        addRemovePage.addElement(2);
        assertEquals(2, addRemovePage.getNrOfElementsPresent());
    }

}
