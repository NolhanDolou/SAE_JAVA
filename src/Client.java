public class Client extends Personne {
    private int numeroClient;
    private String adresse;
    private int codePostal;
    private String ville;

    public Client(String nom, String prenom, int numCli, String adresse, int codePostal, String ville) {
        super(nom, prenom);
        this.numeroClient = numCli;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
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
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    
}