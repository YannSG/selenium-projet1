package fr.ecoleql;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebPage {

    // Checkbox
    public static void setCheckbox(WebElement webElement, boolean bool) {
        if (webElement.isSelected()) {
            if (bool == true) {
            } else {
                webElement.click();
            }
        } else {
            if (bool == false) {
            } else {
                webElement.click();
            }
        }
    }

    // Menu
    public Select getSelect(WebElement webElement) {
        Select select = new Select(webElement);
        return select;
    }

    public List<WebElement> getMenuSelectedOptions(WebElement webElement) {
        List<WebElement> list = getSelect(webElement).getAllSelectedOptions();
        return list;
    }

}
