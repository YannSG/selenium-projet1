package fr.ecoleql;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin extends MainMenu {

    @FindBy(id = "textfield")
    WebElement fieldUsername;
    @FindBy(id = "textfield2")
    WebElement fieldPassword;
    @FindBy(id = "button")
    WebElement btnLogin;

    public PageIndex login(WebDriver driver, String username, String password) {
        fieldUsername.clear();
        fieldUsername.sendKeys(username);
        fieldPassword.clear();
        fieldPassword.sendKeys(password);
        btnLogin.click();
        return PageFactory.initElements(driver, PageIndex.class);
    }

}
