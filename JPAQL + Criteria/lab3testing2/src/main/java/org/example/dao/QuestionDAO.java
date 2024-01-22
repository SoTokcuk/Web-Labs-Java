package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Question;
import org.example.entity.Question_;
import org.example.entity.Test;
import org.example.entity.Test_;

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
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Question> query = criteriaBuilder.createQuery(Question.class);
            Root<Question> root = query.from(Question.class);
            query.select(root)
                    .where(criteriaBuilder.equal(root.get(Question_.subject), subject));

            TypedQuery<Question> typedQuery = entityManager.createQuery(query);
            List<Question> questions = typedQuery.getResultList();

            logger.info("Retrieved questions by subject: " + subject);
            return questions;
        } catch (Exception e) {
            logger.error("Error getting question list by subject: " + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    public static List<Question> getQuestionsByTestID(int testID) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Question> query = criteriaBuilder.createQuery(Question.class);
            Root<Question> root = query.from(Question.class);

            Join<Question, Test> testJoin = root.join(Question_.test);
            query.select(root)
                    .where(criteriaBuilder.equal(testJoin.get(Test_.id), testID));

            TypedQuery<Question> typedQuery = entityManager.createQuery(query);
            List<Question> questions = typedQuery.getResultList();

            logger.info("Retrieved questions by testID: " + testID);
            return questions;
        } catch (Exception e) {
            logger.error("Error getting question list by testID: " + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    public static List<Question> getQuestions() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Question> query = criteriaBuilder.createQuery(Question.class);
            Root<Question> root = query.from(Question.class);
            query.select(root);

            TypedQuery<Question> typedQuery = entityManager.createQuery(query);
            List<Question> questions = typedQuery.getResultList();

            logger.info("Getting question list");
            return questions;
        } catch (Exception e) {
            logger.error("Error getting question list: " + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }

    public static void createQuestion(int testID, String description, String subject) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Test> testQuery = criteriaBuilder.createQuery(Test.class);
            Root<Test> testRoot = testQuery.from(Test.class);
            testQuery.select(testRoot).where(criteriaBuilder.equal(testRoot.get(Test_.id), testID));
            Test test = entityManager.createQuery(testQuery).getSingleResult();

            Question question = new Question();
            question.setTest(test);
            question.setDescription(description);
            question.setSubject(subject);

            entityManager.persist(question);
            entityManager.getTransaction().commit();
            logger.info("Question created");
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to create question", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}