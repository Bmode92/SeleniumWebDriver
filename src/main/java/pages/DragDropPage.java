package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragDropPage {

    @FindBy(id = "column-a")
    private WebElement sourceElement;
    @FindBy(id = "column-b")
    private WebElement targetElement;

    private WebDriver driver;
    public DragDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dragElementToPosition() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement)
                .perform();
    }

    public String getTargetText() {
        return targetElement.getText();
    }
}
