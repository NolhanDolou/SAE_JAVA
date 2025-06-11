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
    public void commanderLivre(Livre livre, int qte, Magasin magasin, Commande commande){
        DetailCommande detailCommande = new DetailCommande(commande.getCommandeFinale().size()+1,livre, qte);
        commande.ajouterDetailCommande(detailCommande);
        magasin.getStock().remove(livre);
    }

    public List<Livre>  onVousRecommande(Client client){
        List<Livre> res= new ArrayList<>();
        //List<Commande> toutesLesCommandes = new ArrayList<>();
        if(toutesLesCommandes.hasNext()){
            toutesLesCommandes.next();
            if(!(client.commandes.contains(commande))){
                for(Livre livre : client.tousLesLivresClient()){
                    if(commande.tousLesLivres().contains(livre)){
                        if(!(res.contains(livre))){
                            res.add(livre);
                        }
                    }
                }
            }
        }
        return res;
         /*list livre res 
         *
         *commande.hasNext 
         * commande next ->
         * if commande n'est pas dans les commandes du client :
         *      for(livre in client.tousleslivresclient)
         *          if commande.tousleslivres.contains(livre)
         *              if ! res.contains(livre)
         *                  res.add(livre)
         * 
         */
    }
    
}