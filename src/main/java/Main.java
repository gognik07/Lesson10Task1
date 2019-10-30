import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=Universal&useLegacyDatetimeCode=false";
        String user = "root";
        String password = "gog69nik69";

        String query = "SELECT nameCourse, AVG(countMonth) AS 'average' FROM (\n" +
                "         SELECT Courses.name AS 'nameCourse', COUNT(*) AS 'countMonth',\n" +
                "                CONCAT(MONTHNAME(registration_date), ' ', YEAR(registration_date)) AS 'dateRegistration' FROM Courses\n" +
                "                  JOIN Subscriptions ON Courses.id = Subscriptions.course_id\n" +
                "                  JOIN Students ON Students.id = Subscriptions.student_id\n" +
                "         GROUP BY CONCAT(MONTHNAME(registration_date), ' ', YEAR(registration_date)), Courses.name) AS tab\n" +
                "GROUP BY nameCourse";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
           // System.out.println("Курс " + result.getString("nameCourse") +
           //         " покупали в среднем " + result.getString("average") + " раз");
            System.out.printf("Курс %s покупали в среднем %.1f раз\n", result.getString("nameCourse"), Double.parseDouble(result.getString("average")));
        }
    }
}
