
public class Vendeur extends Personne {

    private Magasin magasin;

    public Vendeur(String nom, String prenom){
        super(nom, prenom);
        this.magasin=magasin;
    }

    public void ajouterLivre(String titre, Auteur auteur, int nbDePages, double prix, List<Classification> themes){
        Livre livre = new Livre(titre, auteur, nbDePages, prix, themes);
        this.magasin.getStock().add(livre);
    }

    public void majStock(Livre livre, int qte){
        if(qte>0){
            for(int i=0;i<qte;i++){
                this.ajouterLivre(livre.getTitre(), livre.getAuteur(), livre.getNbDePages(), livre.getPrix(), livre.getThemes());
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

    public boolean commandeClient(Livre livre, Client client, int qte){
        for(int i = 0; i<qte; i++){
            if(this.estDispo(livre, this.magasin)){
                client.commanderLivre(livre, this.magasin);}
            else{
                return false;
            }
        return true;}
    }

    public void transfertLivre(Livre livre, Magasin autreMagasin){
        autreMagasin.getStock().remove(livre);
        this.majStock(livre, 1);
    }
}