import java.util.ArrayList;
import java.util.List;

public class Livre{
    private String titre;
    private Auteur auteur;
    private int nbDePages;
    private double prix;
    private Editeur editeur;
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
    public Editeur getEditeur() {
        return editeur;
    }
    public Livre(String titre, Auteur auteur, int nbDePages, double prix, Editeur editeur) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbDePages = nbDePages;
        this.prix = prix;
        this.editeur = editeur;
        this.themes = new ArrayList<>();
    }

    public void ajouterThemes(Classification theme){
        this.themes.add(theme);
    }


}
