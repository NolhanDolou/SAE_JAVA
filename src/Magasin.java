import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private String nom;
    private String ville;
    public List<Livre> stock;

    public Magasin(String nom, String adresse, int codePostal, String ville) {
        this.nom = nom;
        this.ville = ville;
        this.stock = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        stock.add(livre);
    }
}