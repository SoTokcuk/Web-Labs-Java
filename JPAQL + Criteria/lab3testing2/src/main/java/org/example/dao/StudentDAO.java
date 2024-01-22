package org.example.dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.Student;
import org.example.entity.Student_;
import org.example.entity.Test;
import org.example.entity.Test_;


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

    public static void setTestForStudent(int test_id, String name) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Student> update = criteriaBuilder.createCriteriaUpdate(Student.class);
            Root<Student> root = update.from(Student.class);

            update.set(root.get(Student_.test).get(Test_.id), test_id);

            update.where(criteriaBuilder.equal(root.get(Student_.name), name));

            int rowsAffected = entityManager.createQuery(update).executeUpdate();
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
            logger.error("Failed to set test for student", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}