import java.sql.*;
import java.util.*;

public class MagasinBD {
    private Connection laConnexion;

    MagasinBD(Connection laConnexion){
		this.laConnexion=laConnexion;
	}

    public String getidMag(Connection laConnexion, String nomMag) {
        String res = null;
        String req = "SELECT idmag FROM MAGASIN WHERE nommag = '" + nomMag + "';";
        try {
            Statement st = laConnexion.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                res = rs.getString("idmag");
            }
            rs.close();
            st.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Magasin> listeMagasins() throws SQLException {
    List<Magasin> magasins = new ArrayList<>();

    String sql = "SELECT nommag, villemag FROM MAGASIN ORDER BY idmag ";

    try (Statement st = ConnectionBD.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            String nomMag = rs.getString("nommag");
            String villeMag = rs.getString("villemag");

            Magasin magasin = new Magasin(nomMag, villeMag);
            magasins.add(magasin);
        }

    } catch (SQLException e) {
        throw new SQLException("Erreur lors de la récupération de la liste des magasins : " + e.getMessage(), e);
    }

    return magasins;
    }

    public Magasin idMagToMag(String idMag) throws SQLException {
    String sql = "SELECT nommag, villemag FROM MAGASIN WHERE idmag = ?";
    try (PreparedStatement pst = ConnectionBD.getConnexion().prepareStatement(sql)) {
        pst.setString(1, idMag);
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String nom = rs.getString("nommag");
                String ville = rs.getString("villemag");
                return new Magasin(nom, ville);
            } else {
                throw new SQLException("Aucun magasin trouvé avec l'ID : " + idMag);
            }
        }
    } catch (SQLException e) {
        throw new SQLException("Erreur lors de la récupération du magasin : " + e.getMessage(), e);
    }
}

}
