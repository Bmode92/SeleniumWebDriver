package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOne {

    private WebDriver driver;
    private WebDriverWait wait;
    private FluentWait<WebDriver> fluentWait;

    @BeforeEach
    public void before() {
        WebDriverManager.chromedriver().setup();
        String downloadPath = "/path/to/download/directory";

        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("default.default_directory",downloadPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("pref", preferences);

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void baseTest() {
        WebElement formAuthentication = driver.findElement(By.xpath("//a[contains(text(), 'Form Authentication')]"));
        formAuthentication.click();

        WebElement userName = driver.findElement(By.id("username"));
        sendText(userName, "tomsmith");

        WebElement password = driver.findElement(By.id("password"));
        sendText(password, "SuperSecretPassword!");

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();

        String currentURL = driver.getCurrentUrl();
        WebElement logoutBtn = driver.findElement(By.cssSelector(".icon-signout"));

        assertTrue(currentURL.contains("secure"));
        assertTrue(logoutBtn.isDisplayed());

        driver.quit();
    }

    @Test
    public void dynamicLoadingTest() {
        WebElement dynamicLoadingLink = driver.findElement(By.xpath("//a[contains(text(), 'Dynamic Loading')]"));
        dynamicLoadingLink.click();

        WebElement elementHiddenLink = driver.findElement(By.xpath("//a[@href='/dynamic_loading/1']"));
        elementHiddenLink.click();

        WebElement startBtn = driver.findElement(By.xpath("//div[@id='start']//button"));
        startBtn.click();

        WebElement finishText = driver.findElement(By.id("finish"));
        wait.until(d -> finishText.isDisplayed());

        assertEquals("Hello World!", finishText.getText());

        driver.quit();
    }

    @Test
    public void dynamicWaitTest() {
        WebElement dynamicLoadingLink = driver.findElement(By.xpath("//a[contains(text(), 'Dynamic Loading')]"));
        dynamicLoadingLink.click();

        WebElement elementLoadingLink = driver.findElement(By.xpath("//a[@href='/dynamic_loading/2']"));
        elementLoadingLink.click();

        WebElement startBtn = driver.findElement(By.xpath("//div[@id='start']//button"));
        startBtn.click();

        fluentWait.until(elem -> elem.findElement(By.id("finish"))).isDisplayed();
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement finishText = driver.findElement(By.id("finish"));

        assertEquals("Hello World!", finishText.getText());

        driver.quit();

        var baseUrl = "";
        var tableId = "table1";
        var action = "edit";
        var lastName = "bach";
        var linkTextOnPage = "Sortable Data Table";

        makeActionWithRowByLastName(lastName, action, driver.findElement(By.id(tableId)));
    }

    @Test
    public void fileUpload() {
        WebElement elementLoadingLink = driver.findElement(By.xpath("//a[@href='/upload']"));
        elementLoadingLink.click();

        WebElement fileUpload = driver.findElement(By.id("file-upload"));
        String path = "C:\\Users\\SIoan\\Desktop\\stuff\\manusi\\pozaBuletin.jpg";
        fileUpload.sendKeys(path);

        WebElement fileSubmit = driver.findElement(By.id("file-submit"));
        fileSubmit.click();

        WebElement finishText = driver.findElement(By.id("uploaded-files"));
        wait.until(d -> finishText.isDisplayed());

        assertTrue(finishText.getText().contains("poza"));

        driver.quit();
    }

    @Test
    public void fileDownload() {
        WebElement elementLoadingLink = driver.findElement(By.xpath("//a[@href='/download']"));
        elementLoadingLink.click();

        WebElement fileDownload = driver.findElement(By.linkText("file.json"));
        fileDownload.click();

        driver.quit();
    }

    @Test
    public void jsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement elementLoadingLink = driver.findElement(By.xpath("//a[@href='/download']"));
        js.executeScript("arguments[0].click()", elementLoadingLink);
        String fileName = (String) js.executeScript("return document.getElementById('file-upload').files[0].name");

        WebElement fileDownload = driver.findElement(By.linkText("file.json"));
        fileDownload.click();

        driver.quit();
    }

    @Test
    public void cookies() {
        Set<Cookie> allCookies = driver.manage().getCookies();
        Cookie myCookie = new Cookie("name", "value");
        driver.manage().addCookie(myCookie);
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("cookieName");
        driver.manage().deleteCookie(myCookie);
    }

    @Test
    public void iFrameTest() {
        driver.switchTo().frame("id");
        driver.switchTo().defaultContent();
        driver.quit();
    }

    @Test
    public void alertsTest() {
        Alert confirmationAlert = driver.switchTo().alert();
        confirmationAlert.accept();
        confirmationAlert.getText();
        driver.quit();
    }

    private void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    private void makeActionWithRowByLastName(String lastName, String action, WebElement table) {
        findRowByLastName(lastName, table)
                .findElement(By.linkText(action))
                .click();
    }

    private WebElement findRowByLastName(String lastName, WebElement table) {
        return table
                .findElements(By.tagName("tr"))
                .stream()
                .filter(row -> matchesLastName(row, lastName))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No row found with last name: " + lastName));

    }

    private boolean matchesLastName(WebElement row, String lastName) {
        return row.findElements(By.tagName("td"))
                .stream()
                .anyMatch(el -> el.getText().equals(lastName));
    }
}
