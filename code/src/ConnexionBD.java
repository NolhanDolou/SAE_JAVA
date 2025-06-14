import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.*;

public class ConnexionBD {
    public static Connection getConnexion() {
        Properties props = new Properties();
        try (FileReader reader = new FileReader("./INFOOBLIGATOIRE.txt")) {
            props.load(reader);
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture de config.txt : " + e.getMessage());
            return null;
        }

        String host = props.getProperty("host");
        String port = props.getProperty("port");
        String database = props.getProperty("database");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnexion();
        if (conn != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec de la connexion.");
        }
    }
}