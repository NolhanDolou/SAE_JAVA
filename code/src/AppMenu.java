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
        Connection connexion = ConnectionBD.getConnection();
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

        List<String> statistiques = List.of(
            "Nombre de livres vendu d'un magasin",
            "Chiffre d'affaire du magasin",
            "Le livre le plus vendu",
            "quitter"
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
                //créer un compte vendeur
                
                case 1 ->{
                    do {
                        AdministrateurBD administrateur = new AdministrateurBD(connexion);
                        MagasinBD magasinBD = new MagasinBD(connexion);
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
                                    }
                            }
                            //Ajouter une nouvelle librairie
                            case 2-> {
                                Magasin mag = demanderMagasin();
                                AdministrateurBD adminBD = new AdministrateurBD(connexion);
                                MagasinBD magBD = new MagasinBD(connexion);
                                try {
                                    int maxidmag = magBD.listeMagasins().size();
                                    adminBD.ajouteMagasin(String.valueOf(maxidmag+1), mag.getNomMag(),mag.getVilleMag() );
                                } catch (SQLException e) {
                                    System.err.println(e.getMessage());
                                }    
                            }

                            //Gérer les stocks globaux //doit ouvrir un autre menu
                            case 3-> {
                                // afficher les stock d'un magasin précis
                                //ajoute un livre dans la bd
                                //ajoute un livre à un magasin

                            }

                            //Consulter les statistiques de vente
                            case 4-> {
                                do {
                                    afficherMenu("Consultation des statistiques", statistiques);
                                    System.out.print("Votre choix : ");

                                    while (!scanner.hasNextInt()) {
                                        System.out.println("❌ Veuillez entrer un nombre valide.");
                                        scanner.next();
                                        System.out.print("Votre choix : ");
                                    }

                                    choix = scanner.nextInt();
                                    List<Magasin> listeDesMags = new ArrayList<>();
                                            try{
                                                listeDesMags = magasinBD.listeMagasins();
                                            }
                                            catch(SQLException e) {
                                                System.err.println(e.getMessage());
                                            }
                                    MagasinBD magBD = new MagasinBD(connexion);
                                    switch(choix){
                                        // renvoie le nombre de livres vendus
                                        case 1-> {
                                            // l'admin choisi le magasin qu'il veut
                                            Magasin mag = demanderParmiMag(listeDesMags);
                                            // on lui renvoie le nombre de livre qu'a vendu le magasin
                                            String rep = " Le magasin "+mag.getNomMag()+" a vendu ";
                                            // il faut que rep soit la réponse EN STRING !!!!
                                            String idmag = magBD.getidMag(connexion, mag.getNomMag());
                                            try{
                                                rep += String.valueOf(magBD.getNombreLivresVendusParMagasin(idmag));
                                            }
                                            catch(SQLException e){
                                                System.err.println(e.getMessage());
                                            }
                                            // Ensuite on affichera rep
                                            List<String> listeRep = new ArrayList<>();
                                            rep += " livre(s).";
                                            listeRep.add(rep);
                                            afficherMenu("Le nombre de livres vendus : ",listeRep);
                                        }

                                        //le chiffre d'affaire du magasin
                                        case 2-> {
                                            // l'admin choisi le magasin qu'il veut
                                            Magasin mag = demanderParmiMag(listeDesMags);
                                            // on lui renvoie le chiffre d'affaire du magasin
                                            String rep = "Le chiffre d'affaire du magasin "+ mag.getNomMag()+" est de ";
                                            // il faut que rep soit la réponse EN STRING !!!!
                                            String idmag = magBD.getidMag(connexion, mag.getNomMag());
                                            try{
                                                rep += String.valueOf(magBD.getChiffreAffaireMagasin(idmag));
                                            }
                                            catch(SQLException e){
                                                System.err.println(e.getMessage());
                                            }
                                            // Ensuite on affichera rep
                                            List<String> listeRep = new ArrayList<>();
                                            rep += " euros.";
                                            listeRep.add(rep);
                                            afficherMenu("Le chiffre d'affaire du magasin "+ mag.getNomMag() + " : ",listeRep);
                                            
                                        }

                                        // le livre le plus vendu
                                        case 3-> {
                                            // l'admin choisi le magasin qu'il veut
                                            Magasin mag = demanderParmiMag(listeDesMags);
                                            // on lui renvoie le livre le plus vendu 
                                            String rep = "Le livre le plus vendu du magasin "+ mag.getNomMag()+" est : ";
                                            // il faut que rep soit la réponse EN STRING !!!!
                                            String idmag = magBD.getidMag(connexion, mag.getNomMag());
                                            try{
                                                rep += String.valueOf(magBD.getLivreLePlusVendu(idmag));
                                            }
                                            catch(SQLException e){
                                                System.err.println(e.getMessage());
                                            }
                                            // Ensuite on affichera rep
                                            List<String> listeRep = new ArrayList<>();
                                            rep += ".";
                                            listeRep.add(rep);
                                            afficherMenu("Le livre le plus vendu du magasin "+ mag.getNomMag() + " : ",listeRep);
                                        }
                                        

                                        //Quitter
                                        case 4-> System.out.println("Retour au main");
                                        default-> System.out.println("❌ Option invalide.");
                                    }

                                } while(choix !=client.size());
                            }

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
                                AuteurBD Auts = new AuteurBD(connexion);

                                List<Auteur> listAuteurs = new ArrayList<>();
                                try {
                                    System.out.println("dans le try");
                                    listAuteurs = Auts.listeAuteurs();
                                    //System.out.println(listAuteurs);
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
                                Magasin leMag = vend.getMagasin();
                                MagasinBD magBD = new MagasinBD(connexion);
                                String idmag = magBD.getidMag(connexion, leMag.getNomMag()); // renvoie l'idmag au format str
                                List<Livre> lesLivresDuMag = new ArrayList<>();
                                //il nous faut la liste des livres de CE magasin
                                try {
                                    lesLivresDuMag = magBD.livresParMagasin(idmag);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                                
                                
                                Livre livre =  demanderLivre(vend, lesLivresDuMag);
                                //on fait la meme manip que pour magasin pour chopper l'isbn
                                LivreBD livreBD = new LivreBD(connexion);
                                String isbn = "";
                                try{
                                    isbn = livreBD.getIsbn(livre);
                                }
                                catch(SQLException e){
                                    System.err.println(e.getMessage());
                                }

                                int qte = demanderQte();

                                try{
                                    vendeurs.modifierQuantiteLivreMagasin(isbn,idmag,qte);
                                }
                                catch(SQLException e){
                                    System.err.println(e.getMessage());
                                }
                                
                            }
                            case 3-> {
                                MagasinBD magBD = new MagasinBD(connexion);
                                List<Auteur> listAuteurs = new ArrayList<>();
                                try {
                                    //demanderLivreDisponible(magBD);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            }
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

                        MagasinBD magasinBD = new MagasinBD(connexion);
                        List<Magasin> listeDesMags = new ArrayList<>();
                        try{
                        listeDesMags = magasinBD.listeMagasins();
                        }
                        catch(SQLException e) {
                            System.err.println(e.getMessage());
                        }
                        
                        switch(choix){
                           //"Accès Bibliothèque"
                            case 1-> {
                                
                                //on veut renvoyer tous les livres présent dans un magasin choisi 
                                Magasin leMag = demanderParmiMag( listeDesMags);
                                MagasinBD magBD = new MagasinBD(connexion);
                                String idmag = magBD.getidMag(connexion, leMag.getNomMag()); // renvoie l'idmag au format str
                                List<Livre> lesLivresDuMag = new ArrayList<>();
                                //il nous faut la liste des livres de CE magasin
                                try {
                                    lesLivresDuMag = magBD.livresParMagasin(idmag);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            }

                            //"Recommandation"
                            case 2-> System.out.println("Fonction à Faire");

                            //"Passer Commande"
                            case 3-> System.out.println("Fonction à Faire");

                            //"Voir mes Commandes"
                            case 4-> System.out.println("Fonction à Faire");

                            //"Retour"
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

    public static Livre demanderLivre(Vendeur vend, List<Livre> lesLivresDuMag){
        Scanner scanner = new Scanner(System.in);
        List<String> listeTitreLivre= new ArrayList<>();
        for (Livre livre : lesLivresDuMag){
            String titreLivre = livre.getTitre();
            listeTitreLivre.add(titreLivre);
        }
        afficherMenu("Quel livre voulez-vous ?", listeTitreLivre);
        
        int choix = scanner.nextInt();
        int taille = listeTitreLivre.size();
        Livre livre = null;
        boolean flag = false;
        //System.out.println("AVANT LE WHILE");
        while (flag == false){
            //System.out.println(" le choix est : " + choix);
            if (choix-1 < taille && taille > 0){
                livre = lesLivresDuMag.get(choix-1);
                System.out.println("Le nom du livre : "+livre.getTitre());
                System.out.println(livre.getTitre());
                flag = true;
            }
        }
        return livre;

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
        
        boolean flag = false;
        //System.out.println("AVANT LE WHILE");
        while (flag == false){
            //System.out.println(" le choix est : " + choix);
            if (choix-1 < taille && taille > 0){
                aut = listAuteur.get(choix-1);
                System.out.println("Le nom de l'auteur : "+aut.getNom());
                System.out.println(aut.getNom());
                flag = true;
            }
        }
        return aut;
    }

    public static Magasin demanderMagasin(){ 
        Scanner scanner = new Scanner(System.in);
        List<String> listeRep= new ArrayList<>();
        listeRep.add("Veuillez entrer le nom du Magasin : ");
        afficherMenu("Quel est nom du Magasin ?", listeRep);
        String nom = scanner.nextLine();
        
        listeRep = new ArrayList<>();

        listeRep.add("Veuillez entrer le nom de la ville du Magasin: ");
        afficherMenu("Quel est le Magasin ?", listeRep);
        String ville = scanner.nextLine();

        Magasin mag = new Magasin(nom, ville);
        listeRep= new ArrayList<>();
        listeRep.add("D'accord ! Le Magasin s'appelle "+nom+",\n il est situé à " +
        ville + ".");
        afficherMenu("Le Magasins", listeRep);
        return mag;
    }

    public static Vendeur demanderVendeur(List<Vendeur> listeVend){
        Scanner scanner = new Scanner(System.in);
        List<String> listeNomVendeur = new ArrayList<>();
        for (Vendeur v : listeVend){ // erreur
            String nomV = v.getNom();
            listeNomVendeur.add(nomV);
        }

        afficherMenu("Quel Vendeur voulez vous", listeNomVendeur);
        
        int choix = scanner.nextInt();
        int taille = listeNomVendeur.size();
        Vendeur vend = null;
        
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

        public static Magasin demanderParmiMag(List<Magasin> listeDesMags){
            Scanner scanner = new Scanner(System.in);
            List<String> listeNomMag = new ArrayList<>();
            for (Magasin m : listeDesMags){
                String nomM = m.getNomMag();
                listeNomMag.add(nomM);
            }

            afficherMenu("Quel magasin voulez vous", listeNomMag);
            
            int choix = scanner.nextInt();
            int taille = listeNomMag.size();
            Magasin leMag = null;
            
            boolean flag = false;
            System.out.println("AVANT LE WHILE");
            while (flag == false){
                System.out.println(" le choix est : " + choix);
                if (choix-1 < taille && taille > 0){
                    leMag = listeDesMags.get(choix-1);
                    System.out.println("dans le while le vendeur est : "+leMag.getNomMag());
                    flag = true;
                }
            }
            //System.out.println(mag);
            return leMag;
        }


        public static int  demanderQte(){
            Scanner scanner = new Scanner(System.in);
            List<String> listeRep = new ArrayList<>();
            afficherMenu("Quelle est la nouvelle quantité ?", listeRep);
            int choix = scanner.nextInt();
            int rep = 0 ;
            if (choix < 0){
                System.out.println("Vous avez rentré un nombre négatif.\nNous avons donc mis le nombre de livres à zéro.");
            }
            else {
                if (choix == 0){
                    System.out.println("Nous avons mis le nombre de livres à zéro.");
                }
                else{
                    rep = choix;
                    System.out.println("Il y a "+ String.valueOf(rep)+ " livre(s) en stock.");
                    }
                }
                return rep;
            }

        /*
        public static Livre demanderLivreDisponible(MagasinBD magBD){
            Scanner scanner = new Scanner(System.in);
            List<String> listeRep= new ArrayList<>();
            listeRep.add("Veuillez entrer le titre du livre : ");
            afficherMenu("Quel est le livre ?", listeRep);
            String titre = scanner.nextLine();;
            listeRep= new ArrayList<>();

            Livre livre = new Livre(titre);
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
        */
    
}