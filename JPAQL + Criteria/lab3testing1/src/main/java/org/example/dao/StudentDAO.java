package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Student;
import org.example.entity.Test;


public class StudentDAO {
    private static final String ENTITY_MANAGER_FACTORY_NAME = "my-persistence-unit";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);


    public static void createStudent(String name) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Student student = new Student();
            student.setName(name);


            entityManager.persist(student);
            entityManager.getTransaction().commit();
            logger.info("Student created: " + student.getName());
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

    public static void setTestForStudent(int id, String name) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createNamedQuery("SET_TEST");
            query.setParameter("id", id);
            query.setParameter("name", name);

            int rowsAffected = query.executeUpdate();
            if (rowsAffected > 0) {
                entityManager.getTransaction().commit();
                logger.info("Test set successfully");
            } else {
                entityManager.getTransaction().rollback();
                logger.error("Something went wrong. Check the name of the student");
            }
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            logger.error("Failed to add drinks amount", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}