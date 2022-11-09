package fr.ecoleql;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageCritere extends MainMenu {

    WebDriverWait wait;

    @FindBy(xpath = "//td[normalize-space()='Créer']")
    WebElement btnCreer;
    
}
