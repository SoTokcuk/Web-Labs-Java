package by.bsu.dao;

import by.bsu.entity.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

            Test test = new Test();
            test.setName(name);

            entityManager.persist(test);
            entityManager.getTransaction().commit();
            logger.info("Task created: " + test.getName());
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to create drink", e);
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
            TypedQuery<Test> testTypedQuery = entityManager.createNamedQuery("GET_TESTS", Test.class);
            logger.info("Bringing up the test list");
            return testTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting drink list: " + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }
}