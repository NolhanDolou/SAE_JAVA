public abstract class Personne{
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected int codePostal;
    protected String ville;

    public Personne(String nom, String prenom, String adresse, int codePostal, String ville){
        this.nom = nom;
        this.prenom = prenom;
        this.adresse  = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
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

}