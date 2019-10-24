import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=Universal&useLegacyDatetimeCode=false";
        String user = "root";
        String password = "gog69nik69";

        String query = "SELECT Courses.name AS 'Название курса', COUNT(*) AS 'Количество учащихся за месяц',\n" +
                "       CONCAT(MONTHNAME(registration_date), ' ', YEAR(registration_date)) AS 'Дата поступления' FROM Courses\n" +
                "           JOIN Subscriptions ON Courses.id = Subscriptions.course_id\n" +
                "           JOIN Students ON Students.id = Subscriptions.student_id\n" +
                "GROUP BY CONCAT(MONTHNAME(registration_date), ' ', YEAR(registration_date)), Courses.name";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            System.out.println("Курс: " + result.getString("Название курса") +
                    " в " + result.getString("Дата поступления") +
                    " покупали в среднем " + result.getString("Количество учащихся за месяц"));
        }
    }
}
