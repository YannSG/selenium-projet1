package fr.ecoleql;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageListesProjet extends MainMenu {

    WebDriverWait wait;

    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_add.png']")
    public WebElement btnCreerProjet;
    @FindBy(xpath = "//span[contains(text(),'supprimé')]")
    WebElement txtMsgValidationSuppression;

    public void deleteProjet(WebDriver driver, String code) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Récupérer la ligne du tableau correspondant au code du projet
        int i = getTableLineByContent(driver, true, code);
        // Cliquer sur le bouton de suppression de ce projet
        WebElement webElement = driver.findElement(By.xpath(
                "//table//tbody[@class='z-rows']/tr[" + i + "]//img[@src='/libreplan/common/img/ico_borrar1.png']"));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        // Valider la popup
        wait.until(ExpectedConditions.elementToBeClickable(btnOKPopUp));
        btnOKPopUp.click();
    }

}
