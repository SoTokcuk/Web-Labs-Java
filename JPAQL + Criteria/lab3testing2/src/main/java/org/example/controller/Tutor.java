package org.example.controller;



import org.example.dao.AnswerDAO;
import org.example.dao.QuestionDAO;
import org.example.dao.StudentDAO;
import org.example.dao.TestDAO;
import org.example.entity.Answer;
import org.example.entity.Question;
import org.example.entity.Test;

import java.util.List;

public class Tutor {

    public void createStudent(String name) {
        StudentDAO.createStudent(name);
    }

    public void administerTestToStudent(int testID, String name) {
        StudentDAO.setTestForStudent(testID, name);
    }
    public void getStudentAnswers(int studentID, int testID) {
        List<Answer> answers = AnswerDAO.getStudentAnswers(studentID, testID);
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public void estimateAnswer(int id, String mark) {
        AnswerDAO.updateResult(id, mark);
    }

    public void getStudentTestResult(int studentID, int testID) {
        long count = AnswerDAO.countRightAnswers(studentID, testID);
        System.out.println("Total right answers: " + count);
    }

    public  void createTest(String name) {
        TestDAO.createTest(name);
    }

    public void getTests() {
        List<Test> testCollection = TestDAO.getTests();
        for (Test test : testCollection) {
            System.out.println(test);
        }
    }

    public void createQuestion(int testID, String description, String subject) {
        QuestionDAO.createQuestion(testID, description, subject);
    }

    public void getQuestionList(int id) {
        List<Question> questions = QuestionDAO.getQuestionsByTestID(id);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    public void getQuestionsBySubject(String subject) {
        List<Question> questions = QuestionDAO.getQuestionsBySubject(subject);
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
