package main.java.dao;

import main.java.connector.ConnectionPool;
import main.java.entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnswerDAO {

    private static final String GET_STUDENT_ANSWERS = "SELECT answers.id, students.name, answers.answer, answers.result FROM answers JOIN students ON students.id = answers.student_id WHERE students.name = ? AND answers.test_id = ?";
    private static final String UPDATE_ANSWER = "UPDATE answers SET answer = ? WHERE id = ?";
    private static final String UPDATE_RESULT = "UPDATE answers SET result = ? WHERE id = ?";
    private static final String COUNT_RIGHT_ANSWERS = "SELECT COUNT(*) AS correct_count FROM answers JOIN students ON students.id = answers.student_id WHERE students.name = ? AND answers.test_id = ? AND answers.result = 'correct'";
    private static final String CREATE_ANSWER = "INSERT INTO answers (test_id, student_id, answer) VALUES (?, ?, ?)";

    private static final Logger logger = LogManager.getLogger(AnswerDAO.class);
    private static final ConnectionPool connectionPool = new ConnectionPool(10);

    public static void createAnswer(int testID, int studentID, String answer) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Creating answer - Test ID: {}, Student ID: {}, Answer: {}", testID, studentID, answer);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ANSWER);
            preparedStatement.setInt(1, testID);
            preparedStatement.setInt(2, studentID);
            preparedStatement.setString(3, answer);
            preparedStatement.executeUpdate();
            logger.info("Answer created successfully - Test ID: {}, Student ID: {}, Answer: {}", testID, studentID, answer);
        } catch (SQLException e) {
            logger.error("Failed to create answer", e);
            throw new DAOException("Failed to create answer", e);
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

    public static int countRightAnswers(String name, int testID) throws DAOException {
        int count = 0;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Counting right answers - Name: {}, Test ID: {}", name, testID);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_RIGHT_ANSWERS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, testID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("correct_count");
            }
            logger.info("Right answers counted successfully - Name: {}, Test ID: {}, Count: {}", name, testID, count);
        } catch (SQLException e) {
            logger.error("Failed to count right answers", e);
            throw new DAOException("Failed to count right answers", e);
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
        return count;
    }

    public static void updateResult(int id, String result) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Updating result - Answer ID: {}, Result: {}", id, result);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESULT);
            preparedStatement.setString(1, result);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            logger.info("Result updated successfully - Answer ID: {}, Result: {}", id, result);
        } catch (SQLException e) {
            logger.error("Failed to update result", e);
            throw new DAOException("Failed to update result", e);
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

    public static void updateAnswer(String answer, int id) throws DAOException {
        Connection connection = null;
        try {
            logger.info("Updating answer - Answer ID: {}, Answer: {}", id, answer);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ANSWER);
            preparedStatement.setString(1, answer);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            logger.info("Answer updated successfully - Answer ID: {}, Answer: {}", id, answer);
        } catch (SQLException e) {
            logger.error("Failed to update answer", e);
            throw new DAOException("Failed to update answer", e);
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

    public static List<Answer> getStudentAnswers(String name, int testID) throws DAOException {
        List<Answer> answerList = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            logger.info("Getting student answers - Name: {}, Test ID: {}", name, testID);
            connection = connectionPool.retrieve();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_ANSWERS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, testID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String answer = resultSet.getString("answer");
                String result = resultSet.getString("result");
                answerList.add(new Answer(id, name, "test id: " + Integer.toString(testID), answer, result));
            }
            logger.info("Student answers retrieved successfully - Name: {}, Test ID: {}", name, testID);
        } catch (SQLException e) {
            logger.error("Failed to get student answers", e);
            throw new DAOException("Failed to get student answers", e);
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
        return answerList;
    }
}