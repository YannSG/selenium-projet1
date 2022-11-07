package fr.ecoleql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import fr.ecoleql.utilities.Browser;
import fr.ecoleql.utilities.Driver;
import fr.ecoleql.utilities.Reporting;

public class AppTest {

    private Properties params;
    private WebDriver driver;
    private List<Error> errors = new ArrayList<>();

    PageIndex pageIndex;

    @Before
    public void before() throws Exception {
        driver = Driver.getDriver(Browser.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        params = new Properties();
        params.load(new FileInputStream("src/main/resources/JDD/params.properties"));
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

    @Test
    public void GRE_01() throws Exception {
        WebPage pageParticipants = pageIndex.clickMenuRessource(driver, MenuRessources.PARTICIPANTS);
        try {
            assertEquals("Titre de page", "Liste des participants", pageParticipants.getWebPageTitle());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    // @Test
    // public void test() throws Exception {
    // try {
    // assertTrue("La vérité", false);
    // } catch (AssertionError e) {
    // errors.add(e);
    // Reporting.takeScreenShot(driver, "Ceci est vrai");
    // }
    // }

    @After
    public void after() throws Exception {
        driver.quit();
        Reporting.writeReport(errors);
    }

}
