import java.util.List;
import java.util.ArrayList;

public class DetailCommande{
    private int numDetailCommande;
    private Livre livre;
    private int qte;

    
    public int getNumDetailCommande() {
        return numDetailCommande;
    }
    public Livre getLivre() {
        return livre;
    }
    public int getQte() {
        return qte;
    }

    
    public DetailCommande(int numDetailCommande, Livre livre, int qte) {
        this.numDetailCommande = numDetailCommande;
        this.livre = livre;
        this.qte = qte;
    }

    

}