import java.sql.*;

/**
 * Project JDBC
 * Created by End on сент., 2019
 */
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skill";
        String user = "root";
        String pass = "parolmysql159";
        try {
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select course_name, " +
                        "count(course_name)/(month(max(subscription_date))-month(min(subscription_date))) as avg_per_month " +
                        "from purchaselist group by course_name;");
            System.out.println("Курс: - Среднее кол-во покупок в месяц:");
            while (resultSet.next()) {
                String courseName = resultSet.getString(1);
                double avgPurchase = Double.parseDouble(resultSet.getString(2));
                System.out.println(courseName + " - " + avgPurchase);
            }
            connection.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
