import java.util.*;
import java.sql.*;

public class LivreBD {
    private Connection laConnexion;

    public LivreBD(Connection laConnection){
        this.laConnexion = laConnection;
    }

    public String getIsbn(Livre livre) throws SQLException{
        String isbn = "";
        Statement st = laConnexion.createStatement();
        String titre = livre.getTitre().replace("'", "''");
        String req = "SELECT DISTINCT isbn FROM livre WHERE titre = '" + titre +"'";
        ResultSet rsIsbn = st.executeQuery(req);
        while (rsIsbn.next()) {
            isbn = rsIsbn.getString("isbn");
        }
        return isbn;
    }
}
