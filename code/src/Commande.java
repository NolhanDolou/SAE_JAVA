import java.util.ArrayList;
import java.util.List;

public class Commande{
    private int numCommande;
    private List<DetailCommande> commandeFinale;
    private boolean enligne;
    private Livraison livraison;
    private String reception; //l'adresse de livraison (donc le magasin ou le client)


    //si demande de livraison en magasin
    public Commande(int numCommande, boolean enligne, Livraison livraison, Magasin magasin) {
        this.numCommande = numCommande;
        this.commandeFinale = new ArrayList<>();
        this.enligne=enligne;
        this.livraison=livraison;
        this.reception = magasin.getAdresse();
    }

    public Commande(int numCommande, boolean enligne, Livraison livraison, Client client) {
        this.numCommande = numCommande;
        this.commandeFinale = new ArrayList<>();
        this.enligne=enligne;
        this.livraison=livraison;
        this.reception = client.getAdresse();
    }

    public int getNumCommande() {
        return numCommande;
    }

    public String getReception() {
        return this.reception;
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