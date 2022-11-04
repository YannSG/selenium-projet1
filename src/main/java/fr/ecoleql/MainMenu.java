package fr.ecoleql;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class MainMenu extends WebPage {
    
    @FindBy(xpath = "//button[contains(text(),\"Calendrier\")]")
    WebElement menuCalendrier;
    @FindBy(xpath = "//button[contains(text(),\"Ressources\")]")
    WebElement menuRessources;
    @FindBy(xpath = "//button[contains(text(),\"Coût\")]")
    WebElement menuCout;
    @FindBy(xpath = "//button[contains(text(),\"Configuration\")]")
    WebElement menuConfiguration;
    @FindBy(xpath = "//button[contains(text(),\"Communications\")]")
    WebElement menuCommunications;
    @FindBy(xpath = "//button[contains(text(),\"Rapports\")]")
    WebElement menuRapports;
    @FindBy(xpath = "//button[contains(text(),\"Zone personnelle\")]")
    WebElement menuZonePersonnelle;

}
