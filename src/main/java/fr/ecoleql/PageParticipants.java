package fr.ecoleql;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageParticipants extends MainMenu {

    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Surnom')]")
    WebElement txtColSurnom;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Prénom')]")
    WebElement txtColPrenom;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'ID')]")
    WebElement txtColID;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Code')]")
    WebElement txtColCode;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'En file')]")
    WebElement txtColEnFile;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Opérations')]")
    WebElement txtColOperations;
    @FindBy(xpath = "//span[text()='Filtré par']//following::input[1]")
    WebElement fieldFiltrePar;
    @FindBy(xpath = "//span[text()='Détails personnels']//following::input[1]")
    WebElement fieldDetailPersonnels;
    @FindBy(xpath = "//td[contains(text(),'Plus d')][contains(text(),'options')]")
    WebElement btnPlusDOptions;
    @FindBy(xpath = "//span[text()='Période active depuis']//following::input[1]")
    WebElement dateDebutPeriode;
    @FindBy(xpath = "//span[text()='à']//following::input[1]")
    WebElement dateFinPeriode;
    @FindBy(xpath = "//select[@selectedindex='0']")
    WebElement menuType;
    @FindBy(xpath = "//td[text()='Filtre']")
    WebElement btnFiltre;
    @FindBy(xpath = "//div[@class='z-window-embedded'][1]//td[text()='Créer']")
    WebElement btnCreer;
    @FindBy(xpath = "//span[text()='Participant enregistré']")
    WebElement txtMsgValidationCreation;
    @FindBy(xpath = "//span[text()='Travailleur et utilisateur lié supprimés']")
    WebElement txtMsgValidationSuppression;
    
    public List<WebElement> getMenuType(WebDriver driver) {
        return getMenuSelectedOptions(menuType);
    }

    public void setMenuType(WebDriver driver, String string) {
        getSelect(menuType).selectByVisibleText(string);
    }

    public PageCreerParticipant clickBtnCreer(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Cliquer sur le bouton créer
        btnCreer.click();
        // Instancier la nouvelle page
        PageCreerParticipant pageCreerParticipant = PageFactory.initElements(driver, PageCreerParticipant.class);
        // Attendre que le bouton de déconnexion soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageCreerParticipant.btnEnregistrer));

        return pageCreerParticipant;
    }

    public void clickBtnPlusDOptions(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Cliquer sur le bouton "plus d'options"
        btnPlusDOptions.click();
        wait.until(ExpectedConditions.elementToBeClickable(menuType));
    }

    public void deleteParticipant(WebDriver driver, String ID, boolean utilisateurLie) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Récupérer la ligne du tableau correspondant à l'ID de l'utilisateur
        int i = getTableLineByContent(driver, true, ID);
        // Cliquer sur le bouton de suppression de cet utilisateur
        WebElement webElement = driver.findElement(By.xpath(
                "//div[@class='clickable-rows z-grid']//table//tbody[@class='z-rows']/tr[" + i
                        + "]//img[@src='/libreplan/common/img/ico_borrar1.png']"));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        // Valider la popup
        wait.until(ExpectedConditions.elementToBeClickable(btnOKPopUp));
        btnOKPopUp.click();
        if (utilisateurLie == true) {
            wait.until(ExpectedConditions.elementToBeClickable(btnOuiPopUp));
            btnOuiPopUp.click();
        }
    }

    public void filter(WebDriver driver, String nom) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Filtrer
        fieldDetailPersonnels.sendKeys(nom);
        btnFiltre.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnCreer));
    }

    public boolean verifID(WebDriver driver, String ID) {
        if (getTableLineByContent(driver, true, ID) - 1 < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verifNom(WebDriver driver, String nom) {
        if (getTableLineByContent(driver, true, nom) - 1 < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verifPrenom(WebDriver driver, String prenom) {
        if (getTableLineByContent(driver, true, prenom) - 1 < 0) {
            return false;
        } else {
            return true;
        }
    }

}
