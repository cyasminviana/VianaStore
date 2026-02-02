import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDAO {

    public Connection connectDB() throws SQLException {

        String host = "localhost";
        String port = "3306";
        String database = "vianastoredesktop"; 
        String user = "root";
        String password = "Fronteiras23.";

      String url = "jdbc:mysql://localhost:3306/vianastoredesktop?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(url, user, password);
    }
}
