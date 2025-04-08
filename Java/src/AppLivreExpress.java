import java.util.ArrayList;
import java.util.List;

public class AppLivreExpress {
    Magasin mag
    boolean quitter;
    private Personne personne_selectionnee;
    
    AppLivreExpress(Magasin  mag) {
        this.mag = mag;
        this.quitter = false;
        this.utilisateur = null;
        this.personne_selectionnee = null;
        }

    public static void restConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'effacement du terminal.");
        }
    }

    public void run() {
        bienvenue();
        boolean continuer = true;
        while(!quitter) {
            menu();
        }
        au_revoir();
        }
    
    public void menu() {
        boolean commande_faite = false;
        while(!commande_faite) {
            List<String> option = new ArrayList<>();
            option.add(" Q: quitter");
            option.add(" C: Client");
            option.add(" V: Vendeur");
            option.add(" A: Administrateur");
            option.add(" R: Rooteur ");
            menuAffiche("Menu principal" , option);
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if(commande.equals("q")) {
            quitter = true;
            commande_faite = true;
            } 
    
            else if(commande.equals("c")){
                menu_personne();
            }
    
            else if(commande.equals("v")){
                System.out.println(we.getDepenses());
            }
    
            else if(commande.equals("a")){
                System.out.println(we.totalDepenses());
            }
    
            else if(commande.equals("r")){
                System.out.println(we.depensesMoyenne());
            }
    
            else {
            System.out.println("Commande '" + commande_brute + "' invalide.");
            }
        }
    }
}