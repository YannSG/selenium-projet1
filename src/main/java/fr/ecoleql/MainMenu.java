package fr.ecoleql;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu extends WebPage {

    @FindBy(xpath = "//button[contains(text(),'Calendrier')]")
    WebElement menuCalendrier;

    @FindBy(xpath = "//button[contains(text(),'Ressources')]")
    WebElement menuRessources;
    @FindBy(xpath = "//a[@href='/libreplan/resources/worker/worker.zul']")
    WebElement menuRessourcesParticipant;

    @FindBy(xpath = "//button[contains(text(),'Coût')]")
    WebElement menuCout;
    @FindBy(xpath = "//button[contains(text(),'Configuration')]")
    WebElement menuConfiguration;
    @FindBy(xpath = "//button[contains(text(),'Communications')]")
    WebElement menuCommunications;
    @FindBy(xpath = "//button[contains(text(),'Rapports')]")
    WebElement menuRapports;
    @FindBy(xpath = "//button[contains(text(),'Zone personnelle')]")
    WebElement menuZonePersonnelle;

    @FindBy(partialLinkText = "Déconnexion")
    WebElement btnLogout;

    private Actions actions;

    public PageParticipants clickMenuRessourcesParticipants(WebDriver driver) {
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Instancier actions
        actions = new Actions(driver);
        // Se déplacer sur le bouton
        actions.moveToElement(menuRessources, 0, 0).perform();
        // Attendre que le bouton soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(menuRessourcesParticipant));
        // Cliquer sur le bouton
        menuRessourcesParticipant.click();
        // Instancier la nouvelle page
        PageParticipants pageParticipants = PageFactory.initElements(driver, PageParticipants.class);
        // Attendre que le bouton de création de participant soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageParticipants.btnCreer));

        return pageParticipants;
    }

    public boolean isLoggedIn() {
        if (btnLogout.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
