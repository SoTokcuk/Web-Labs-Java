package org.example.dao;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Answer;
import org.example.entity.Student;
import org.example.entity.Test;
import java.util.List;

public class AnswerDAO {
    private static final String ENTITY_MANAGER_FACTORY_NAME = "my-persistence-unit";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);
    private static final Logger logger = LogManager.getLogger(AnswerDAO.class);


    public static void createAnswer(int testID, int studentID, String answer) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            logger.info("Creating answer - Test ID: {}, Student ID: {}, Answer: {}", testID, studentID, answer);

            Answer answer1 = new Answer();
            Test test = new Test();
            Student student = new Student();

            answer1.setTest(test);
            answer1.setStudent(student);

            answer1.setAnswer(answer);
            answer1.setStudentId(studentID);
            answer1.setTestId(testID);
            answer1.setResult("unchecked");

            entityManager.persist(answer1);
            entityManager.getTransaction().commit();
            logger.info("Answer created successfully - Test ID: {}, Student ID: {}, Answer: {}", testID, studentID, answer);
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to create answer", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static long countRightAnswers(int studentId, int testId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager.createNamedQuery("COUNT_RIGHT_ANSWERS", Long.class)
                    .setParameter("id", studentId)
                    .setParameter("test_id", testId)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    public static void updateResult(int id, String result) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("UPDATE_RESULT");
            query.setParameter("id", id);
            query.setParameter("result", result);

            int rowsAffected = query.executeUpdate();
            if (rowsAffected > 0) {
                entityManager.getTransaction().commit();
                logger.info("Result updated successfully");
            } else {
                entityManager.getTransaction().rollback();
                logger.error("Something went wrong.");
            }
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to update result", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static void updateAnswer(String answer, int id) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("UPDATE_ANSWER");
            query.setParameter("id", id);
            query.setParameter("answer", answer);

            int rowsAffected = query.executeUpdate();
            if (rowsAffected > 0) {
                entityManager.getTransaction().commit();
                logger.info("Answer updated successfully");
            } else {
                entityManager.getTransaction().rollback();
                logger.error("Something went wrong.");
            }
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to update answer", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static List<Answer> getStudentAnswers(int studentID, int testID) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<Answer> answers = entityManager.createNamedQuery("GET_STUDENT_ANSWERS", Answer.class);
            answers.setParameter("test_id", testID);
            answers.setParameter("st_id", studentID);
            logger.info("Retrieved answers by studentID:" + studentID + " for the test: " + testID);
            return answers.getResultList();
        } catch (Error e) {
            logger.error("Error getting answers: " + e);
        } finally {
            entityManager.close();
        }
        return null;
    }
}