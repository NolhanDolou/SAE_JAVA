import java.util.ArrayList;
import java.util.List;

public class Commande{
    private int numCommande;
    private List<DetailCommande> commandeFinale;
    private boolean enligne;
    private Livraison livraison;
    private String reception; //l'adresse de livraison (donc le magasin ou le client)
    private Client client;
    private Magasin magasin;


    //si demande de livraison en magasin
    public Commande(int numCommande, boolean enligne, Livraison livraison, Magasin magasin, Client client) {
        this.numCommande = numCommande;
        this.commandeFinale = new ArrayList<>();
        this.enligne=enligne;
        this.livraison=livraison;
        if (livraison == Livraison.MAGASIN){
            this.reception = magasin.getAdresse();
        } else {
            this.reception = client.getAdresse();
        }
        this.client = client;
        this.magasin = magasin;
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
            prixTotal+= dc.getLivre().getPrix()*dc.getQte();
        }
        return prixTotal;
    }

    public String editerFacture(){
        String facture = "";
        facture += "Commande n°" + this.numCommande + "\n";
        facture += "\n";
        facture += "Numéro de client : " + this.client.getNumeroClient() + "\n";
        facture += "Client : " + this.client.getNom() + " " + this.client.getPrenom() + "\n" + "\n";
        if (this.enligne){
            facture += "Commande en ligne sur le magasin " + this.magasin.getNomMag() + "\n";
        } else {
            facture += "Commande en magasin au magasin " + this.magasin.getNomMag() + "\n";
        }
        if (this.livraison == Livraison.DOMICILE){
            facture += "Livraison à domicile\n";
        } else {
            facture += "Retrait en magasin\n";
        }
        facture += "Adresse de livraison: " + this.reception + "\n";
        facture += "Commande en ligne: " + (this.enligne ? "Oui" : "Non") + "\n" + "\n";
        facture += "Détails de la commande:\n";

        for(DetailCommande dc : this.commandeFinale){
            facture += dc.toString() + "\n";
        }

        facture += "Prix total: " + this.prixTotal() + " €\n";

        return facture;
    }

}