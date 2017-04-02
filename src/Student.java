import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLWarning;

/**
 * Created by gaohan on 3/25/17.
 */
public class Student extends User {
    public Address address;

    public Student(int id) {
        super(id);
    }

    public void payApplicationFee(Connection connection, int userId, Double price, int applicationId) throws Exception {
        PreparedStatement payApplicationFee =
                connection.prepareStatement
                        (" INSERT INTO ApplicationFee(price,paidBy,PaidFor ) VALUES (?,?,?) ");
        PreparedStatement updateApplicationFeeStatus =
                connection.prepareStatement
                        (" UPDATE Application SET isPaid = ?  WHERE id = ?");
        SQLWarning warning = payApplicationFee.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        warning = updateApplicationFeeStatus.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            payApplicationFee.setDouble(1, price);
            payApplicationFee.setInt(2, userId);
            payApplicationFee.setInt(3, applicationId);

            int updateCount = payApplicationFee.executeUpdate();
            SQLWarning queryWarning = payApplicationFee.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            System.out.println("Update Count is " + updateCount);

            updateApplicationFeeStatus.setBoolean(1, true);
            updateApplicationFeeStatus.setInt(2, applicationId);

            updateCount = updateApplicationFeeStatus.executeUpdate();
            queryWarning = payApplicationFee.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            System.out.println("Update Count is " + updateCount);
        } finally {
            payApplicationFee.close();
            updateApplicationFeeStatus.close();
        }
    }
}
