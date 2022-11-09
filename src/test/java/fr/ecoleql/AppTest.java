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
    public void creationParticipant() throws Exception {
        // PDT 2 = Afficher la page "Liste des participants"
        PageParticipants pageParticipants = pageIndex.clickMenuRessourcesParticipants(driver);
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
                    pageParticipants.verifPrenom(driver, params.getProperty("participantPrenom")));
            assertTrue("[PageParticipants] - L'ID du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifID(driver, params.getProperty("participantID")));
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le nouveau participant créer n'apparaît pas correctement dans le tableau");
        }
        // PDT 6 = Filtrer par détails personnels
        pageParticipants.filter(driver, params.getProperty("participantPrenom"));
        try {
            assertTrue("[PageParticipants] - Le nom du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifNom(driver, params.getProperty("participantNom")));
            assertTrue("[PageParticipants] - Le prénom du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifPrenom(driver,
                            params.getProperty("participantPrenom")));
            assertTrue("[PageParticipants] - L'ID du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifID(driver, params.getProperty("participantID")));
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le participant filtré n'apparaît pas correctement dans le tableau");
        }
        // PDT 7 = Menu 'Plus d'options'
        pageParticipants.clickBtnPlusDOptions(driver);
        try {
            assertTrue(
                    "[PageParticipants] - Le champs 'Date de début de préiode active' n'apparaît pas dans 'Plus d'options'",
                    pageParticipants.dateDebutPeriode.isDisplayed());
            assertTrue(
                    "[PageParticipants] - Le champs 'Date de fin de préiode active' n'apparaît pas dans 'Plus d'options'",
                    pageParticipants.dateFinPeriode.isDisplayed());
            assertEquals("[PageParticipants] - Le type 'Tous' n'est pas sélectionné par défaut dans 'Plus d'options'",
                    "Tous",
                    pageParticipants.getMenuType(driver).get(0).getText());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le menu 'Plus d'option' n'apparaît pas correctement");
        }
        // Teardown : Supprimer le participant et l'utilisateur lié créés
        pageParticipants.deleteParticipant(driver, params.getProperty("participantID"), true);
        try {
            assertTrue(
                    "[PageParticipants] - Le message de validation 'Travailleur et utilisateur lié supprimés' ne s'affiche pas",
                    pageParticipants.txtMsgValidationSuppression.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Erreur d'affichage du message de validation de suppression de nouveau participant");
        }
    }

    // @Test sur la creation des projets
    @Test
    public void creationDeProjet() throws Exception {
        // Appel à la methode clickIconCreerProjet dans PageIndex
        pageIndex.clickIconCreerProjet(driver);
        // Appel à la methode "creerProjet" et Instanciation de la PageProjet
        DetailDuProjet detailDuProjet = pageIndex.creerProjet(driver, params.getProperty("projetNom"),
                params.getProperty("projetCode"));
        // Verification de la creation du projet: (Titre de l'oglet horizontal Menue WBS
        // ="WBS (tâches)")
        try {
            assertEquals("WBS (tâches)", detailDuProjet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // Appel à la methode cliquer sur le bouton d'annulation d'edition (pour tester
        // le bouton annuler)
        detailDuProjet.clikButtonAnnulerEditionProjet(driver);
        // Verifier que la PopUp est bien apparue
        try {
            assertEquals(detailDuProjet.textPopUpAVerifier, detailDuProjet.textPopUp.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // Appel à la methode clique sur le bouton annuler de PopUp
        detailDuProjet.clikButtonAnnulerPopUp(driver);
        // Verification de la creation du projet existe toujour
        try {
            assertEquals("WBS (tâches)", detailDuProjet.menuWbs.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // Rappeler la methode cliquer sur le bouton d'annulation d'edition (pour tester
        // le bouton ok)
        detailDuProjet.clikButtonAnnulerEditionProjet(driver);
        // Verifier que la PopUp est bien apparue
        try {
            assertEquals(detailDuProjet.textPopUpAVerifier, detailDuProjet.textPopUp.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // Appel à la methode clique sur le bouton OK de PopUp
        PageIndex pageIndex = detailDuProjet.clikButtonOkPopUp(driver);
        // Verifier l'affichage de menu Planification des projets
        try {
            assertFalse("le menue Planification de projet n'est pas present",
                    PageIndex.buttonPlanificationProjet.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // verifier la creation du projet-->> Appel a la methode
        // clickMenuCalendierProjet
        PageListesProjet pageListesProjet = pageIndex.clickMenuCalendierProjet(driver);
        // Verifier l'affichage de menu Listes des progets des projets
        try {
            assertEquals("le menue Planification de projet n'est pas present", "Liste des projets",
                    pageListesProjet.webPageTitle);
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Teardown : Supprimer le participant et l'utilisateur lié créés
        pageListesProjet.deleteProjet(driver, params.getProperty("projetCode"));
        try {
            assertTrue(
                    "[PageListeProjets] - Le message de validation de suppression ne s'affiche pas",
                    pageListesProjet.txtMsgValidationSuppression.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageListeProjets] - Erreur d'affichage du message de validation de suppression de nouveau participant");
        }
    }

    @After
    public void after() throws Exception {
        driver.quit();
        Reporting.writeReport(errors);
    }

}
