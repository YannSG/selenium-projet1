package fr.ecoleql;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailDuProjet extends MainMenu {

    WebDriverWait wait;

    @FindBy(xpath = "//span[.='WBS (tâches)']")
    WebElement menuWbs;

    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_back.png']")
    WebElement buttonAnnulation;

    @FindBy(xpath = "//img[@src='/libreplan/common/img/imgico_save.png']")
    WebElement buttonEnregistrement;

    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='OK']")
    WebElement buttonOKPopUp;

    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='Annuler']")
    WebElement buttonAnnulerPopUp;

    @FindBy(xpath = "//span[.='Les modifications non enregistrées seront perdues. Êtes-vous sûr ?']")
    WebElement textPopUp;

    String textPopUpAVerifier = "Les modifications non enregistrées seront perdues. Êtes-vous sûr ?";

    

        // Cliquer sur le bouton "annuler edition projet"
    public void clikButtonAnnulerEditionProjet(WebDriver driver) {
        buttonAnnulation.click();
    }

        // Cliquer sur le bouton annuler de "PoupUp"
    public void clikButtonAnnulerPopUp(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(buttonAnnulerPopUp));
        buttonAnnulerPopUp.click();
    }

         // Cliquer sur le bouton OK de "PoupUp"
    public PageIndex clikButtonOkPopUp (WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOKPopUp));
        buttonOKPopUp.click();
         // Instanciation de la page : PageIndex
         PageIndex pageIndex = PageFactory.initElements(driver,PageIndex.class);
         // Attendre que le bouton "menuWbs" soit cliquable
         wait = new WebDriverWait(driver, Duration.ofSeconds(3));
         wait.until(ExpectedConditions.visibilityOf(PageIndex.buttonPlanificationProjet));
         return pageIndex;
        
    }

    

}
