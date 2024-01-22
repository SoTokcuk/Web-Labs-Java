package org.example.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.*;

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

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Test> testQuery = criteriaBuilder.createQuery(Test.class);
            Root<Test> testRoot = testQuery.from(Test.class);
            testQuery.select(testRoot).where(criteriaBuilder.equal(testRoot.get(Test_.id), testID));
            Test test = entityManager.createQuery(testQuery).getSingleResult();

            CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);
            Root<Student> studentRoot = studentQuery.from(Student.class);
            studentQuery.select(studentRoot).where(criteriaBuilder.equal(studentRoot.get(Student_.id), studentID));
            Student student = entityManager.createQuery(studentQuery).getSingleResult();

            Answer answer1 = new Answer();
            answer1.setTest(test);
            answer1.setStudent(student);
            answer1.setAnswer(answer);
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
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
            Root<Answer> root = query.from(Answer.class);

            Join<Answer, Student> studentJoin = root.join(Answer_.student);
            Join<Answer, Test> testJoin = root.join(Answer_.test);

            query.select(criteriaBuilder.count(root))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(studentJoin.get(Student_.id), studentId),
                            criteriaBuilder.equal(testJoin.get(Test_.id), testId),
                            criteriaBuilder.equal(root.get(Answer_.result), "correct")
                    ));

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        } finally {
            entityManager.close();
        }
    }

    public static void updateResult(int id, String result) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Answer> updateQuery = criteriaBuilder.createCriteriaUpdate(Answer.class);
            Root<Answer> root = updateQuery.from(Answer.class);

            updateQuery.set(root.get(Answer_.result), result)
                    .where(criteriaBuilder.equal(root.get(Answer_.id), id));

            int rowsAffected = entityManager.createQuery(updateQuery).executeUpdate();
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

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Answer> updateQuery = criteriaBuilder.createCriteriaUpdate(Answer.class);
            Root<Answer> root = updateQuery.from(Answer.class);

            updateQuery.set(root.get(Answer_.answer), answer)
                    .where(criteriaBuilder.equal(root.get(Answer_.id), id));

            int rowsAffected = entityManager.createQuery(updateQuery).executeUpdate();
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
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Answer> query = criteriaBuilder.createQuery(Answer.class);
            Root<Answer> root = query.from(Answer.class);

            query.select(root)
                    .where(
                            criteriaBuilder.equal(root.get(Answer_.student).get(Student_.id), studentID),
                            criteriaBuilder.equal(root.get(Answer_.test).get(Test_.id), testID)
                    );

            TypedQuery<Answer> typedQuery = entityManager.createQuery(query);
            List<Answer> resultList = typedQuery.getResultList();

            logger.info("Retrieved answers by studentID: " + studentID + " for the test: " + testID);
            return resultList;
        } catch (Exception e) {
            logger.error("Error getting answers: " + e.getMessage());
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }
}