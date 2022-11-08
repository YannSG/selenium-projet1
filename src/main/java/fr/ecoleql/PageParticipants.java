package fr.ecoleql;

import java.time.Duration;

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
    @FindBy(xpath = "//span[contains(text(),'Filtré par')]//following::input[1]")
    WebElement fieldFiltrePar;
    @FindBy(xpath = "//span[contains(text(),'Détails personnels')]//following::input[1]")
    WebElement fieldDetailPersonnels;
    @FindBy(xpath = "//td[contains(text(),'Plus d')][contains(text(),'options')]")
    WebElement btnPlusDOptions;
    @FindBy(xpath = "//td[contains(text(),'Filtre')]")
    WebElement btnFiltre;
    @FindBy(xpath = "//div[@class='z-window-embedded'][1]//td[text()='Créer']")
    WebElement btnCreer;

    public PageCreerParticipant clickBtnCreer(WebDriver driver) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        btnCreer.click();
        // Instancier la nouvelle page
        PageCreerParticipant pageCreerParticipant = PageFactory.initElements(driver, PageCreerParticipant.class);
        // Attendre que le bouton de déconnexion soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageCreerParticipant.btnEnregistrer));

        return pageCreerParticipant;
    }

}
