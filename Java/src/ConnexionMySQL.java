import java.sql.*;

public class ConnexionMySQL {
	public ConnexionMySQL() throws ClassNotFoundException{
		// Chargement du driver MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	public void getConnection() throws SQLException {
		Connection c ;
		c=DriverManager.getConnection("jdbc:mysql://servinfo-maria:3306/DBmoisan","moisan","moisan");
	}

		
	
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}
}

