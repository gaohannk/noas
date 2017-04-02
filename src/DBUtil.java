import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaohan on 3/27/17.
 */
public class DBUtil {
    public static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/noas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        /*
            Test 1: A student pay application Fee for an application
         */
        System.out.println("Test 1: A student pay application Fee for an application");
        int userId = 5;
        Student student = new Student(userId);
        Double price = 50.0;
        int applicationId = 6;
        student.payApplicationFee(connection, userId, price, applicationId);

        /*
            Test 2: Get an application status
         */
        System.out.println("Test 2: Get an application status");
        applicationId = 3;
        Application app = new Application(applicationId);
        System.out.println(app.getDecisionStatus(connection));

        /*
            Test 3: An administrator get application statement for an application
         */
        System.out.println("Test 3: An administrator get application statement for an application");
        userId = 7;
        applicationId = 5;
        Administrator administrator = new Administrator(userId);
        administrator.getApplicationStatement(connection, applicationId);
        /*
            Test 4: Make a decision for an application
         */
        System.out.println(connection.isClosed());
        System.out.println("Test 4: Make a decision for an application");
        DecisionStatus decisionStatus = Enum.valueOf(DecisionStatus.class, "OFFER");
        administrator.makeDecision(connection, decisionStatus, applicationId);

        /*
            Test 5: A user update password
         */
        System.out.println("Test 5: A user update password");
        User user = new Administrator(userId);
        String password = "ilovedatabase";
        user.updatePassword(connection, password);

        /*
            Test 6: Check fee-paid status for an application
         */
        System.out.println("Test 6: Check fee-paid status for an application");
        applicationId = 2;
        app = new Application(applicationId);
        System.out.print(app.checkFeePaid(connection));
    }

}
