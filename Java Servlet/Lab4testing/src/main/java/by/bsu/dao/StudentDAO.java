package by.bsu.dao;

import by.bsu.entity.Student;
import by.bsu.entity.Test;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class StudentDAO {
    private static final String ENTITY_MANAGER_FACTORY_NAME = "my-persistence-unit";
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);

    public static List<Student> getStudents() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<Student> testTypedQuery = entityManager.createNamedQuery("GET_STUDENTS", Student.class);
            logger.info("Bringing up the student list");
            return testTypedQuery.getResultList();
        } catch (Error e) {
            logger.error("Error getting student list: " + e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return null;
    }
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

            Student student = entityManager.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class)
                    .setParameter("name", name)
                    .getSingleResult();

            Test test = entityManager.find(Test.class, id);
            student.setTest(test);

            entityManager.merge(student);
            entityManager.getTransaction().commit();
            logger.info("Test set successfully");
        } catch (NoResultException e) {
            entityManager.getTransaction().rollback();
            logger.error("Student not found");
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