import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendeurBD {
    Connection laConnexion;
	Statement st;
	VendeurBD(Connection laConnexion){
		this.laConnexion=laConnexion;
	}

	public String modifierLivre(String isbn, String idmag, Integer qte ){
		return null;
	}

	public void ajouterLivre(Livre livre) throws SQLException{
        String isbnStr = "";
		this.st = this.laConnexion.createStatement();
        ResultSet rs = this.st.executeQuery("SELECT MAX(isbn) max FROM livre;");
        while (rs.next()){
            String str=rs.getString("max");
            Long isbn = Long.parseLong(str);
            isbn++;
            isbnStr = Long.toString(isbn);
        }
        rs.close();
        st=laConnexion.createStatement();
        String req = "INSERT INTO livre VALUES(" +isbnStr+",'"+
        livre.getTitre() + "'," +
        String.valueOf(livre.getNbDePages()) + "," + 
		String.valueOf(livre.getDateparution())+ "," + 
        String.valueOf(livre.getPrix()) + ")";

        List<String> reqDeux = new ArrayList<>();
        for (Classification cl : livre.getThemes()){
            String reqClass = "INSERT INTO themes VALUES( "+ isbnStr + "," + cl.getIddewey() +")";
            reqDeux.add(reqClass);
        }
        // AJOUTER DANS LIVRE
        st.executeUpdate(req);

        // AJOUTER DANS CLASSIFICATION
        for (String lesReq : reqDeux){
            st.executeQuery(lesReq);
        }

        // AJOUTER DANS ECRIRE
        AuteurBD auteurBD = new AuteurBD(laConnexion);
        String reqEcr = "INSERT INTO ecrire VALUES(" + isbnStr +",'"+ auteurBD.getIdAuteur(livre.getAuteur().getNom())+"')";
        st.executeUpdate(reqEcr);

        // AJOUTER DANS EDITER
        ResultSet rsEdit = st.executeQuery("SELECT DISTINCT idedit FROM EDITEUR WHERE nomedit='" + livre.getEditeur().getNom()+"'");
		if(!rsEdit.next()){
			System.err.println("L'éditeur ne correspond à aucun éditeur de notre base de données");
		}else{
            int idedit = rsEdit.getInt("idedit");
            if(rsEdit.wasNull()){
                System.err.println("Le champ est null");
            }else{
			    String reqEdit = "INSERT INTO editer VALUES("+isbnStr +","+ String.valueOf(idedit)+")";
        	    st.executeUpdate(reqEdit);
            }
        }
        rsEdit.close();
	}

	public void modifierQuantiteLivreMagasin(String isbn, String idmag, int qte) throws SQLException{
		try{
			st=laConnexion.createStatement();
        	String req = "UPDATE POSSEDER SET qte = " + String.valueOf(qte) + " WHERE isbn = '" + isbn
        	+ "' AND idmag = '"+ idmag+"'";
            st.executeUpdate(req);
		}catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}

    public List<Vendeur> getListVendeurs(){
        String req = "SELECT * FROM vendeur";
        List<Vendeur> vendeurs = new ArrayList<>();
        try{
        Statement st = ConnectionBD.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            MagasinBD magBD = new MagasinBD(laConnexion);
            Magasin mag = magBD.idMagToMag(rs.getString("idmag"));
            Vendeur vendeur = new Vendeur(nom,prenom,mag);
            vendeurs.add(vendeur);
        }

    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des auteurs : " + e.getMessage());
    }
        return vendeurs;
    }

    public boolean verifierDisponibiliteLivre(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) AS nb FROM POSSEDER WHERE isbn = ? AND qte > 0";
        try (PreparedStatement pst = laConnexion.prepareStatement(sql)) {
            pst.setString(1, isbn);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int nb = rs.getInt("nb");
                return nb > 0; 
            } else {
                return false; 
            }
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la vérification de la disponibilité du livre : " + e.getMessage(), e);
        }
    }
}
