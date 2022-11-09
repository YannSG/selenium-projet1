package fr.ecoleql;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageListesProjet extends MainMenu {

    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_add.png']")
    public WebElement btnCreerProjet;

}
