import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by gaohan on 3/27/17.
 */
public class DBUtil {
    public static Connection connection;
    private static final String URL = "localhost";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception{
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
