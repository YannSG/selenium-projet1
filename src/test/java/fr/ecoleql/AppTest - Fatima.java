package fr.ecoleql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import fr.ecoleql.utilities.Browser;
import fr.ecoleql.utilities.Driver;
import fr.ecoleql.utilities.Reporting;

public class AppTest {

    private Properties params;
    private WebDriver driver;
    private List<Error> errors = new ArrayList<>();

    PageIndex pageIndex;
    WebDriverWait wait;

    @Before
    public void before() throws Exception {
        driver = Driver.getDriver(Browser.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        params = new Properties();
        params.load(new FileInputStream("src/main/resources/JDD/params.properties"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // Instancier la page login
        driver.get(params.getProperty("baseUrl"));
        PageLogin pageLogin = PageFactory.initElements(driver, PageLogin.class);
        // Se connecter et accéder à la page index
        pageIndex = pageLogin.login(driver, params.getProperty("username"), params.getProperty("password"));
        // Vérifier le titre de la page
        try {
            assertEquals("Accès à la page index", "LibrePlan: Calendrier", driver.getTitle());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    // @Test pour le cas projet et taches
    @Test
    public void PRO_TA_01() throws Exception {

        // Appel à la methode clickIconCreerProjet dans PageIndex
        pageIndex.clickIconCreerProjet(driver);
        wait.until(ExpectedConditions.elementToBeClickable(pageIndex.buttonAccepter));

        //Appel à la methode "creerProjet" et Instanciation de la PageProjet
        PageProjet pageprojet =  pageIndex.creerProjet(driver, "PROJET_TEST1", "PRJTEST001");
        wait.until(ExpectedConditions.visibilityOf(pageprojet.menuWbs));

        //Verification de la creation du projet: (Titre de l'oglet horizontal Menue WBS ="WBS (tâches)")
        try {
            assertEquals("WBS (tâches)", pageprojet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }

        
        
        //Appel à la methode cliquer sur le bouton d'annulation d'edition (pour tester le bouton annuler)
        pageprojet.clikButtonAnnulerEditionProjet(driver);

        //Verifier que la PopUp est bien apparue
        try {
            assertEquals(pageprojet.textPopUpAVerifier, pageprojet.textPopUp);
                } catch (AssertionError e) {
                  errors.add(e);
                }

         //Appel à la methode clique sur le bouton annuler de PopUp
        pageprojet.clikButtonAnnulerPopUp(driver);

         //Verification de la creation du projet existe toujour
         try {
            assertEquals("WBS (tâches)", pageprojet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Rappeler la methode cliquer sur le bouton d'annulation d'edition (pour tester le bouton ok) 
        pageprojet.clikButtonAnnulerEditionProjet(driver);

        //Verifier que la PopUp est bien apparue
        try {
            assertEquals(pageprojet.textPopUpAVerifier, pageprojet.textPopUp);
                } catch (AssertionError e) {
                  errors.add(e);
                }

         //Appel à la methode clique sur le bouton OK de PopUp
        pageprojet.clikButtonOkPopUp(driver);

         //Verification de la creation du projet est bien annulé (Absence des element WBS de menu)
         try {
            assertFalse("le menue WBS est present", pageprojet.menuWbs.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
        }

        
            

       
        

       

        // try {
        // assertTrue("La vérité", false);
        // } catch (AssertionError e) {
        // errors.add(e);
        // Reporting.takeScreenShot(driver, "Ceci est vrai");
        // }
    }

    @After
    public void after() throws Exception {
       // driver.quit();
        Reporting.writeReport(errors);
    }

}