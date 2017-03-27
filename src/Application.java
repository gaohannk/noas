import java.sql.*;

/**
 * Created by gaohan on 3/25/17.
 */
public class Application {
    public int id;
    public DecisionStatus applicationStatus;
    public int submittedBy;

    public DecisionStatus getApplicationStatus(Connection connection) throws Exception {
        PreparedStatement getSafari =
                connection.prepareStatement
                        ("select s.name from Safari s where s.id = ? ");
        SQLWarning warning = getSafari.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            getSafari.setInt(1, id);
            ResultSet rs = getSafari.executeQuery();
            SQLWarning queryWarning = getSafari.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            while (rs.next()) {
                applicationStatus = DecisionStatus.valueOf(rs.getString(1));
            }
            throw new Exception("No applicatoin Status found for id " + id);
        } finally {
            getSafari.close();
        }
    }

}
