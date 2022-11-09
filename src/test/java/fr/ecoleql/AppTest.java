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

    private Properties constants;
    private Properties variables;
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
        constants = new Properties();
        constants.load(new FileInputStream("src/main/resources/JDD/constants.properties"));
        variables = new Properties();
        variables.load(new FileInputStream("src/main/resources/JDD/variables.properties"));
        // Instancier la page login
        driver.get(constants.getProperty("BASEURL"));
        PageLogin pageLogin = PageFactory.initElements(driver, PageLogin.class);
        // Se connecter et accéder à la page index
        pageIndex = pageLogin.login(driver, constants.getProperty("USERNAME"), constants.getProperty("PASSWORD"));
        // Vérifier le titre de la page
        try {
            assertTrue("[PageIndex] - Le bouton 'Déconnexion' n'apparaît pas", pageIndex.isLoggedIn());
        } catch (AssertionError e) {
            errors.add(e);
        }
    }

    @Test
    public void Scenario_01_Criteres_CRI_01_AdministrationDesCriteres() throws Exception {
    // // Test fonctionnel CRI_01 - Administration des critères
    // // PDT 1 --> @Before
    // // PDT 2 : Accéder à la page d'administration des calendriers :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 3 : Créer un calendrier - Accès au formulaire de création :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 4 : Créer un calendrier - bouton [Enregistrer] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 5 : Créer un calendrier dérivé :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 6 : Créer un critère - bouton [Enregistrer] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 7 : Créer un critère - Accès au formulaire de création :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 8 : Créer un critère - bouton [Sauver et continuer] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 9 : Retour page d'administration des critères :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 10 : Modifier un critère - accès formulaire de modification - Colonne "Opération" :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 11 : Modifier un critère -  Bouton [Annuler] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 12 : Modifier un critère - accès formulaire de modification - Colonne "Nom" :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 13 : Modifier un critère - modification du nom :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 14 : Modifier un critère - bouton [Sauver et continuer] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 15 : Retour page d'administration des critères :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 16 : Supprimer un critère - Pop-up de confirmation :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 17 : Supprimer un critère - Bouton [Annuler] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 18 : Supprimer un critère - Pop-up de confirmation :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    // // PDT 19 : Supprimer un critère - Bouton [OK] :
    // // action = A MODIFIER
    // try {
    //     // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
    // } catch (AssertionError e) {
    //     errors.add(e);
    //     Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
    // }
    
}

    @Test
    public void creationParticipant() throws Exception {
        // PDT 2 = Accéder à la page de gestion des participants
        PageParticipants pageParticipants = pageIndex.clickMenuRessourcesParticipants(driver);
        // PDT 3 = Créer un participant - Accès au formulaire de création
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
        // PDT 4 = Créer un participant - Conformité de l'onglet "Données personnelles"
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
        // PDT 5 = Créer un participant - Bouton [Enregistrer]
        pageCreerParticipant.saisirDonneesDeBase(driver, variables.getProperty("participantPrenom"),
                variables.getProperty("participantNom"), variables.getProperty("participantID"));
        pageCreerParticipant.radioUtilisateurLieCreerUnNouvelUtilisateur.click();
        pageCreerParticipant.creerNouvelUtilisateur(driver, variables.getProperty("utilisateurNom"),
                variables.getProperty("utilisateurMDP"), variables.getProperty("utilisateurMail"));
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
                    pageParticipants.verifNom(driver, variables.getProperty("participantNom")));
            assertTrue("[PageParticipants] - Le prénom du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifPrenom(driver, variables.getProperty("participantPrenom")));
            assertTrue("[PageParticipants] - L'ID du participant créé n'apparaît pas dans le tableau",
                    pageParticipants.verifID(driver, variables.getProperty("participantID")));
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le nouveau participant créer n'apparaît pas correctement dans le tableau");
        }
        // PDT 6 = Utilisation du filtre "Détails personnels"
        pageParticipants.filter(driver, variables.getProperty("participantPrenom"));
        try {
            assertTrue("[PageParticipants] - Le nom du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifNom(driver, variables.getProperty("participantNom")));
            assertTrue("[PageParticipants] - Le prénom du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifPrenom(driver,
                            variables.getProperty("participantPrenom")));
            assertTrue("[PageParticipants] - L'ID du participant filtré n'apparaît pas dans le tableau",
                    pageParticipants.verifID(driver, variables.getProperty("participantID")));
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageParticipants] - Le participant filtré n'apparaît pas correctement dans le tableau");
        }
        // PDT 7 = Filtre "Plus d'options" - conformité des options
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

        // Teardown = Supprimer le participant et l'utilisateur lié créés
        pageParticipants.deleteParticipant(driver, variables.getProperty("participantID"), true);
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
        // PDT 2 = Accéder au formulaire de création d'un projet
        pageIndex.clickIconCreerProjet(driver);
        // PDT 3 = Créer un projet - Bouton [Accepter]
        PageDetailsProjet pageDetailsProjet = pageIndex.creerProjet(driver, variables.getProperty("projetNom"),
                variables.getProperty("projetCode"));
        try {
            assertTrue("[PageDetailsProjet] - L'onglet 'WBS (tâches) n'apparaît pas",
                    pageDetailsProjet.menuDetailDuProjet.isDisplayed());
            assertTrue("[PageDetailsProjet] - Le menu 'Détail du projet' n'apparaît pas",
                    pageDetailsProjet.tabWbs.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageDetailsProjet] - Erreur lors de la création de projet");
        }
        // PDT 4 = Vérifier les onglets - menu vertical
        try {
            assertEquals(
                    "Le menu 'Planification de projet' n'apparait pas sur le menu vertical de la page DetailDuProjet",
                    "Planification de projet", pageDetailsProjet.menuPlanificationDeProjet.getText());
            assertEquals(
                    "Le menu 'Détail du projet' n'apparait pas sur le menu vertical de la page DetailDuProjet",
                    "Détail du projet", pageDetailsProjet.menuDetailDuProjet.getText());
            assertEquals(
                    "Le menu 'Chargement des ressources' n'apparait pas sur le menu vertical de la page DetailDuProjet",
                    "Chargement des ressources", pageDetailsProjet.menuChargementDesRessources.getText());
            assertEquals(
                    "Le menu 'Allocation avancée' n'apparait pas sur le menu vertical de la page DetailDuProjet",
                    "Allocation avancée", pageDetailsProjet.menuAllocationAvancee.getText());
            assertEquals(
                    "Le menu 'Tableau de bord' n'apparait pas sur le menu vertical de la page DetailDuProjet",
                    "Tableau de bord", pageDetailsProjet.menuTableauDeBord.getText());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, "[PageCreerParticipant] - Eléments par défaut");
        }
        // PDT 5 = Vérifier les onglets - menu horizontal
        try {
            assertTrue("L'onglet 'WBS (tâches)' n'apparaît pas",
                    pageDetailsProjet.tabWbs.isDisplayed());
            assertTrue("L'onglet 'Données générales' n'apparaît pas",
                    pageDetailsProjet.tabDonneesGenerales.isDisplayed());
            assertTrue("L'onglet 'Coût' n'apparaît pas",
                    pageDetailsProjet.tabCout.isDisplayed());
            assertTrue("L'onglet 'Avancement' n'apparaît pas",
                    pageDetailsProjet.tabAvancement.isDisplayed());
            assertTrue("L'onglet 'Libéllés' n'apparaît pas",
                    pageDetailsProjet.tabLibelles.isDisplayed());
            assertTrue("L'onglet 'Exigences de critère' n'apparaît pas",
                    pageDetailsProjet.tabExigences.isDisplayed());
            assertTrue("L'onglet 'Matériels' n'apparaît pas",
                    pageDetailsProjet.tabMateriels.isDisplayed());
            assertTrue("L'onglet 'Formulaires qualité des tâches' n'apparaît pas",
                    pageDetailsProjet.tabFormulaire.isDisplayed());
            assertTrue("L'onglet 'Autorisation' n'apparaît pas",
                    pageDetailsProjet.tabAutorisation.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, "[PageCreerParticipant] - Eléments par défaut");
        }
        // PDT 7 = Utilisation du bouton d'annulation de l'édition du projet (1/4)
        pageDetailsProjet.clikButtonAnnulerEditionProjet(driver);
        try {
            assertEquals(
                    "[PageDetailsProjet] - Le message de confirmation d'annulation des modifications du projet n'apparaît pas correctement",
                    "Les modifications non enregistrées seront perdues. Êtes-vous sûr ?",
                    pageDetailsProjet.textPopUp.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // PDT 8 = Utilisation du bouton d'annulation de l'édition du projet (2/4)
        pageDetailsProjet.clikButtonAnnulerPopUp(driver);
        try {
            assertTrue("[PageDetailsProjet] - L'onglet 'WBS (tâches) n'apparaît pas",
                    pageDetailsProjet.menuDetailDuProjet.isDisplayed());
            assertTrue("[PageDetailsProjet] - Le menu 'Détail du projet' n'apparaît pas",
                    pageDetailsProjet.tabWbs.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver,
                    "[PageDetailsProjet] - Erreur lors de l'utilisation du bouton d'annulation de l'édition du projet");
        }
        // PDT 9 = Utilisation du bouton d'annulation de l'édition du projet (3/4)
        pageDetailsProjet.clikButtonAnnulerEditionProjet(driver);
        try {
            assertEquals(
                    "[PageDetailsProjet] - Le message de confirmation d'annulation des modifications du projet n'apparaît pas correctement",
                    "Les modifications non enregistrées seront perdues. Êtes-vous sûr ?",
                    pageDetailsProjet.textPopUp.getText());
        } catch (AssertionError e) {
            errors.add(e);
        }
        // PDT 10 = Utilisation du bouton d'annulation de l'édition du projet (4/4)
        PageIndex pageIndex = pageDetailsProjet.clikButtonOkPopUp(driver);
        try {
            assertTrue("[PageIndex] - Le menu 'Planification des projets' n'apparaît pas",
                    pageIndex.btnPlanificationProjet.isDisplayed());
            assertTrue("[PageDetailsProjet] - Le menu horizontal de détail du projet apparaît toujours",
                    pageDetailsProjet.menuDetailDuProjet.toString().contains("DefaultElementLocator "));
        } catch (AssertionError e) {
            errors.add(e);
        }
        // PDT 11 = Vérifier la création du projet
        PageListeProjets pageListesProjet = pageIndex.clickMenuCalendierProjet(driver);
        try {
            assertTrue("[PageListesProjet] - Le menu 'Liste des projets' n'apparaît pas",
                    pageListesProjet.btnListeProjets.isDisplayed());
        } catch (AssertionError e) {
            errors.add(e);
        }

        // Teardown : Supprimer le participant et l'utilisateur lié créés
        pageListesProjet.deleteProjet(driver, variables.getProperty("projetCode"));
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
