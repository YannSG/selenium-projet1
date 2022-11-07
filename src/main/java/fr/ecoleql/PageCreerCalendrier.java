package fr.ecoleql;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageCreerCalendrier {


    @FindBy(xpath = "//*[@class=\"z-button-cm\"]")
    WebElement creerCalendriers;

    @FindBy(xpath = "//*[@class=\"z-textbox z-textbox-text-invalid\"]")
    WebElement nomCalendriers;

    @FindBy(xpath = "//span[contains(text(),'Date de début')]//following::input[1]")
    WebElement dateDeDebut;

    @FindBy(xpath = "//span[contains(text(),'Date de fin')]//following::input[1]")
    WebElement dateDeFin;

    @FindBy(xpath = "")

 
    public PageCreerCalendrier() {
        creerCalendriers.click();

        nomCalendriers.clear();

        nomCalendriers.sendKeys();
        
    }

    
    
    



  

    
    
}
