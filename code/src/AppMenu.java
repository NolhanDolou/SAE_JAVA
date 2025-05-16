import java.util.List;
import java.util.Scanner;

public class AppMenu{

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void afficherMenu(String titre, List<String> options) {
        clear();
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
        System.out.println("║" + centrerTexte(titre, largeur) + " ║");
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
        return " ".repeat(Math.max(0, padding)) + texte + " ".repeat(Math.max(0, padding));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> options = List.of(
            "Administrateur",
            "Vendeur",
            "Client",
            "Quitter"
        );

        int choix;
        do {
            afficherMenu("Appli Random", options);
            System.out.print("Votre choix : ");

            while (!scanner.hasNextInt()) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.next();
                System.out.print("Votre choix : ");
            }

            choix = scanner.nextInt();

            switch (choix) {
                case 1 -> System.out.println("👋 Bonjour !");
                case 2 -> {
                    System.out.print("Nombre 1 : ");
                    int a = scanner.nextInt();
                    System.out.print("Nombre 2 : ");
                    int b = scanner.nextInt();
                    System.out.println("Résultat : " + (a + b));
                }
                case 3 -> System.out.println("💡 \"La simplicité est la sophistication suprême.\" – Léonard de Vinci");
                case 4 -> System.out.println("👋 Au revoir !");
                default -> System.out.println("❌ Option invalide.");
            }

            System.out.println(); // espace

        } while (choix != 4);

        scanner.close();
    }
}