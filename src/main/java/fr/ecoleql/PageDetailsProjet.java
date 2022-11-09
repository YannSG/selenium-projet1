package fr.ecoleql;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageDetailsProjet extends PageListeProjets {

    WebDriverWait wait;

    @FindBy(xpath = "//img[@src='/libreplan/common/img/imgico_save.png']")
    WebElement btnEnregistrerProjet;
    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_back.png']")
    WebElement btnAnnulationEdition;
    @FindBy(xpath = "//span[.='Les modifications non enregistrées seront perdues. Êtes-vous sûr ?']")
    WebElement textPopUp;

    // Menu latéral vertical
    @FindBy(xpath = "//td[contains(text(),'Planification de projet')]")
    WebElement menuPlanificationDeProjet;
    @FindBy(xpath = "//td[contains(text(),'Détail du projet')]")
    WebElement menuDetailDuProjet;
    @FindBy(xpath = "//td[contains(text(),'Allocation avancée')]")
    WebElement menuAllocationAvancee;
    @FindBy(xpath = "//td[contains(text(),'Tableau de bord')]")
    WebElement menuTableauDeBord;

    // Onglets horizontaux
    @FindBy(xpath = "//span[text()='WBS (tâches)']")
    WebElement tabWbs;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Données générales']")
    WebElement tabDonneesGenerales;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Coût']")
    WebElement tabCout;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Avancement']")
    WebElement tabAvancement;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Libellés']")
    WebElement tabLibelles;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Exigence de critère']")
    WebElement tabExigences;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Matériels']")
    WebElement tabMateriels;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Formulaires qualité des tâches']")
    WebElement tabFormulaire;
    @FindBy(xpath = "//span[text()='WBS (tâches)']/ancestor::ul//span[text()='Autorisation']")
    WebElement tabAutorisation;

    // Creation de la methode : cliquer sur le bouton annuler edition projet
    public void clikButtonAnnulerEditionProjet(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Annuler
        wait.until(ExpectedConditions.elementToBeClickable(btnAnnulationEdition));
        btnAnnulationEdition.click();
    }

    // Creation de la methode cliquer sur le bouton annuler de PoupUp
    public void clikButtonAnnulerPopUp(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Annuler
        wait.until(ExpectedConditions.elementToBeClickable(btnAnnulerPopUp));
        btnAnnulerPopUp.click();
    }

    // Creation de la methode cliquer sur le bouton OK de PoupUp
    public PageIndex clikButtonOkPopUp(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Valider la popup
        wait.until(ExpectedConditions.elementToBeClickable(btnOKPopUp));
        btnOKPopUp.click();
        // Instancier la nouvelle page "ListesProjets"
        PageIndex pageIndex = PageFactory.initElements(driver, PageIndex.class);

        return pageIndex;
    }

}
