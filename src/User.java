import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gaohan on 3/25/17.
 */
public abstract class User {
    public int id;
    public String password;
    public String name;

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updatePassword(Connection connection,String password) throws SQLException {
        PreparedStatement updatePassword =
                connection.prepareStatement
                        (" update User set password= ?  where id = ?");
        try {
            updatePassword.setString(1, password);
            updatePassword.setInt(2, this.id);
            int updateCount = updatePassword.executeUpdate();
            System.out.println("Update Count is " + updateCount);
        } finally {
            updatePassword.close();
        }
    }
}
