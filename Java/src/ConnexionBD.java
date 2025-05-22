import java.sql.*;

public class ConnexionBD {
    private Connection mysql=null;
    private boolean connecte =false;
	public ConnexionBD() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}

    public void connecter() throws SQLException {
		this.mysql = DriverManager.getConnection("jdbc:mysql://servinfo-maria:3306/DBmoisan","moisan" ,"moisan");
		// si tout s'est bien passé la connexion n'est plus nulle
		this.connecte=this.mysql !=null;
	}

	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}
    
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
}
