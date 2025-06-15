import java.sql.*;
import java.util.*;

public class AuteurBD{
    Connection laConnexion;
	Statement st;
	AuteurBD(Connection laConnexion){
		this.laConnexion = laConnexion;
	}

	public String getIdAuteur(String nomprenom) throws SQLException {
        String sql = "SELECT idauteur FROM AUTEUR WHERE nomauteur = ?";

        try (PreparedStatement pst = ConnectionBD.getConnexion().prepareStatement(sql)) {
            pst.setString(1, nomprenom);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("idauteur");
                } else {
                    throw new SQLException("Auteur non trouvé pour : " + nomprenom);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la récupération de l'id de l'auteur : " + e.getMessage(), e);
        }
    }

	public List<Auteur> listeAuteurs() throws SQLException {
    List<Auteur> auteurs = new ArrayList<>();

    String sql = "SELECT nomauteur FROM AUTEUR";

    try{
        Statement st = ConnectionBD.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String nom = rs.getString("nomauteur");
            //System.out.println(nom);
            //String prenom = rs.getString("prenomauteur");

            //Auteur auteur = new Auteur(nom, prenom);
            Auteur auteur = new Auteur(nom);
            auteurs.add(auteur);
        }

    } catch (SQLException e) {
        throw new SQLException("Erreur lors de la récupération des auteurs : " + e.getMessage(), e);
    }
    System.out.println(auteurs);
    return auteurs;
}
}