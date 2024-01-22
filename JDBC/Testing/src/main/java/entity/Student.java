package main.java.entity;

import main.java.dao.AnswerDAO;
import main.java.dao.QuestionDAO;
import main.java.exception.DAOException;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private int testID;

    public void getQuestions() throws DAOException {
        List<Question> questions = QuestionDAO.getQuestionsByTestID(this.getTestID());
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    public void getAnswers() throws DAOException {
        List<Answer> answers = AnswerDAO.getStudentAnswers(this.getName(), this.getTestID());
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public void changeAnswer(int id, String answer) throws DAOException {
        AnswerDAO.updateAnswer(answer, id);
    }

    public void createAnswer(String answer) throws DAOException {
        AnswerDAO.createAnswer(this.getTestID(), this.getId(), answer);
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    @Override
    public String toString() {
        if(this.getTestID() == 0) {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", testID: there are no tests for this student"  +
                    '}';
        }
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testID=" + testID +
                '}';
    }
}
