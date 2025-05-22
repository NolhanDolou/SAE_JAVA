import java.sql.*;

public class VendeurBD {
    ConnexionBD laConnexion;
	Statement st;
	VendeurBD(ConnexionBD laConnexion){
		this.laConnexion=laConnexion;
	}

	public String modifierLivre(String isbn, String idmag, Integer qte ){
		String requete = "UPDATE livre SET prix = 20 WHERE id = 1";
		try {
			st = laConnexion.getConnection().createStatement();
			int rowsAffected = st.executeUpdate(requete);
			if (rowsAffected > 0) {
				return "Livre modifié avec succès.";
			} else {
				return "Aucun livre trouvé avec cet ID.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Erreur lors de la modification du livre.";
		} finally {
			try {
				if (st != null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
