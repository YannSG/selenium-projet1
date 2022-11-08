package fr.ecoleql;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCreerParticipant extends MainMenu {

    WebDriverWait wait;

    @FindBy(xpath = "//span[contains(text(),'Données personnelles')]")
    WebElement tabDonnesPersonnelles;
    @FindBy(xpath = "//span[contains(text(),'Données de base')]")
    WebElement txtDonneesDeBase;
    @FindBy(xpath = "//fieldset//span[text()='Code']//following::input[1]")
    WebElement fieldCode;
    @FindBy(xpath = "//fieldset//span[text()='Code']//following::input[2]")
    WebElement chkCode;
    @FindBy(xpath = "//fieldset//span[text()='Prénom']//following::input[1]")
    WebElement fieldPrenom;
    @FindBy(xpath = "//fieldset//span[text()='Nom']//following::input[1]")
    WebElement fieldNom;
    @FindBy(xpath = "//fieldset//span[text()='ID']//following::input[1]")
    WebElement fieldID;
    @FindBy(xpath = "//fieldset//span[text()='Type']//following::select[1]")
    WebElement menuType;
    @FindBy(xpath = "//label[text()='Non lié']/../input[@type='radio']")
    WebElement radioUtilisateurLieNonLié;
    @FindBy(xpath = "//label[text()='Utilisateur existant']/../input[@type='radio']")
    WebElement radioUtilisateurLieUtilisateurExistant;
    @FindBy(xpath = "//label[text()='Créer un nouvel utilisateur']/../input[@type='radio']")
    WebElement radioUtilisateurLieCreerUnNouvelUtilisateur;
    @FindBy(xpath = "//span[contains(text(),'Nom')][contains(text(),'utilisateur')]//ancestor::tr//input")
    WebElement fieldNomUtilisateur;
    @FindBy(xpath = "//span[text()='Mot de passe']//ancestor::tr//input")
    WebElement fieldMDP;
    @FindBy(xpath = "//span[text()='Confirmation du mot de passe']//ancestor::tr//input")
    WebElement fieldConfirmMDP;
    @FindBy(xpath = "//span[text()='Email']//ancestor::tr//input")
    WebElement fieldEmail;
    @FindBy(xpath = "//td[text()='Enregistrer']")
    WebElement btnEnregistrer;
    @FindBy(xpath = "//td[text()='Sauver et continuer']")
    WebElement btnSauverEtContinuer;
    @FindBy(xpath = "//td[text()='Annuler']")
    WebElement btnAnnuler;

    public List<WebElement> getMenuType(WebDriver driver) {
        return getMenuSelectedOptions(menuType);
    }

    public void setMenuType(WebDriver driver, String string) {
        getSelect(menuType).selectByVisibleText(string);
    }

    public void saisirDonneesDeBase(WebDriver driver, String prenom, String nom, String ID) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Renseigner les informations du formulaire
        fieldPrenom.sendKeys(prenom);
        fieldNom.sendKeys(nom);
        fieldID.sendKeys(ID);
    }

    public void creerNouvelUtilisateur(WebDriver driver, String nom, String mdp, String mail) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Renseigner les informations du formulaire
        fieldNomUtilisateur.sendKeys(nom);
    }

    public PageParticipants enregistrer(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Instancier la nouvelle page
        PageParticipants pageParticipants = PageFactory.initElements(driver,
                PageParticipants.class);
        // Attendre que le bouton de création de participant soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageParticipants.btnCreer));

        return pageParticipants;
    }

}
