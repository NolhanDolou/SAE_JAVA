import java.util.List;
import java.util.Scanner;

public class AppMenu{

    //public static void clear() {
    //    System.out.print("\033[H\033[2J");
    //    System.out.flush();
    //}

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
            "Modifier Un Livre",
            "Vérifier Disponibilité",
            "Commander du stock",
            "Transférer un livre",
            "Quitter"
        );
        List<String> admin = List.of(
            "Créer un compte vendeur",
            "Ajouter une nouvelle librairie",
            "Gérer les stocks",
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
                        afficherMenu("Accès Admin", admin);
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

                //Boucle Vendeur
                case 2 -> {
                    do {
                        afficherMenu("Accès Vendeur", vendeur);
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
}
