# selenium-projet1
 Projet 1 - Groupe 3
 
---------------------------------------
Constitution de l'équipe automatisation :
---------------------------------------

    * Fatima B.    - Ingénieure Réseaux et automaticienne Selenium/Java (S/J) Junior
    * Benjamin A.  - Ingénieur Qualité logicielle et automaticien S/JJunior
    * Yann S.      - Expert outils et ingénieur automaticien S/J confirmé. *Tech lead*.
    * Sébastien P. - Test Analyst et ingénieur automatisation senior. Automaticien S/J débutant. *Scrum Master*. 

------------------
 Bonnes pratiques :
------------------

#PageObjects :


    Lorsque vous découpez votre architecture d’automatisation en couches et que vous concevez les Page Objects, 
    vous devez respecter plusieurs règles générales :
    
    * Les Page Objects ne doivent pas contenir d'assertions sur la logique métier ni de points de vérification.
    * Toutes les assertions et vérifications techniques concernant l'interface graphique 
    (par exemple, vérifier si une page a fini de se charger) doivent être réalisées dans les Page Objects.
    * Toutes les attentes doivent être encapsulées dans les Page Objects.
    * Seul le Page Object doit contenir les appels aux fonctions Selenium.
    * Un Page Object n'a pas besoin de couvrir toute la page ou le formulaire. 
    Il peut contrôler une section ou une autre partie spécifique de celle-ci.

#GitHub rules :

    Blablabla

#Agilité :

    * Kanban blablabla
    * Cérémonies agiles blablabla (Stand Up, et autres blablabla)

------------------------------
Template des cas/pas de test :
------------------------------

    @Test
    public void REFERENCE_DU_TEST() throws Exception {
        // PDT NUMERO_DU_PAS_DE_TEST + description
        // action = A MODIFIER
        try {
            // ASSERTION ( = assertTrue / assertEquals) =  A MODIFIER
        } catch (AssertionError e) {
            errors.add(e);
            Reporting.takeScreenShot(driver, NOM_DU_SCREENSHOT);
        }
    }
