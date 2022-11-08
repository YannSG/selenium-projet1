# selenium-projet1
 Projet 1 - Groupe 3

        Bonnes pratiques pour les PageObjects :
    
    Lorsque vous découpez votre architecture d’automatisation en couches et que vous concevez les Page Objects, vous devez respecter plusieurs règles générales :
            Les Page Objects ne doivent pas contenir d'assertions sur la logique métier ni de points de vérification.
            Toutes les assertions et vérifications techniques concernant l'interface graphique (par exemple, vérifier si une page a fini de se charger) doivent être réalisées dans les Page Objects.
            Toutes les attentes doivent être encapsulées dans les Page Objects.
            Seul le Page Object doit contenir les appels aux fonctions Selenium.
            Un Page Object n'a pas besoin de couvrir toute la page ou le formulaire. Il peut contrôler une section ou une autre partie spécifique de celle-ci.


        Template des pas de test :
    
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
