import java.util.List;
import java.util.ArrayList;

public class Auteur extends Personne{
    private List<Livre> livreEcrits;

    public Auteur(String nom, String prenom){
        super(nom,prenom);
        this.livreEcrits=new ArrayList<>();
    }

    public Auteur(String nom){
        super(nom);
        this.livreEcrits=new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public List<Livre> getLivreEcrits() {
        return livreEcrits;
    }

    public void ajouterLivreEcrit(Livre livre){
        this.livreEcrits.add(livre);
    }

    public String toString(){
        return this.nom;
    }

}