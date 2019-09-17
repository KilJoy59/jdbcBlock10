import java.sql.*;

/**
 * Project JDBC
 * Created by End on сент., 2019
 */
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skill";
        String user = "root";
        String pass = "parolmysql59";
        try {
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select course_name, count(subscription_date)/6 as avgPurchase from purchaselist group by course_name;");
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
