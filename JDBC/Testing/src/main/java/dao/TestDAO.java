package main.java.dao;

import main.java.builder.TestBuilder;
import main.java.collection.TestCollection;
import main.java.connector.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDAO {

    private static final String GET_TESTS = "SELECT * FROM tests";
    private static final String CREATE_TEST = "INSERT INTO tests(name) VALUES(?)";

    private static final Logger logger = LogManager.getLogger(TestDAO.class);
    private static final ConnectionPool connectionPool = new ConnectionPool(10);

    public static void createTest(String name) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Creating test: {}", name);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TEST);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            logger.info("Test created successfully");
        } catch (SQLException e) {
            logger.error("Failed to create test", e);
            throw new DAOException("Failed to create test", e);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.putback(connection);
                } catch (NullPointerException e) {
                    logger.error("Failed to put back connection to the pool", e);
                    throw new DAOException("Failed to put back connection to the pool", e);
                }
            }
        }
    }

    public static TestCollection getTests() throws DAOException {
        TestCollection testCollection = new TestCollection();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Retrieving tests");
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TESTS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                testCollection.addTest(new TestBuilder().setID(id).setName(name).build());
            }
            logger.info("Tests retrieved successfully");
        } catch (SQLException e) {
            logger.error("Failed to get tests", e);
            throw new DAOException("Failed to get tests", e);
        } finally {
            if (connection != null) {
                try {
                    connectionPool.putback(connection);
                } catch (NullPointerException e) {
                    logger.error("Failed to put back connection to the pool", e);
                    throw new DAOException("Failed to put back connection to the pool", e);
                }
            }
        }
        return testCollection;
    }
}