import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Client extends Personne {
    private int numeroClient;
    private String adresse;
    private int codePostal;
    private String ville;
    private List<Commande> commandes;

    public Client(String nom, String prenom, int numCli, String adresse, int codePostal, String ville) {
        super(nom, prenom);
        this.numeroClient = numCli;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.commandes= new ArrayList<>();
    }
    public int getNumeroClient() {
        return numeroClient;
    }
    public String getAdresse() {
        return adresse;
    }
    public int getCodePostal() {
        return codePostal;
    }
    public String getVille() {
        return ville;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public List<Commande> getCommandes() {
        return commandes;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Livre> tousLesLivresClient(){
        List<Livre> res = new ArrayList<>();
        for(Commande commande : this.commandes){
            for(Livre livre :commande.tousLesLivres()){
                if(!(res.contains(livre)))
            res.add(livre);
        }}
        return res;
    }

    public void commanderLivre(Livre livre, int qte, Magasin magasin, Commande commande, int nbPlusGrandDetailCommande){
        DetailCommande detailCommande = new DetailCommande(nbPlusGrandDetailCommande+1, livre, qte, commande.getNumCommande());
        commande.ajouterDetailCommande(detailCommande);
        //magasin.supprimerLivre(livre);
    }

    public List<Livre> onVousRecommande(Client client, List<Commande> toutesLesCommandes){
        List<Livre> recommandations= new ArrayList<>();
        Iterator<Commande> it = toutesLesCommandes.iterator();
        while(it.hasNext()){
            Commande commande = it.next();
            if(!(client.commandes.contains(commande))){//si la commande qu'on regarde n'est pas celle du client
                for(Livre livre : client.tousLesLivresClient()){// on parcourt chaque livre que le client à commandé
                    if(commande.tousLesLivres().contains(livre)){// si dans la commande qu'on regarde il y a le livre que le client a commandé (il faut donc lui recommander les livres de cette commande)
                        for(Livre livreCommande : commande.tousLesLivres() ){// pour chaque livre de la commande
                            if(!(client.tousLesLivresClient().contains(livreCommande))&& !(recommandations.contains(livreCommande))){//si le client n'a jamais commandé ce livre et qu'il n'est pas dans les recommandations
                                recommandations.add(livreCommande);// l'ajouter aux recommandations
                            }
                        }
                    }
                }
            }
        }
        return recommandations;
    }

    
}