package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Question;
import org.example.entity.Test;
import java.util.List;

public class QuestionDAO {
    private static final String ENTITY_MANAGER_FACTORY_NAME = "my-persistence-unit";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);
    private static final Logger logger = LogManager.getLogger(QuestionDAO.class);
    //private static final String CREATE_QUESTION = "INSERT INTO questions(test_id, description, subject) VALUES(?, ?, ?)";

    public static List<Question> getQuestionsBySubject(String subject) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<Question> questionTypedQuery = entityManager.createNamedQuery("GET_QUESTIONS_BY_SUBJECT", Question.class);
            questionTypedQuery.setParameter("subject", subject);
            logger.info("Retrieved questions by subject:" + subject);
            return questionTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting question list by type: " + e);
        } finally {
            entityManager.close();
        }
        return null;
    }

    public static List<Question> getQuestionsByTestID(int testID) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<Question> questionTypedQuery = entityManager.createNamedQuery("GET_QUESTIONS_BY_TEST_ID", Question.class);
            questionTypedQuery.setParameter("id", testID);
            logger.info("Retrieved questions by testID:" + testID);
            return questionTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting question list by testID: " + e);
        } finally {
            entityManager.close();
        }
        return null;
    }

    public static List<Question> getQuestions() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<Question> questionTypedQuery = entityManager.createNamedQuery("GET_QUESTIONS", Question.class);
            logger.info("Getting question list");
            return questionTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting question list: " + e);
        } finally {
            entityManager.close();
        }
        return null;
    }

    public static void createQuestion(int testID, String description, String subject) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Question question = new Question();
            Test test = new Test();

            question.setTest(test);
            question.setTestID(testID);
            question.setDescription(description);
            question.setSubject(subject);

            entityManager.persist(question);
            entityManager.getTransaction().commit();
            logger.info("Student created");
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to create student", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}