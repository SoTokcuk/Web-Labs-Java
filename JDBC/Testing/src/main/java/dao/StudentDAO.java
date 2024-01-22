package main.java.dao;

import main.java.connector.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentDAO {

    private static final String CREATE_STUDENT = "INSERT INTO students(name) VALUES(?)";
    private static final String SET_TEST = "UPDATE students SET test_id = ? WHERE name =?";

    private static final Logger logger = LogManager.getLogger(StudentDAO.class);
    private static final ConnectionPool connectionPool = new ConnectionPool(10);

    public static void createStudent(String name) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Creating student: {}", name);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            logger.info("Student created successfully");
        } catch (SQLException e) {
            logger.error("Failed to create student", e);
            throw new DAOException("Failed to create student", e);
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

    public static void setTestForStudent(int id, String name) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Setting test for student: {} - Test ID: {}", name, id);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(SET_TEST);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            logger.info("Test set successfully for student: {} - Test ID: {}", name, id);
        } catch (SQLException e) {
            logger.error("Failed to set test for student", e);
            throw new DAOException("Failed to set test for student", e);
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
}