public class Auteur extends Personne  {
    String nom;
    String prenom;
    String adresse;
    int codePostal;
    String ville;

    public Auteur(String nom, String prenom, String adresse, int codePostal, String ville){
        super(nom, prenom);
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;

        
    }

}
