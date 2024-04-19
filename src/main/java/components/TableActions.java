package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableActions {

    public WebElement findRowByLastName(String lastName, WebElement table) {
       return table.findElements(By.tagName("tr"))
               .stream()
               .filter(row -> row.findElements(By.tagName("td"))
                       .stream().anyMatch(el -> el.getText().equals(lastName)))
               .findFirst()
               .orElseThrow();
    }

    public void clickRowOptionByLastName(WebElement  table, String option, String lastName) {
        findRowByLastName(lastName, table)
                .findElements(By.linkText(option));
    }
}
