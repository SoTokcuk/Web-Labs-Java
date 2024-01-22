package org.example.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Test;
import org.example.entity.Test_;


import java.util.List;

public class TestDAO {

    private static final Logger logger = LogManager.getLogger(TestDAO.class);
    private static final String ENTITY_MANAGER_FACTORY_NAME = "my-persistence-unit";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);


    public static void createTest(String name) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Test> query = criteriaBuilder.createQuery(Test.class);
            Root<Test> root = query.from(Test.class);
            query.select(root);

            Test test = new Test();
            test.setName(name);

            entityManager.persist(test);
            entityManager.getTransaction().commit();
            logger.info("Test created: " + name);
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to create test", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public static List<Test> getTests() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Test> criteriaQuery = criteriaBuilder.createQuery(Test.class);
            Root<Test> root = criteriaQuery.from(Test.class);
            criteriaQuery.select(root);

            TypedQuery<Test> testTypedQuery = entityManager.createQuery(criteriaQuery);
            logger.info("Bringing up the test list");
            return testTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting test list: " + e);
        } finally {
            entityManager.close();
        }
        return null;
    }
}