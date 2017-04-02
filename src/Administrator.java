import java.lang.reflect.Type;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gaohan on 3/25/17.
 */
public class Administrator extends User {
    public String deptartment;

    public Administrator(int id) {
        super(id);
    }

    public void getApplicationStatement(Connection connection, int applicationId) throws Exception {
        PreparedStatement getApplicationStatement =
                connection.prepareStatement
                        ("SELECT s.id, s.score, s.testName, s.statement, s.resume " +
                                "FROM Application a,Statement s " +
                                "WHERE a.id = ? " +
                                "AND a.id = s.partOf");
        SQLWarning warning = getApplicationStatement.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            getApplicationStatement.setInt(1, applicationId);
            ResultSet rs = getApplicationStatement.executeQuery();
            SQLWarning queryWarning = getApplicationStatement.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            while (rs.next()) {
                System.out.println("Statement Id: " + rs.getInt(1));
                System.out.println("Score : " + rs.getDouble(2));
                System.out.println("TestName : " + rs.getString(3));
                System.out.println("Statement : " + rs.getString(4));
                System.out.println("Resume : " + rs.getString(5));
                System.out.println("Application Id : " + applicationId);
            }
        } finally {
            getApplicationStatement.close();
        }
    }

    public void makeDecision(Connection connection, DecisionStatus decisionStatus, int applicationId) throws Exception {
        PreparedStatement makeDecision =
                connection.prepareStatement
                        ("INSERT INTO DecisionMake(madeBy,make) VALUE (?,?)");
        PreparedStatement updateDecisionStatus =
                connection.prepareStatement
                        ("UPDATE Application SET decisionStatus = ? where id = ?");
        SQLWarning warning = makeDecision.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            makeDecision.setInt(1, this.id);
            makeDecision.setInt(2, applicationId);
            int updateCount = makeDecision.executeUpdate();
            if (updateCount != 1) {
                throw new Exception("Update error when make decision for application" + applicationId);
            }

            updateDecisionStatus.setString(1, decisionStatus.toString());
            updateDecisionStatus.setInt(2, applicationId);
            updateCount = updateDecisionStatus.executeUpdate();
            if (updateCount != 1) {
                throw new Exception("Update error when make decision for application " + applicationId);
            }
        } finally {
            makeDecision.close();
            updateDecisionStatus.close();
        }
    }

}
