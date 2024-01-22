package org.example;

import org.example.controller.ConStudent;
import org.example.controller.Tutor;
import org.example.dao.QuestionDAO;
import org.example.entity.Question;


public class App 
{
    public static void main( String[] args )
    {
        Tutor tutor = new Tutor();
        tutor.getStudentTestResult(1, 1);
        System.out.println("Getting questions by subject math: ");
        tutor.getQuestionsBySubject("math");
        System.out.println("Getting questions for the test 1");
        tutor.getQuestionList(1);
        System.out.println("Getting student answers");
        tutor.getStudentAnswers(1, 1);
        System.out.println("Getting test list");
        tutor.getTests();
        System.out.println();

        /*
        tutor.createStudent("NEW UNIQUE STUDENT");
        tutor.createTest("NEW UNIQUE TEST");
        tutor.createQuestion(3, "desc", "WEB");
        tutor.estimateAnswer(1, "correct");
        tutor.administerTestToStudent(4, "NEW UNIQUE STUDENT"); */

        ConStudent student = new ConStudent(1, "John Doe", 1);
        System.out.println("Student getting his questions");
        student.getQuestions();
        System.out.println("Student getting his answers");
        student.getAnswers();

        //student.createAnswer("KKKKKKKK");
        //student.changeAnswer(22, "NEW ANSWER");

    }
}
