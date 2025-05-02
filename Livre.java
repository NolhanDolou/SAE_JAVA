import java.util.List;

public class Livre {
    private String titre;
    private int nbDePages;
    private int prixLivre;
    private Auteur auteur;
    private Editeur editeur;
    private List<Classification> classifications;

    public Livre(String titre, int nbDePages, int prixLivre, Auteur auteur, Editeur editeur, List<Classification> classifications) {
        this.titre = titre;
        this.nbDePages = nbDePages;
        this.prixLivre = prixLivre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.classifications = classifications;
    }

    public String getTitre() { return titre; }
    public int getNbDePages() { return nbDePages; }
    public int getPrixLivre() { return prixLivre; }
    public Auteur getAuteur() { return auteur; }
    public Editeur getEditeur() { return editeur; }
    public List<Classification> getClassifications() { return classifications; }

    public void setTitre(String titre) { this.titre = titre; }
    public void setNbDePages(int nbDePages) { this.nbDePages = nbDePages; }
    public void setPrixLivre(int prixLivre) { this.prixLivre = prixLivre; }
    public void setAuteur(Auteur auteur) { this.auteur = auteur; }
    public void setEditeur(Editeur editeur) { this.editeur = editeur; }
    public void setClassifications(List<Classification> classifications) { this.classifications = classifications; }
}
