package fr.ecoleql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
            assertTrue("[PageIndex] - Le bouton 'Déconnexion' n'apparaît pas", pageIndex.isLoggedIn());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void GRE_01() throws Exception {
        // PDT 2 = Afficher la page "Liste des participants"
        PageParticipants pageParticipants = pageIndex.clickMenuRessourcesParticipants(driver,
                MenuRessources.PARTICIPANTS);
        // PDT 3 = Afficher la page "Créer un participant"
        PageCreerParticipant pageCreerParticipant = pageParticipants.clickBtnCreer(driver);
        try {
            assertTrue("[PageCreerParticipants] - L'onglet 'Données personnelles' est absent",
                    pageCreerParticipant.tabDonnesPersonnelles.isDisplayed());
            assertTrue("[PageCreerParticipants] - L'onglet par défaut n'est pas 'Données personnelles'",
                    pageCreerParticipant.txtDonneesDeBase.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, "[PageCreerParticipant] - Onglet par défaut");
        }
        // PDT 4 = Vérifier la conformité de la page "Créer un participant"
        try {
            assertTrue("[PageCreerParticipants] - Le bouton 'Générer le code' n'est pas sélectionné par défaut",
                    pageCreerParticipant.chkCode.isSelected());
            assertEquals("[PageCreerParticipants] - Le type par défaut n'est pas 'Ressource normale'",
                    "Ressource normale",
                    pageCreerParticipant.getMenuType(driver).get(0).getText());
            assertTrue("[PageCreerParticipants] - Le bouton 'Générer le code' n'est pas sélectionné par défaut",
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
        pageParticipants = pageCreerParticipant.enregistrer(driver);
        try {
            assertTrue("[PageParticipants] - Le message de validation 'Participant enregistré' ne s'affiche pas",
                    pageParticipants.txtMsgValidationCreation.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Erreur d'affichage du message de validation de création de nouveau participant");
        }
        try {
            assertTrue("[PageParticipants] - Le nom du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifNom(driver, params.getProperty("participantNom")));
            assertTrue("[PageParticipants] - Le prénom du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifNom(driver, params.getProperty("participantPrenom")));
            assertTrue("[PageParticipants] - L'ID du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifNom(driver, params.getProperty("participantID")));
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le nouveau participant créer n'apparaît pas correctement dans le tableau");
        }
        // Supprimer le participant et l'utilisateur lié créés
        pageParticipants.deleteParticipant(driver, params.getProperty("participantID"), true);
        try {
            assertTrue("[PageParticipants] - Le message de validation 'Travailleur et utilisateur lié supprimés' ne s'affiche pas",
                    pageParticipants.txtMsgValidationSuppression.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Erreur d'affichage du message de validation de suppression de nouveau participant");
        }
    }

    @Test
    public void PRO_TA_01() throws Exception {

        // Appel à la methode clickIconCreerProjet dans PageIndex
        pageIndex.clickIconCreerProjet(driver);

        // Appel à la methode "creerProjet" et Instanciation de la PageProjet
        PageProjet pageprojet = pageIndex.creerProjet(driver, "PROJET_TEST1", "PRJTEST001");

        // Verification de la creation du projet: (Titre de l'oglet horizontal Menue WBS
        // ="WBS (tâches)")
        try {
            assertEquals("WBS (tâches)", pageprojet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Appel à la methode cliquer sur le bouton d'annulation d'edition (pour tester
        // le bouton annuler)
        pageprojet.clikButtonAnnulerEditionProjet(driver);

        // Verifier que la PopUp est bien apparue
        try {
            assertEquals(pageprojet.textPopUpAVerifier, pageprojet.textPopUp);
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Appel à la methode clique sur le bouton annuler de PopUp
        pageprojet.clikButtonAnnulerPopUp(driver);

        // Verification de la creation du projet existe toujour
        try {
            assertEquals("WBS (tâches)", pageprojet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Rappeler la methode cliquer sur le bouton d'annulation d'edition (pour tester
        // le bouton ok)
        pageprojet.clikButtonAnnulerEditionProjet(driver);

        // Verifier que la PopUp est bien apparue
        try {
            assertEquals(pageprojet.textPopUpAVerifier, pageprojet.textPopUp);
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Appel à la methode clique sur le bouton OK de PopUp
        pageprojet.clikButtonOkPopUp(driver);

        // Verification de la creation du projet est bien annulé (Absence des element
        // WBS de menu)
        try {
            assertFalse("le menue WBS est present", pageprojet.menuWbs.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @After
    public void after() throws Exception {
        driver.quit();
        Reporting.writeReport(errors);
    }

}
