import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private String nomMag ;
    private String villeMag;
    private String adresseMag;
    private List<Livre> stock;

    public Magasin( String nomMag, String villeMag, String adresseMag){
        this.nomMag = nomMag;
        this.villeMag = villeMag;
        this.adresseMag = adresseMag;
        this.stock = new ArrayList<>();
    }

    public String getNomMag() {
        return this.nomMag;
    }

    public String getAdresse() {
        return this.adresseMag;
    }

    public String getVilleMag() {
        return this.villeMag;
    }

    public List<Livre> getStock() {
        return this.stock;
    }

    public void ajouterLivre(Livre livre){
        this.stock.add(livre);
    }
    
    public void supprimerLivre(Livre livre){
        this.stock.remove(livre);
    }
    
    
    
}