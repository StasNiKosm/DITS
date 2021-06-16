package dao;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;

@Component
public class DataBaseConnectionFactory {

    private static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String DATA_BASE_URL = "jdbc:postgresql://localhost/postgres";

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "stas";

    private static Properties properties = null;

    private DataBaseConnectionFactory() {

    }

    private static void init() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS_NAME);
        properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);
        Connection conn = DriverManager.getConnection(DATA_BASE_URL, properties);
        conn.close();
    }

    public static Connection getConnection() {
        try {
            if (properties == null)
                init();
            return DriverManager.getConnection(DATA_BASE_URL, properties);
        } catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
