package fr.ecoleql;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageProjet {


    WebDriverWait wait;  

    @FindBy(xpath = "//span[.='WBS (tâches)']") WebElement menuWbs;
     
   
    @FindBy(xpath = "//img[@src='/libreplan/common/img/ico_back.png']")
    WebElement buttonAnnulation;

   
    @FindBy(xpath = "//img[@src='/libreplan/common/img/imgico_save.png']")
    WebElement buttonEnregistrement;

    
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='OK']")
    WebElement buttonOKPopUp;

    
    @FindBy(xpath = "//div[@class='z-window-modal-cl']//td[text()='Annuler']")
    WebElement buttonAnnulerPopUp;

   
    @FindBy(xpath = "//span[.='Les modifications non enregistrées seront perdues. Êtes-vous sûr ?']")
    WebElement textPopUp;

    String  textPopUpAVerifier="Les modifications non enregistrées seront perdues. Êtes-vous sûr ?";



    //Creation de la methode : cliquer sur le bouton annuler edition projet
    public void clikButtonAnnulerEditionProjet( WebDriver driver){
        buttonAnnulation.click();
    }

    //Creation de la methode cliquer sur le bouton annuler de PoupUp
    public void clikButtonAnnulerPopUp(WebDriver driver){
        // driver.switchTo().frame("oG7Qxa");
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(buttonAnnulerPopUp));
        buttonAnnulerPopUp.click();
    }

    //Creation de la methode cliquer sur le bouton OK de PoupUp
    public void clikButtonOkPopUp(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOKPopUp));
        buttonOKPopUp.click();
    }
    
    // Verifier la creation d'un projet
   // public void ClikOngletCalendrier (WebDriver deriver){



  //  }



}
