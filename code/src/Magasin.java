import java.sql.*;
import java.util.*;

public class Magasin {
    private String nomMag;
    private String villeMag;
    //private List<Livre> stock;

    public Magasin( String nomMag, String villeMag){
        this.nomMag = nomMag;
        this.villeMag = villeMag;
        //this.stock = new ArrayList<>();
    }

    public String getNomMag() {
        return this.nomMag;
    }

    public String getVilleMag() {
        return this.villeMag;
    }

    /*public List<Livre> getStock() {
        return this.stock;
    }

    public void ajouterLivre(Livre livre){
        this.stock.add(livre);
    }
    
    public void supprimerLivre(Livre livre){
        this.stock.remove(livre);
    }*/
}