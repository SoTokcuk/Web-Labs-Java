package main.java.dao;

import main.java.connector.ConnectionPool;
import main.java.entity.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuestionDAO {

    private static final String GET_QUESTIONS = "SELECT * FROM questions";
    private static final String GET_QUESTIONS_BY_TEST_ID = "SELECT * FROM questions WHERE test_id = ?";
    private static final String GET_QUESTIONS_BY_SUBJECT = "SELECT * FROM questions WHERE subject = ?";
    private static final String CREATE_QUESTION = "INSERT INTO questions(test_id, description, subject) VALUES(?, ?, ?)";

    private static final Logger logger = LogManager.getLogger(QuestionDAO.class);
    private static final ConnectionPool connectionPool = new ConnectionPool(10);

    public static List<Question> getQuestionsBySubject(String subject) throws DAOException {
        List<Question> questionList = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Retrieving questions by subject: {}", subject);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTIONS_BY_SUBJECT);
            preparedStatement.setString(1, subject);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int testID = resultSet.getInt("test_id");
                String desc = resultSet.getString("description");

                questionList.add(new Question(id, testID, desc, subject));
            }
            logger.info("Questions retrieved successfully by subject: {}", subject);
        } catch (SQLException e) {
            logger.error("Failed to get questions by subject", e);
            throw new DAOException("Failed to get questions by subject", e);
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
        return questionList;
    }

    public static List<Question> getQuestionsByTestID(int testID) throws DAOException {
        List<Question> questionList = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Retrieving questions by test ID: {}", testID);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTIONS_BY_TEST_ID);
            preparedStatement.setInt(1, testID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String desc = resultSet.getString("description");
                String subject = resultSet.getString("subject");

                questionList.add(new Question(id, testID, desc, subject));
            }
            logger.info("Questions retrieved successfully by test ID: {}", testID);
        } catch (SQLException e) {
            logger.error("Failed to get questions by test ID", e);
            throw new DAOException("Failed to get questions by test ID", e);
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
        return questionList;
    }

    public static List<Question> getQuestions() throws DAOException {
        List<Question> questionList = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Retrieving all questions");
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTIONS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int testID = resultSet.getInt("test_id");
                String desc = resultSet.getString("description");
                String subject = resultSet.getString("subject");

                questionList.add(new Question(id, testID, desc, subject));
            }
            logger.info("All questions retrieved successfully");
        } catch (SQLException e) {
            logger.error("Failed to get all questions", e);
            throw new DAOException("Failed to get all questions", e);
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
        return questionList;
    }

    public static void createQuestion(int testID, String description, String subject) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Creating question - Test ID: {}, Description: {}, Subject: {}", testID, description, subject);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUESTION);
            preparedStatement.setInt(1, testID);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, subject);
            preparedStatement.executeUpdate();
            logger.info("Question created successfully - Test ID: {}, Description: {}, Subject: {}", testID, description, subject);
        } catch (SQLException e) {
            logger.error("Failed to create question", e);
            throw new DAOException("Failed to create question", e);
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