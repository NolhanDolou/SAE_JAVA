import java.util.List;
import java.util.ArrayList;

public class Classification {
    private String theme;
    private List<Livre> livresGenre;

    public Classification(String theme){
        this.theme=theme;
        this.livresGenre=new ArrayList<>();
    }

    public String getTheme() {
        return this.theme;
    }

    public List<Livre> getLivresGenre() {
        return livresGenre;
    }

    public void ajouterLivreGenre(Livre livre){
        this.livresGenre.add(livre);
    }
}