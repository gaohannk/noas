import java.sql.*;

/**
 * Created by gaohan on 3/25/17.
 */
public class Application {
    public int id;
    public DecisionStatus decisionStatus;
    public int submittedBy;
    public boolean checkFeePaid;

    public Application(int id) {
        this.id = id;

    }

    public DecisionStatus getDecisionStatus(Connection connection) throws Exception {
        PreparedStatement getDecisionStatus =
                connection.prepareStatement
                        ("SELECT a.decisionStatus FROM Application a WHERE a.id = ? ");
        SQLWarning warning = getDecisionStatus.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            getDecisionStatus.setInt(1, id);
            ResultSet rs = getDecisionStatus.executeQuery();
            SQLWarning queryWarning = getDecisionStatus.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            while (rs.next()) {
                decisionStatus = DecisionStatus.valueOf(DecisionStatus.class, rs.getString("decisionStatus").toUpperCase());
            }
            throw new Exception("No application status found for id " + id);
        } finally {
            getDecisionStatus.close();
            return decisionStatus;
        }
    }


    public Boolean checkFeePaid(Connection connection) throws Exception {
        PreparedStatement checkFeePaid =
                connection.prepareStatement
                        ("SELECT a.isPaid FROM Application a WHERE a.id = ? ");
        SQLWarning warning = checkFeePaid.getWarnings();
        while (warning != null) {
            System.err.println("Database warning: " + warning);
        }
        try {
            checkFeePaid.setInt(1, id);
            ResultSet rs = checkFeePaid.executeQuery();
            SQLWarning queryWarning = checkFeePaid.getWarnings();
            while (warning != null) {
                System.err.println("Query warning: " + queryWarning);
            }
            while (rs.next()) {
                return rs.getBoolean(1);
            }
            throw new Exception("No check result found for application" + id);
        } finally {
            checkFeePaid.close();
        }
    }

//    public boolean checkFeePaid(Connection connection) throws Exception {
//        return true;
//    }
//
//    /**
//     * Add a new application to Applicant
//     *
//     * @param student    student.name.
//     * @param statement
//     * @param connection The database connection.
//     * @throws Exception If the student doesn't upload this applicant.
//     */
//    public void addApplicant(Connection connection, String student, String statement)
//            throws Exception {
//        PreparedStatement addApplicant = connection.prepareStatement
//                ("insert into Applicant(submit, isSubmitBy, number)" +
//                        "select a.id, stu.id, ? " +
//                        "from Application a, Student stu, Statement sta " +
//                        "where stu.name = ? " +
//                        "and sta.testName = ?" +
//                        "and sta.resume = ?" +
//                        "and sta.transc = ?" +
//                        "and sta.score = ?");
//        SQLWarning warning = addApplicant.getWarnings();
//        while (warning != null) {
//            System.err.println("Database warning: " + warning);
//        }
//        try {
//            addApplicant.setInt(1, number);
//            addApplicant.setString(2, student);
//            addApplicant.setString(3, statement);
//
//            int updateCount = addApplicant.executeUpdate();
//            SQLWarning updateWarning = addApplicant.getWarnings();
//            while (updateWarning != null) {
//                System.err.println("Update warning: " + updateWarning);
//            }
//            while (rs.next()) {
//                return rs.getInt(1);
//            }
//            if (updateCount != 1) {
//                throw new Exception("there is no such student" + student +
//                        "upload this applicant" + statement + number);
//            }
//        } finally {
//            addApplicant.close();
//        }
//    }
//}
}
