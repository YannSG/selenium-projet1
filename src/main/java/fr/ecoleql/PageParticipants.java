package fr.ecoleql;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageParticipants extends MainMenu {

    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Surnom')]")
    private WebElement txtColSurnom;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Prénom')]")
    private WebElement txtColPrenom;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'ID')]")
    private WebElement txtColID;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Code')]")
    private WebElement txtColCode;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'En file')]")
    private WebElement txtColEnFile;
    @FindBy(xpath = "//div[@class='clickable-rows z-grid']//div[contains(text(),'Opérations')]")
    private WebElement txtColOperations;

    @FindBy(xpath = "//span[contains(text(),'Filtré par')]//following::input[1]")
    private WebElement fieldFiltrePar;
    @FindBy(xpath = "//span[contains(text(),'Détails personnels')]//following::input[1]")
    private WebElement fieldDetailPersonnels;

    @FindBy(xpath = "//td[contains(text(),'Plus d')][contains(text(),'options')]")
    private WebElement btnPlusDOptions;
    @FindBy(xpath = "//td[contains(text(),'Filtre')]")
    private WebElement btnFiltre;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/table[1]/tbody[1]/tr[2]/td[2]")
    private WebElement btnCreer;

    public void creerParticipant() {
        btnCreer.click();
    }

    public boolean pageElementsArePresents() {
        if (txtColSurnom.isDisplayed()
                && txtColPrenom.isDisplayed()
                && txtColID.isDisplayed()
                && txtColCode.isDisplayed()
                && txtColEnFile.isDisplayed()
                && txtColOperations.isDisplayed()
                && fieldFiltrePar.isDisplayed()
                && fieldDetailPersonnels.isDisplayed()
                && btnPlusDOptions.isDisplayed()
                && btnFiltre.isDisplayed()
                && btnCreer.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

}
