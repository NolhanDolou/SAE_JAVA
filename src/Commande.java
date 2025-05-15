import java.util.List;
import java.util.ArrayList;

public class Commande{
    private int numCommande;
    private List<DetailCommande> commandeFinale;
    private boolean enligne;
    private Livraison livraison;
    
    public Commande(int numCommande, boolean enligne, Livraison livraison) {
        this.numCommande = numCommande;
        this.commandeFinale = new ArrayList<>();
        this.enligne=enligne;
        this.livraison=livraison;
    }

    public int getNumCommande() {
        return numCommande;
    }
    public List<DetailCommande> getCommandeFinale() {
        return commandeFinale;
    }
    

    public void ajouterDetailCommande(DetailCommande dc){
        this.commandeFinale.add(dc);
    }

    public boolean getEnligne() {
        return enligne;
    }
    public Livraison getLivraison() {
        return livraison;
    }

    public List<Livre> tousLesLivres(){
        List<Livre> lesLivres = new ArrayList<>();
        for(DetailCommande dc : this.commandeFinale){
            lesLivres.add(dc.getLivre());
        }
        return lesLivres;
    }
    
    public double prixTotal(){
        double prixTotal =0;
        for(DetailCommande dc : this.commandeFinale){
            prixTotal+= dc.getLivre().getPrix();
        }
        return prixTotal;
    }

}