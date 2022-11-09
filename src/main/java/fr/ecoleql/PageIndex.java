package fr.ecoleql;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageIndex extends MainMenu {

    @FindBy(xpath = "//span[.='Nom']/following::input[1]")
    WebElement fieldNom;
    @FindBy(xpath = "//label[.='Générer le code']/preceding-sibling::input[@type='checkbox']")
    WebElement checkboxGenererCode;
    @FindBy(xpath = "//span[.='Code']/following::input[1]")
    WebElement fieldCode;
    @FindBy(xpath = "//span[.='Date de début']/following::input[1]")
    WebElement fieldDate;
    @FindBy(xpath = "//span[.='Echéance']/following::input[1]")
    WebElement fieldEcheance;
    @FindBy(xpath = "//div[@class='z-window-modal-cnt-noborder']//input[@class='z-combobox-inp']")
    WebElement comboboxCalendrier;
    @FindBy(xpath = "//td[contains(text(),'Accepter')]")
    WebElement buttonAccepter;
    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_add.png']")
    public WebElement buttonCreerProjet;
    @FindBy(xpath = "//td[contains(text(),'Planification des projets')]")
    public WebElement btnPlanificationProjet;
    @FindBy(xpath = "//td[contains(text(),'Liste des projets')]")
    public WebElement btnListeProjets;

    WebDriverWait wait;

    static LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Cliquer sur l'icone "+" pour Accéder au formulaire de création d'un projet :
    public void clickIconCreerProjet(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        buttonCreerProjet.click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonAccepter));

    }

    // Créer un projet : Remplir les champs du modale et cliquer sur le Bouton
    // "Accepter"
    public PageDetailsProjet creerProjet(WebDriver driver, String Nom, String valeurCode) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // ecrire le nom
        fieldNom.clear();
        fieldNom.sendKeys(Nom);
        // Appel à la méthode setCheckbox pour verifier et decoucher le chekbox
        WebPage.setCheckbox(checkboxGenererCode, false);
        wait.until(ExpectedConditions.elementToBeClickable(fieldCode));
        fieldCode.clear();
        fieldCode.sendKeys(valeurCode);
        // Date debut projet = date d'aujourd'hui + 5 jours
        fieldDate.clear();
        fieldDate.sendKeys(now.plusDays(5).format(formatter));
        // Date Echeance = date d'aujourd'hui + 15 jours
        fieldEcheance.clear();
        fieldEcheance.sendKeys(now.plusDays(15).format(formatter));
        // Verifier si le champs Calendrier est egale à Default et Cliquer sur accepter
        if (comboboxCalendrier.getText() == "Default") {
            buttonAccepter.click();
        } else {
            comboboxCalendrier.clear();
            comboboxCalendrier.sendKeys("Default");
            buttonAccepter.click();
        }

        // Instanciation de la page : DetailDuProjet
        PageDetailsProjet detailDuProjet = PageFactory.initElements(driver, PageDetailsProjet.class);
        // Attendre que le bouton "menuWbs" soit cliquable
        wait.until(ExpectedConditions.visibilityOf(detailDuProjet.menuWbs));
        return detailDuProjet;
    }

}
