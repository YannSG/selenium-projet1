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

    // @FindBy(id = "xVzP48")
    @FindBy(xpath = "//span[.='Nom']/following::input[1]")
    // @FindBy(xpath="[@class='z-textbox z-textbox-text-invalid']")
    WebElement fieldNom;

    // @FindBy(id = "xVzPk8-real")
    @FindBy(xpath = "//label[.='Générer le code']/preceding-sibling::input[@type='checkbox']")
    WebElement checkboxGenererCode;

    // @FindBy(id = "xVzPj8")
    // @FindBy(xpath="[@class='z-textbox z-textbox-text-invalid']")
    @FindBy(xpath = "//span[.='Code']/following::input[1]")
    WebElement fieldCode;

    /* @FindBy(id = "xVzPj8") */
    @FindBy(xpath = "//span[.='Date de début']/following::input[1]")
    WebElement fieldDate;

    // @FindBy(id = "xVzPq8-real")
    @FindBy(xpath = "//span[.='Echéance']/following::input[1]")
    WebElement fieldEcheance;

    // @FindBy(id = "xVzPha-real")
    @FindBy(xpath = "//div[@class='z-window-modal-cnt-noborder']//input[@class='z-combobox-inp']")
    WebElement comboboxCalendrier;

    @FindBy(xpath = "//td[contains(text(),'Accepter')]")
    WebElement buttonAccepter;

    WebDriverWait wait;

    // String dateAujourdhui;

    // c'est pour la date d'aaujourd'hui
    static LocalDateTime now = LocalDateTime.now();
    // Definir le format de la date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_add.png']")
    public WebElement buttonCreerProjet;

    
    public void clickIconCreerProjet(WebDriver driver) {
        buttonCreerProjet.click();
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

        return PageFactory.initElements(driver, PageProjet.class);

    }

}
