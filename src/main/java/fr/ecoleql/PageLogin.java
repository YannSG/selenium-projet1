package fr.ecoleql;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLogin extends MainMenu {

    WebDriverWait wait;

    @FindBy(id = "textfield")
    WebElement fieldUsername;
    @FindBy(id = "textfield2")
    WebElement fieldPassword;
    @FindBy(id = "button")
    WebElement btnLogin;

    public PageIndex login(WebDriver driver, String username, String password) {
        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Entrer le username
        fieldUsername.clear();
        fieldUsername.sendKeys(username);
        // Entrer le mot de passe
        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        // Valider
        btnLogin.click();
        // Instancier la nouvelle page
        PageIndex pageIndex = PageFactory.initElements(driver, PageIndex.class);
        // Attendre que le bouton de déconnexion soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(pageIndex.btnLogout));

        return pageIndex;
    }

}
