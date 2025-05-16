import java.util.List;

public class Vendeur extends Personne {

    private Magasin magasin;

    public Vendeur(String nom, String prenom,Magasin magasin){
        super(nom, prenom);
        this.magasin=magasin;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void ajouterLivre(String titre, Auteur auteur, int nbDePages, double prix, List<Classification> themes, Editeur editeur){
        Livre livre = new Livre(titre, auteur, nbDePages, prix, editeur);
        for(Classification theme : themes){
        livre.ajouterThemes(theme);}
        for(Classification theme : themes){
        theme.ajouterLivreGenre(livre);}
        this.magasin.getStock().add(livre);
    }

    public void majStock(Livre livre, int qte){
        if(qte>0){
            for(int i=0;i<qte;i++){
                this.ajouterLivre(livre.getTitre(), livre.getAuteur(), livre.getNbDePages(), livre.getPrix(), livre.getThemes(),livre.getEditeur());
            }}
        else{
            for(int i=0;i>qte;i--){
                this.magasin.getStock().remove(livre);}
        }
    } 

    public boolean estDispo(Livre livre, Magasin magasin){
        if(magasin.getStock().contains(livre)){
            return true;
        }
        return false;
    }

    public boolean commandeClient(Livre livre, Client client, int qte,Commande commande){
        int nbtrue =0;
        for(int i = 0; i<qte; i++){
            if(this.estDispo(livre, this.magasin)){
                client.commanderLivre(livre, qte, this.magasin,commande );
            nbtrue+=1;}
            }
        if(nbtrue == qte){
        return true;}
        return false;
    }

    public void transfertLivre(Livre livre, Magasin autreMagasin){
        autreMagasin.getStock().remove(livre);
        this.majStock(livre, 1);
    }
}