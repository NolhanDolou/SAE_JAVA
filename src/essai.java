import java.sql.*;
public class essai{
public static void main(String[] args) throws SQLException{
    Connection c;
    try{
    Class.forName("com.mysql.jdbc.Driver");
    c=DriverManager.getConnection(
    "jdbc:mysql://servinfo-maria:3306/mabd",
    "monlog","monpass");
    //ICI CODE DU PROGRAMME
    }
    //gestion des exceptions
    catch (SQLException ex){
        System.out.println("Msg:"+ex.getMessage()+
        ex.getErrorCode());
    }
}
}
