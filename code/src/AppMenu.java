import java.util.*;
import java.sql.*;
import java.io.*;

public class AppMenu{
    public static void afficherMenu(String titre, List<String> options) {
        //clear();
        // Déterminer la largeur maximale
        int largeur = titre.length();
        for (int i = 0; i < options.size(); i++) {
            String ligne = (i + 1) + ". " + options.get(i);
            if (ligne.length() > largeur) {
                largeur = ligne.length();
            }
        }
        largeur += 2; // marges

        // Afficher le cadre supérieur
        System.out.println("╔" + "═".repeat(largeur) + "╗");
        System.out.println("║" + centrerTexte(titre, largeur) + "║");
        System.out.println("╠" + "═".repeat(largeur) + "╣");

        // Afficher les options
        for (int i = 0; i < options.size(); i++) {
            String ligne = (i + 1) + ". " + options.get(i);
            System.out.println("║ "+ligne+" ".repeat(largeur-options.get(i).length()-4)+"║");
        }

        // Afficher le cadre inférieur
        System.out.println("╚" + "═".repeat(largeur) + "╝");
    }

    public static String centrerTexte(String texte, int largeur) {
        int padding = (largeur - texte.length()) / 2;
        if(padding%2==0){
            padding+=1;
        }
        return " ".repeat(Math.max(0, padding)) + texte + " ".repeat(Math.max(0, padding));
    }
    
    public static void main(String[] args) {
        Connection connexion = ConnectionBD.getConnexion();
        Scanner scanner = new Scanner(System.in);
        List<String> mainapp = List.of(
            "Administrateur",
            "Vendeur",
            "Client",
            "Quitter"
        );

        

        List<String> client = List.of(
            "Accès Bibliothèque",
            "Recommandation",
            "Passer Commande",
            "Voir mes Commandes",
            "Retour"
        );

        List<String> vendeur = List.of(
            "Ajouter livre ",
            "Modifier Quantité Livre",
            "Vérifier Disponibilité",
            "Commander du stock",
            "Transférer un livre",
            "Quitter"
        );

        List<String> admin = List.of(
            "Créer un compte vendeur",
            "Ajouter une nouvelle librairie",
            "Gérer les stocks globaux", //doit ouvrir un autre menu
            "Consulter les statistiques de vente",
            "Quitter"
        );


        int choix;
        do {
            afficherMenu("Appli LivreExpress", mainapp);
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.next();
                System.out.print("Votre choix : ");
            }

            choix = scanner.nextInt();

            switch (choix) {
                //Boucle Admin
                case 1 ->{
                    do {
                        AdministrateurBD administrateur = new AdministrateurBD(connexion);
                        afficherMenu("Accès Admin", admin);
                        System.out.print("Votre choix : ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("❌ Veuillez entrer un nombre valide.");
                            scanner.next();
                            System.out.print("Votre choix : ");
                        }

                        choix = scanner.nextInt();
                        switch(choix){
                            case 1->{
                                    String consignes = "Pour créer un compte vendeur, veuillez :";
                                    String lenom = "Entrer le nom : ";
                                    String leprenom = "-----------------";
                                    String lemag = "-----------------";
                                    String varLenom = "nom";
                                    String varLeprenom = "prenom";
                                    String varLemag = "mag";
                                    boolean ok = false;
                                    while(ok != true){
                                        List<String> listeRep = new ArrayList<>();
                                        listeRep.add(consignes);
                                        listeRep.add(lenom);
                                        listeRep.add(leprenom);
                                        listeRep.add(lemag);
                                        afficherMenu("Créer un compte vendeur", listeRep); 

                                        // ON DEMANDE LE NOM                                       
                                        String nom = scanner.next();
                                        while (!(nom instanceof String)){
                                            System.out.println("met un string frere par pitié");
                                        }
                                        listeRep = new ArrayList<>();
                                        listeRep.add(consignes);

                                        varLenom = nom;
                                        lenom += varLenom;
                                        listeRep.add(lenom);

                                        leprenom = "Entrer le prénom : ";
                                        listeRep.add(leprenom);

                                        listeRep.add(lemag);
                                        afficherMenu("Créer un compte vendeur", listeRep);
                                        
                                        // ON DEMANDE LE PRENOM
                                        String prenom = scanner.next();
                                        while (!(prenom instanceof String)){
                                            System.out.println("met un string frere par pitié");
                                        }
                                        listeRep = new ArrayList<>();
                                        listeRep.add(consignes);
                                        listeRep.add(lenom);

                                        leprenom += prenom;
                                        varLeprenom = prenom;
                                        listeRep.add(leprenom);
                                        
                                        lemag = "Entrer le magasin ou il travail : ";
                                        listeRep.add(lemag);
                                        afficherMenu("Créer un compte vendeur", listeRep);


                                        /*
                                        // ON DEMANDE LE NOM MAGASIN
                                        String mag = scanner.nextLine();
                                        while (!(mag instanceof String)){
                                            System.out.println("met un string frere par pitié");
                                            
                                        }

                                        // ON DEMANDE LA VILLE DU MAGASIN
                                        lemag = "Entrer l'addresse du magasin ou il travail : ";
                                        listeRep.add(lemag);
                                        afficherMenu("Créer un compte vendeur", listeRep);
                                        String adrMag = scanner.nextLine();
                                        while (!(adrMag instanceof String)){
                                            System.out.println("met un string frere par pitié");
                                            
                                        }
                                        */                                                                           
                                    }
                            }
                            case 2-> {
                                MagasinBD mags = new MagasinBD(connexion);
                                        List<Magasin> listMag = new ArrayList<>();
                                        try {
                                            listMag = mags.listeMagasins();
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                                        //la fonction de clement qui renvoie une liste des magasin existant
                                        Magasin magasin = demanderMagasin();
                                        System.out.println(magasin);
                                        listeRep = new ArrayList<>();
                                        listeRep.add(consignes);
                                        listeRep.add(lenom);
                                        listeRep.add(leprenom);
                                        //lemag += mag;
                                        //varLemag = mag;
                                        listeRep.add(lemag);
                                        //Magasin magasin = new Magasin(varLemag, adrMag);
                                        Vendeur leVendeur = new Vendeur(varLenom, varLeprenom, magasin);
                                        
                                        //System.out.println("Vous avez créé "+ varLeprenom +" "+ varLenom+ ", qui travail au magasin " + magasin.getNomMag() + ".");
                                        ok = true;
                                        try {
                                            administrateur.creerVendeur(leVendeur, magasin);
                                        } catch (Exception e) {
                                            System.err.println(e.getMessage());
                                        }
                            }
                            case 3-> System.out.println("Fonction à Faire");
                            case 4-> System.out.println("Fonction à Faire");
                            case 5-> System.out.println("Retour au main");
                            default-> System.out.println("❌ Option invalide.");
                        }

                    } while(choix !=client.size());
                }

                //Boucle Vendeur
                case 2 -> {
                    do {
                        VendeurBD vendeurs = new VendeurBD(connexion);
                        afficherMenu("Accès Vendeur", vendeur);
                        System.out.print("Votre choix : ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("❌ Veuillez entrer un nombre valide.");
                            scanner.next();
                            System.out.print("Votre choix : ");
                        }

                        choix = scanner.nextInt();
                        switch(choix){
                            case 1-> {
                                /*
                                ajouterLivre(Livre)

                                private String titre;
                                private Auteur auteur;
                                private int nbDePages;
                                private double prix;
                                private int dateParution;
                                private Editeur editeur;
                                private List<Classification> themes;
                                */ 

                                //String titre = " ";
                                //List<Auteur> listAuteur = new ArrayList<>();
                                //Auteur auteur = demanderAuteur(listAuteur);
                                
                                AuteurBD Auts = new AuteurBD(connexion);

                                List<Auteur> listAuteurs = new ArrayList<>();
                                try {
                                    System.out.println("dans le try");
                                    listAuteurs = Auts.listeAuteurs();
                                    System.out.println(listAuteurs);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                                //la fonction de clement qui renvoie une liste des magasin existant
                                Livre livre = demanderLivre(listAuteurs);
                                VendeurBD vend = new VendeurBD(connexion);
                                //Auteur auteur = demanderAuteur(livre);
                                try {
                                    vend.ajouterLivre(livre);
                                } catch (SQLException e) {
                                    System.err.println(e.getMessage());
                                }
                                
                            }
                            case 2-> {
                            //"Modifier Quantité Livre"
                                //on demande la liste des vendeurs pour qu'il s'identifie et que 'lon récupère son magasin'
                                List<Vendeur> listeVend = vendeurs.getListVendeurs();
                                Vendeur vend = demanderVendeur(listeVend);
                    
                                //modifierQuantiteLivreMagasin(int isbn, int idmag)
                            }
                            case 3-> System.out.println("Fonction à Faire");
                            case 4-> System.out.println("Fonction à Faire");
                            case 5-> System.out.println("Fonction à Faire");
                            case 6-> System.out.println("Retour au main");
                            default-> System.out.println("❌ Option invalide.");
                        }

                    } while(choix !=vendeur.size());
                }
                
                //Boucle Client
                case 3 -> {
                    do {
                        afficherMenu("Accès Client", client);
                        System.out.print("Votre choix : ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("❌ Veuillez entrer un nombre valide.");
                            scanner.next();
                            System.out.print("Votre choix : ");
                        }

                        choix = scanner.nextInt();
                        switch(choix){
                            case 1-> System.out.println("Fonction à Faire");
                            case 2-> System.out.println("Fonction à Faire");
                            case 3-> System.out.println("Fonction à Faire");
                            case 4-> System.out.println("Fonction à Faire");
                            case 5-> System.out.println("Retour au main");
                            default-> System.out.println("❌ Option invalide.");
                        }

                    } while(choix !=client.size());
                }
                case 4 -> System.out.println("👋 Au revoir !");
                default -> System.out.println("❌ Option invalide.");
            }

            System.out.println(); // espace

        } while (choix != mainapp.size());

        scanner.close();
    }


    public static Livre demanderLivre(List<Auteur> listAuteur){
        
        Scanner scanner = new Scanner(System.in);
        List<String> listeRep= new ArrayList<>();
        listeRep.add("Veuillez entrer le titre du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        String titre = scanner.nextLine();
        Auteur auteur = demanderAuteur(listAuteur);
        String nomAut = auteur.getNom();
        listeRep= new ArrayList<>();

        listeRep.add("Veuillez entrer le nombre de pages du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        int nbPage = scanner.nextInt();

        listeRep.add("Veuillez entrer le prix du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        double prix = scanner.nextDouble();

        listeRep.add("Veuillez entrer la date de parution du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        int date = scanner.nextInt();

        listeRep.add("Veuillez entrer l'éditeur du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        String nomEdit = scanner.next();
        Editeur editeur = new Editeur(nomEdit);
        
        listeRep.add("Veuillez entrer le nombre de thèmes du livre : ");
        afficherMenu("Quel est le livre ?", listeRep);
        int nbTh = scanner.nextInt();

        /*
        List<Classification> themes = null;
        for (Integer i; i<nbTh; i++){
            listeRep.add("Veuillez entrer le thèmes " + String.valueOf(i) + " du livre : ");
            afficherMenu("Quel est le livre ?", listeRep);
            //le int iddewey
            // le String theme
            String theme = scanner.next();
            themes.add(new Classification(theme));
        }
        */

        Livre livre = new Livre(titre, auteur, date, nbPage, prix, editeur);
        listeRep= new ArrayList<>();
        listeRep.add("D'accord ! Le livre s'appelle "+titre+",\n il a été écrit par  "+
        //auteur.getPrenom() + "\n "+ 
        nomAut + " en " +
        String.valueOf(date) + ".\n Il à été édité par " +
        editeur.getNom() + "\n au prix de " +
        String.valueOf(prix) + "€ et \nil fait "+ String.valueOf(nbPage) + " pages.");
        afficherMenu("Le livre !", listeRep);
        return livre;
    }
    public static Auteur demanderAuteur(List<Auteur> listAuteur){
        Scanner scanner = new Scanner(System.in);
        List<String> listeNomAut = new ArrayList<>();
        System.out.println(listAuteur);
        for (Auteur aut : listAuteur){
            String nomAut = aut.getNom();
            listeNomAut.add(nomAut);
        }

        afficherMenu("Qui est l'auteur que vous souhaitez", listeNomAut);
        
        int choix = scanner.nextInt();
        int taille = listeNomAut.size();
        Auteur aut = null;
        /*while (cpt < taille){
            if (choix == cpt){
                mag = listeMag.get(cpt);
            }
        }*/
        boolean flag = false;
        //System.out.println("AVANT LE WHILE");
        while (flag == false){
            //System.out.println(" le choix est : " + choix);
            if (choix-1 < taille && taille > 0){
                aut = listAuteur.get(choix-1);
                System.out.println("dans le while le magasin est : "+aut.getNom());
                flag = true;
            }
        }
        return aut;
        /*
        Scanner scanner = new Scanner(System.in);
        List<String> listeRep= new ArrayList<>();
        listeRep.add("Veuillez entrer le nom de l'auteur : ");
        afficherMenu("Qui est l'auteur(trice) ?", listeRep);
        String nom = scanner.next();
        listeRep= new ArrayList<>();
        listeRep.add("Veuillez entrer le prenom de l'auteur : ");
        afficherMenu("Qui est l'auteur(trice) ?", listeRep);
        String prenom = scanner.next();
        Auteur auteur = new Auteur(nom, prenom);
        listeRep= new ArrayList<>();
        listeRep.add("D'accord ! L'auteur est "+prenom+" "+nom );
        afficherMenu("Qui est l'auteur(trice) ?", listeRep);
        return auteur;
        */
    }

    public static Magasin demanderMagasin(){ 
        Scanner scanner = new Scanner(System.in);
        List<String> listeRep= new ArrayList<>();
        listeRep.add("Veuillez entrer le nom du Magasin : ");
        afficherMenu("Quel est le Magasin ", listeRep);
        String nom = scanner.nextLine();
        listeRep= new ArrayList<>();

        listeRep.add("Veuillez entrer la ville du Magasin : ");
        afficherMenu("Quel est le livre ?", listeRep);
        String villeMag = scanner.nextLine();


    }

    public static Vendeur demanderVendeur(List<Vendeur> listeVend){
        Scanner scanner = new Scanner(System.in);
        List<String> listeNomVendeur = new ArrayList<>();
        for (Vendeur v : listeVend){
            String nomV = v.getNom();
            listeNomVendeur.add(nomV);
        }

        afficherMenu("Quel Vendeur voulez vous", listeNomVendeur);
        
        int choix = scanner.nextInt();
        int taille = listeNomVendeur.size();
        Vendeur vend = null;
        /*while (cpt < taille){
            if (choix == cpt){
                mag = listeMag.get(cpt);
            }
        }*/
        boolean flag = false;
        System.out.println("AVANT LE WHILE");
        while (flag == false){
            System.out.println(" le choix est : " + choix);
            if (choix-1 < taille && taille > 0){
                vend = listeVend.get(choix-1);
                System.out.println("dans le while le vendeur est : "+vend.getNom());
                flag = true;
            }
        }
        //System.out.println(mag);
        return vend;
        }
    
}