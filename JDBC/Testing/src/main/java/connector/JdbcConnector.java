package main.java.connector;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


// Класс, который реализует подключение к бд

public class JdbcConnector {
    private JdbcConnector() {};

    private static Connection conn = null;
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                Properties props = new Properties();
                try(InputStream in = Files.newInputStream(Paths.get("C:\\Users\\user\\Documents\\lab2testing\\src\\main\\java\\properties\\database.properties"))){
                    props.load(in);
                }
                String url = props.getProperty("url");
                String username = props.getProperty("username");
                String password = props.getProperty("password");
                conn = DriverManager.getConnection(url, username, password);
            }
            catch (Exception ex) {
                System.out.println("Connection failed...");
                System.out.println(ex);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No connection exists");
        }
    }
}

