import java.util.List;
 

 
public class AppLivreExpress {
 

    Magasin mag
 

    boolean quitter;
 

    private Librairie mag;
 

    private boolean quitter;
 

    private personne utilisateur;
 
    private Personne personne_selectionnee;
 
}
    AppLivreExpress(Magasin  mag) {
 

    AppLivreExpress(Librairie  mag) {
 
        this.mag = mag;
 
        this.quitter = false;
 
        this.utilisateur = null;
 
        this.personne_selectionnee = null;
 
        }
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
    }

        