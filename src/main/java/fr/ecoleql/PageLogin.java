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
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        fieldUsername.clear();
        fieldUsername.sendKeys(username);
        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        btnLogin.click();
        PageIndex pageIndex = PageFactory.initElements(driver, PageIndex.class);
        wait.until(ExpectedConditions.elementToBeClickable(pageIndex.btnLogout));
        return pageIndex;
    }

}
