package fr.ecoleql;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageListesProjet extends MainMenu {

    @FindBy(partialLinkText = "//td[contains(text(),'Liste des projets')]")
    WebElement list_projet;
}
