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

    // menu vertical de la page PageDetailProjet
    @FindBy(xpath = "//td[contains(text(),'Planification de projet')]")
    WebElement menuPlanification;
    @FindBy(xpath = "//td[contains(text(),'Détail du projet')]")
    WebElement menuDetai;
    @FindBy(xpath = "//td[contains(text(),'Chargement des ressources')]")
    WebElement menuChargement;
    @FindBy(xpath = "//td[contains(text(),'Allocation avancée')]")
    WebElement menuAllocation;
    @FindBy(xpath = "//td[contains(text(),'Tableau de bord')]")
    WebElement menuTableau;

   // menu horizontal de la page PageDetailProjet

  
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Données générales']")
   WebElement menuDonneesGenerales;
   @FindBy(xpath = "//span[.=\"WBS (tâches)\"]/ancestor::ul//span[.=\"Coût\"]")
   WebElement menuCout1;
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Avancement']")
   WebElement menuAvancement;
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Libellés']")
   WebElement menuLibelles;
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Exigence de critère']")
   WebElement menuExigences;
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Matériels']")
   WebElement menuMateriels;
   @FindBy(xpath = "//span[.='WBS (tâches)'']/ancestor::ul//span[.='Formulaires qualité des tâches']")
   WebElement menuFormulaire;
   @FindBy(xpath ="//span[.='WBS (tâches)'']/ancestor::ul//span[.='Autorisation']")
   WebElement menuAutorisation;


    @FindBy(partialLinkText = "Déconnexion")
    WebElement btnLogout;

    protected Actions actions;
    private WebDriverWait wait;

    public PageParticipants clickMenuRessourcesParticipants(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

    public PageListeProjets clickMenuCalendierProjet(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Instancier actions
        actions = new Actions(driver);
        // Se déplacer sur le bouton
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
        wait.until(ExpectedConditions.elementToBeClickable(menuCalendrier));
        actions.moveToElement(menuCalendrier).build().perform();
        // Attendre que le bouton soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(optionProjetMenuCalendrier));
        // Cliquer sur le bouton
        optionProjetMenuCalendrier.click();
        // Instancier la nouvelle page "ListesProjets"
        PageListeProjets pageListeProjets = PageFactory.initElements(driver, PageListeProjets.class);
        // Attendre que le menu "Liste des projets" soit affiché
        wait.until(ExpectedConditions.elementToBeClickable(pageListeProjets.btnCreerProjet));

        return pageListeProjets;
    }

    public boolean isLoggedIn() {
        if (btnLogout.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
