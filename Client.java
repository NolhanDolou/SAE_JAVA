import java.util.List;

public class Client extends Personne {
    private int numClient;

    public Client(int numClient, String nom, String prenom, String adresse, int codePostal, String ville) {
        super(nom, prenom, adresse, codePostal, ville);
        this.numClient = numClient;
    }

    public int getNumClient() {
        return numClient;
    }

    public boolean commanderLivre(Livre livre, int qte) {    
        return true;
    }
    public void reception() {
       
    }
    public List<Livre> consulterCatalogue() {
       
        return List.of();
    }
}