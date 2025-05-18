import java.util.HashMap;
import java.util.Map;


public class Magasin {
    private String nomMag ;
    private String villeMag;
    private String adresseMag;
    private Map<Livre, Integer> stock;

    public Magasin( String nomMag, String villeMag, String adresseMag){
        this.nomMag = nomMag;
        this.villeMag = villeMag;
        this.adresseMag = adresseMag;
        this.stock = new HashMap<>();
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

    public Map<Livre, Integer> getStock() {
        return this.stock;
    }

    public String afficherStock(){
        String rep = " ";
        for (Map.Entry<Livre, Integer> entry : this.stock.entrySet()) {
            Livre livre = entry.getKey();
            Integer qte = entry.getValue();
            rep += livre.getTitre() + " de " + livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom() + " : " + qte + "\n";
        }
        return rep;
    }

    public void ajouterLivre(Livre livre, Integer qte){
        this.stock.putIfAbsent(livre, 0);
        this.stock.put(livre, qte);
    }
    
    public void supprimerLivre(Livre livre){
        this.stock.remove(livre);
    }
    
    
    
    
}