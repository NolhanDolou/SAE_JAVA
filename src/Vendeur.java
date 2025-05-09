import java.util.List;

public class Vendeur extends Personne {

    private Magasin magasin;

    public Vendeur(String nom, String prenom){
        super(nom, prenom, adresse, codePostal, ville);
        this.magasin = magasin;
    }

    public void ajouterLivre(String titre, Auteur auteur, int nbDePages, double prix, List<Classification> themes){
        Livre livre = new Livre(titre, auteur, nbDePages, prix, themes);
        this.magasin.stock.add(livre);
    }

    public void majStock(Livre livre, int qte){
        if(qte>0){
            for(int i=0;i<qte;i++){
                this.ajouterLivre(livre.titre, livre.auteur, livre.nbDePages, livre.prix, livre.themes);
            }}
        else{
            for(int i=0;i>qte;i--){
                this.magasin.stock.remove(livre);}
        }
    } 

    public boolean estDispo(Livre livre, Magasin magasin){
        if(magasin.stock.contains(livre)){
            return true;
        }
        return false;
    }

    public boolean commandeClient(Livre livre, Client client, int qte){
        return client.commanderLivre(livre, qte);
    }

    public boolean transfertLivre(Livre livre, Magasin autreMagasin){
        autreMagasin.stock.remove(livre);
        this.majStock(livre, this.magasin);
    }
}