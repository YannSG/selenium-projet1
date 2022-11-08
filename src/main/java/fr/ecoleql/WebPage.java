package fr.ecoleql;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WebPage {

    @FindBy(xpath = "//div[@class=\"z-window-embedded-header\"]")
    WebElement webPageTitle;
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='OK']")
    WebElement btnOKPopUp;
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='Annuler']")
    WebElement btnAnnulerPopUp;
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='Oui']")
    WebElement btnOuiPopUp;
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='Non']")
    WebElement btnNonPopUp;

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

    // Titre
    public String getWebPageTitle() {
        return webPageTitle.getText();
    }

    @SuppressWarnings("null")
    //// tr[@class='clickable-rows z-row'][1]//td[@class='z-row-inner'][1]
    public int getTableLineByContent(WebDriver driver, boolean equals, String string) {
        int i = 1;
        List<WebElement> nRow = driver
                .findElements(By.xpath("//div[@class='clickable-rows z-grid']//table//tbody[@class='z-rows']/tr"));
        for (WebElement row : nRow) {
            List<WebElement> l_cell = row.findElements(By.xpath("td"));
            for (WebElement cell : l_cell) {
                if (equals == true) {
                    if (cell.getText().equals(string)) {
                        return i;
                    }
                } else {
                    if (cell.getText().contains(string)) {
                        return i;
                    }
                }
            }
            i++;
        }
        return (Integer) null;
    }

}
