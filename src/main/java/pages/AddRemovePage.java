package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddRemovePage {
    private WebDriver driver;
    @FindBy(xpath = "//div[@class]/button")
    private WebElement addElem;
    @FindBy(id = "elements")
    private WebElement elements;

    public AddRemovePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addElement(int elementsToAdd) {
        for (int i = 0; i < elementsToAdd; i++) {
            addElem.click();
        }
    }

    public int getNrOfElementsPresent() {
        return elements.findElements(By.xpath("//button[@class['added-manually']]")).size();
    }
}
