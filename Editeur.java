public class Editeur {
    private String nom;
    private String prenomA;

    public Editeur(String nom, String prenomA) {
        this.nom = nom;
        this.prenomA = prenomA;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenomA() {
        return prenomA;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenomA(String prenomA) {
        this.prenomA = prenomA;
    }
}

