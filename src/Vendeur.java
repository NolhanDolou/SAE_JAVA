import java.util.List;

public class Vendeur extends Personne {

    private Magasin magasin;

    public Vendeur(String nom, String prenom, String adresse, int codePostal, String ville, Magasin magasin){
        super(nom, prenom, adresse, codePostal, ville);
        this.magasin=magasin;
    }

    public void ajouterLivre(String titre, Auteur auteur, int nbDePages, double prix, List<Classification> themes){
        Livre livre = new Livre(titre, auteur, nbDePages, prix, themes);
        magasin.stock.add(livre);
    }

    
}