import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.*;

public class ConnectionBD {
    private static Connection connexion = null;
    public static Connection getConnexion() {
        if (connexion != null) return connexion;

        Properties props = new Properties();
        try (FileReader reader = new FileReader("./INFOOBLIGATOIRE.txt")) {
            props.load(reader);
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture de INFOOBLIGATOIRE.txt : " + e.getMessage());
            return null;
        }

        String host = props.getProperty("host");
        String port = props.getProperty("port");
        String database = props.getProperty("database");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

        try {
            connexion = DriverManager.getConnection(url, user, password);
            return connexion;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }

    public static Statement createStatement() {
        try {
            Connection conn = getConnexion();
            if (conn != null) {
                return conn.createStatement();
            } else {
                System.out.println("Connexion non disponible pour créer un Statement.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
            return null;
        }
    }
}