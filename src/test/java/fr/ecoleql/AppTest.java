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
        // Instancier le driver
        driver = Driver.getDriver(Browser.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Récupérer les propriétes
        params = new Properties();
        params.load(new FileInputStream("src/main/resources/JDD/params.properties"));
        // Instancier la page login
        driver.get(params.getProperty("baseUrl"));
        PageLogin pageLogin = PageFactory.initElements(driver, PageLogin.class);
        // Se connecter et accéder à la page index
        pageIndex = pageLogin.login(driver, params.getProperty("username"), params.getProperty("password"));
        // Vérifier le titre de la page
        try {
            assertTrue("Connexion effectuée", pageIndex.isLoggedIn());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void REFERENCE_DU_TEST() throws Exception {
        System.out.println("test");
        // PDT NUMERO_DU_PAS_DE_TEST + description
        // action = A MODIFIER
        // try {
        // // ASSERTION ( = assertTrue / assertEquals) = A MODIFIER
        // } catch (AssertionError e) {
        // errors.add(e);
        // }
    }

    @Test
    public void GRE_01() throws Exception {
        // PDT 2 = Afficher la page "Liste des participants"
        PageParticipants pageParticipants = pageIndex.clickMenuRessourcesParticipants(driver,
                MenuRessources.PARTICIPANTS);
        // PDT 3 = Afficher la page "Créer un participant"
        PageCreerParticipant pageCreerParticipant = pageParticipants.clickBtnCreer(driver);
        try {
            assertTrue("L'onglet 'Données personnelles' est absent",
                    pageCreerParticipant.tabDonnesPersonnelles.isDisplayed());
            assertTrue("L'onglet par défaut n'est pas 'Données personnelles'",
                    pageCreerParticipant.txtDonneesDeBase.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, "[PageCreerParticipant] - Onglet par défaut");
        }
        // PDT 4 = Vérifier la conformité de la page "Créer un participant"

        // try {
        // assertTrue(Integer.toString(i), .isDisplayed());
        // } catch (AssertionError e) {
        // errors.add(e);
        // }
        try {
            assertTrue("Le bouton 'Générer le code' n'est pas sélectionné par défaut",
                    pageCreerParticipant.chkCode.isSelected());
            assertEquals("Le type par défaut n'est pas 'Ressource normale'", "Ressource normale",
                    pageCreerParticipant.getMenuType(driver).get(0).getText());
            assertTrue("Le bouton 'Générer le code' n'est pas sélectionné par défaut",
                    pageCreerParticipant.radioUtilisateurLieNonLié.isSelected());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, "[PageCreerParticipant] - Eléments par défaut");
        }
        // PDT 5 = Créer un nouveau participant
        pageCreerParticipant.saisirDonneesDeBase(driver, params.getProperty("participantPrenom"),
                params.getProperty("participantNom"), params.getProperty("participantID"));
        pageCreerParticipant.radioUtilisateurLieCreerUnNouvelUtilisateur.click();
        pageCreerParticipant.creerNouvelUtilisateur(driver, params.getProperty("utilisateurNom"),
                params.getProperty("utilisateurMDP"), params.getProperty("utilisateurMail"));
    }

    @After
    public void after() throws Exception {
        driver.quit();
        Reporting.writeReport(errors);
    }

}
