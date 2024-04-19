package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;
    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginPageLink;
    @FindBy(xpath = "//a[@href='/tables']")
    private WebElement dataTableLink;
    @FindBy(xpath = "//a[@href='/windows']")
    private WebElement multipleWindowsLink;
    @FindBy(xpath = "//a[@href='/frames']")
    private WebElement framesLink;
    @FindBy(xpath = "//a[@href='/drag_and_drop']")
    private WebElement dragDropLink;
    @FindBy(xpath = "//a[@href='/add_remove_elements/']")
    private WebElement addRemoveLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage openLoginPage() {
        loginPageLink.click();
        return new LoginPage(driver);
    }

    public MultipleWindowsPage openMultipleWindowsPage() {
        multipleWindowsLink.click();
        return new MultipleWindowsPage(driver);
    }

    public DataTablePage openDataTablePage() {
        dataTableLink.click();
        return new DataTablePage(driver);
    }

    public FramePage openFramePage() {
        framesLink.click();
        return new FramePage(driver);
    }

    public DragDropPage openDragDropPage() {
        dragDropLink.click();
        return new DragDropPage(driver);
    }

    public AddRemovePage openAddRemovePage() {
        addRemoveLink.click();
        return new AddRemovePage(driver);
    }

    public HomePage open() {
        driver.get("https://the-internet.herokuapp.com");
        return this;
    }
}
