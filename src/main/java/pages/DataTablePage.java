package pages;

import components.TableActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DataTablePage {

    private final WebDriver driver;
    private final TableActions tableActions = new TableActions();
    @FindBy(xpath = "//table[@class='tablesorter']")
    private WebElement table;


    public DataTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickRowEditDeleteOption(String option, String lastName) {
        tableActions.clickRowOptionByLastName(table, option, lastName);
    }
}
