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

    WebDriverWait wait;

    // Récupérer la date présente
    static LocalDateTime now = LocalDateTime.now();
    // Définir le format de la date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void clickIconCreerProjet(WebDriver driver) {
        buttonCreerProjet.click();
        wait.until(ExpectedConditions.elementToBeClickable(buttonAccepter));
        // return PageFactory.initElements(driver, PageProjet.class);
    }

    public PageProjet creerProjet(WebDriver driver, String Nom, String valeurCode) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        /* suitcher ver frame id = "z_ddstkup" */
        // driver.switchTo().frame("z_ddstkup");
        // ecrire le nom
        fieldNom.clear();
        fieldNom.sendKeys(Nom);
        // Decocher la case Générer le code et ecrire le code
        WebPage.setCheckbox(checkboxGenererCode, false);
        wait.until(ExpectedConditions.elementToBeClickable(fieldCode));
        fieldCode.clear();
        fieldCode.sendKeys(valeurCode);

        // Mettre la date d'aujourdhui
        // dateAujourdhui.sendKeys(now.format(formatter));

        // Mettre la date de debut de projet
        fieldDate.clear();
        fieldDate.sendKeys(now.plusDays(5).format(formatter));
        // Mettre l'echeance:
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

        // Instancier la nouvelle page
        PageProjet pageProjet = PageFactory.initElements(driver, PageProjet.class);
        // Attendre que le bouton soit cliquable
        wait.until(ExpectedConditions.visibilityOf(pageProjet.menuWbs));

        return pageProjet;
    }

}
