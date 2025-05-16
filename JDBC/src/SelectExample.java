import java.sql.*;

public class SelectExample {
   static final String DB_URL = "jdbc:mariadb://servinfo-maria:3306/DBdolou/Librairie";  // jdbc:mariadb://servinfo-maria/DBdolou  jdbc:mysql://localhost/TUTORIALSPOINT
   static final String USER = "dolou";
   static final String PASS = "dolou";
   static final String QUERY = "SELECT * FROM Employees";


   // servinfo-maria
	// DBDolou

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);) {
         // Extract data from result set
         while (rs.next()) {
            // Retrieve by column name
            System.out.print("ID: " + rs.getInt("id"));
            System.out.print(", Age: " + rs.getInt("age"));
            System.out.print(", First: " + rs.getString("first"));
            System.out.println(", Last: " + rs.getString("last"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}