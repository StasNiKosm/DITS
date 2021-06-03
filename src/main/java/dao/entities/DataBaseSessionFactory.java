package dao.entities;

import java.sql.*;
import java.util.Properties;

public class DataBaseSessionFactory {

    public static void init() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/postgres";
            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","741AzxY321963");
            Connection conn = DriverManager.getConnection(url, props);

            conn.close();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
}
