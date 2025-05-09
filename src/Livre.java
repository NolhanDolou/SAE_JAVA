import java.util.List;
import java.util.ArrayList;

public class Livre{
    private String titre;
    private Auteur auteur;
    private int nbDePages;
    private double prix;
    private List<Classification> themes;

    public String getTitre() {
        return titre;
    }
    public Auteur getAuteur() {
        return auteur;
    }
    public int getNbDePages() {
        return nbDePages;
    }
    public double getPrix() {
        return prix;
    }
    public List<Classification> getThemes() {
        return themes;
    }


}
