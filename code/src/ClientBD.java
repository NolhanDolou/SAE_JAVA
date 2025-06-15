import java.sql.*;
import java.util.*;

public class ClientBD{
    ConnectionBD laConnexion;
	Statement st;
	ClientBD(ConnectionBD laConnexion){
		this.laConnexion = laConnexion;
	}

	public List<Livre> consulterCatalogue() throws SQLException {
	    List<Livre> livres = new ArrayList<>();
	
	    String sql = """
	        SELECT DISTINCT 
	            l.titre, l.nbpages, l.datepubli, l.prix,
	            a.nomauteur AS nomAuteur, a.prenomauteur AS prenomAuteur,
	            e.nomedit AS nomEditeur
	        FROM LIVRE l
	        JOIN POSSEDER p ON l.isbn = p.isbn
	        LEFT JOIN ECRIRE ea ON ea.isbn = l.isbn
	        LEFT JOIN AUTEUR a ON ea.idauteur = a.idauteur
	        LEFT JOIN EDITER ed ON ed.isbn = l.isbn
	        LEFT JOIN EDITEUR e ON ed.idedit = e.idedit
	        WHERE p.qte > 0
	    """;
	
	    try (Statement st = ConnectionBD.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {
			
	        while (rs.next()) {
	            String titre = rs.getString("titre");
	            int nbPages = rs.getInt("nbpages");
	            int datePubli = rs.getInt("datepubli");
	            double prix = rs.getDouble("prix");
	
	            String nomAuteur = rs.getString("nomAuteur");
	            String prenomAuteur = rs.getString("prenomAuteur");
	            Auteur auteur = new Auteur(nomAuteur, prenomAuteur);
	
	            String nomEditeur = rs.getString("nomEditeur");
	            Editeur editeur = new Editeur(nomEditeur);
	
	            Livre livre = new Livre(titre, auteur, datePubli, nbPages, prix, editeur);
	            livres.add(livre);
	        }
	
	    } catch (SQLException e) {
	        throw new SQLException("Erreur lors de la récupération du catalogue : " + e.getMessage(), e);
	    }
	
	    return livres;
	}
}
