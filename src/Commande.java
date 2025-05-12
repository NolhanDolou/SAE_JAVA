import java.util.List;
import java.util.ArrayList;

public class Commande{
    private int numCommande;
    private List<DetailCommande> commandeFinale;
    
    public int getNumCommande() {
        return numCommande;
    }
    public List<DetailCommande> getCommandeFinale() {
        return commandeFinale;
    }
    public Commande(int numCommande) {
        this.numCommande = numCommande;
        this.commandeFinale = new ArrayList<>();
    }

    

}