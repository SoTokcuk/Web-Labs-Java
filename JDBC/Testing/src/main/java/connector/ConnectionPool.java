package main.java.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private ConcurrentLinkedQueue<Connection> availableConns = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Connection> usedConns = new ConcurrentLinkedQueue<>();
    private String url;
    private String username;
    private String password;

    public ConnectionPool(int initConnCnt) {
        Properties props = getProperties();

        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("Failed to load JDBC driver", e);
        }

        this.url = url;
        this.password = password;
        this.username = username;

        for (int i = 0; i < initConnCnt; i++) {
            availableConns.offer(getConnection());
        }
    }

    private Properties getProperties() {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("C:\\Users\\user\\Documents\\lab2testingpart3\\src\\main\\java\\properties\\database.properties"))) {
            props.load(in);
        } catch (IOException e) {
            logger.error("Failed to load database properties", e);
            throw new RuntimeException(e);
        }
        return props;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            logger.error("Failed to create a new database connection", e);
        }
        return conn;
    }

    public Connection retrieve() throws SQLException {
        Connection newConn = availableConns.poll();
        if (newConn == null) {
            newConn = getConnection();
        }
        usedConns.offer(newConn);
        return newConn;
    }

    public void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.remove(c)) {
                availableConns.offer(c);
            } else {
                logger.error("Connection not in the usedConns queue");
                throw new NullPointerException("Connection not in the usedConns queue");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}