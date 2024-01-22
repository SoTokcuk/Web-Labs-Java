package org.example.controller;

import org.example.dao.AnswerDAO;
import org.example.dao.QuestionDAO;
import org.example.entity.Answer;
import org.example.entity.Question;
import org.example.entity.Test;

import java.util.List;

public class ConStudent {
    private int id;
    private String name;
    private int test_id;

    public ConStudent(int id, String name, int test_id) {
        this.id = id;
        this.name = name;
        this.test_id = test_id;
    }

    public void getQuestions() {
        List<Question> questions = QuestionDAO.getQuestionsByTestID(this.test_id);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    public void getAnswers() {
        List<Answer> answers = AnswerDAO.getStudentAnswers(this.id, this.test_id);
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public void changeAnswer(int id, String answer) {
        AnswerDAO.updateAnswer(answer, id);
    }

    public void createAnswer(String answer) {
        AnswerDAO.createAnswer(this.test_id, this.id, answer);
    }
}
