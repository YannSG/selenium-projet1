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
    @FindBy(xpath = "//a[@class='z-menu-item-cnt'][contains(normalize-space(),'Projets')]")
    WebElement optionProjetMenuCalendrier;

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

    protected Actions actions;
    private WebDriverWait wait;

    public PageParticipants clickMenuRessourcesParticipants(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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

    /*// creation d'une methode qui fait cliquer sur l'option projet du menu
    // Calendrier + instanciation de la page Listes Projets
    public ListesProjet clickMenuCalendierProjet(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //System.out.println("wait OK");
        // Instancier actions
        actions = new Actions(driver);
        //System.out.println("action OK");
        // Se déplacer sur le bouton
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
       // System.out.println("menuRessources = "+menuRessources);
        //actions.moveToElement(menuRessources, 0, 0).perform();
        //System.out.println("menuCalendrier = "+menuCalendrier);
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
        actions.moveToElement(menuCalendrier).build().perform();
        //System.out.println("move to element");
        // Attendre que le bouton soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(optionProjetMenuCalendrier));
        // Cliquer sur le bouton
        optionProjetMenuCalendrier.click();
        // Instancier la nouvelle page "ListesProjets"
        ListesProjet listeProjets = PageFactory.initElements(driver, ListesProjet.class);
        // Attendre que le menu "Liste des projets" soit affiché
       // wait.until(ExpectedConditions.visibilityOf(listeProjets.list_projet));

        return listeProjets;
    }*/

    public boolean isLoggedIn() {
        if (btnLogout.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
