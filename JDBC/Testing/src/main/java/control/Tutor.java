package main.java.control;

import main.java.collection.TestCollection;
import main.java.dao.AnswerDAO;
import main.java.dao.QuestionDAO;
import main.java.dao.StudentDAO;
import main.java.dao.TestDAO;
import main.java.entity.Answer;
import main.java.entity.Question;
import main.java.entity.Student;
import main.java.entity.Test;
import main.java.exception.DAOException;

import java.util.List;

public class Tutor {
    public  void createTest(String name) throws DAOException {
        TestDAO.createTest(name);
    }

    public void createQuestion(int testID, String description, String subject) throws DAOException {
        QuestionDAO.createQuestion(testID, description, subject);
    }

    public void createStudent(String name) throws DAOException {
        StudentDAO.createStudent(name);
    }

    public void administerTestToStudent(int testID, String name) throws DAOException {
        StudentDAO.setTestForStudent(testID, name);
    }
    public void getStudentAnswers(String name, int testID) throws DAOException {
        List<Answer> answers = AnswerDAO.getStudentAnswers(name, testID);
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public void estimateAnswer(int id, String mark) throws DAOException {
        AnswerDAO.updateResult(id, mark);
    }

    public void getStudentTestResult(String name, int testID) throws DAOException {
        int count = AnswerDAO.countRightAnswers(name, testID);
        System.out.println("Total right answers: " + count);
    }

    public void getTests() throws DAOException {
        TestCollection testCollection = TestDAO.getTests();
        for (Test test : testCollection.getTests()) {
            System.out.println(test);
        }
    }

    public void getQuestionList(int id) throws DAOException {
        List<Question> questions = QuestionDAO.getQuestionsByTestID(id);
        for (Question question : questions) {
            System.out.println(question);
        }
    }
    public void getQuestionsBySubject(String subject) throws DAOException {
        List<Question> questions = QuestionDAO.getQuestionsBySubject(subject);
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
