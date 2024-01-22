package main.java;

import main.java.control.Tutor;
import main.java.entity.Student;
import main.java.exception.DAOException;

public class Main {
    public static void main(String[] args) throws DAOException {

        Student student = new Student(1, "John Doe");
        student.setTestID(1);
        Tutor tutor = new Tutor();

        System.out.println("Получаем вопросы студента");
        student.getQuestions();

        System.out.println("Создаём новый ответ");
        student.createAnswer("New answer");
        student.getAnswers();

        System.out.println("Изменяем ответ");
        student.changeAnswer(1, "Changed answer 1000");
        student.getAnswers();

        //назначаем тест студенту
        tutor.administerTestToStudent(1, "John Doe");
        System.out.println("Выводим список вопросов к тесту");
        tutor.getQuestionList(1);

        System.out.println("Выводим список тестов");
        tutor.getTests();

        System.out.println("Выводим список вопросов по предмету Math");
        tutor.getQuestionsBySubject("Math");

        System.out.println("Получаем список ответов студента");
        tutor.getStudentAnswers("John Doe", 1);

        System.out.println("Оцениваем ответы и выводим общий результат");
        tutor.estimateAnswer(2, "correct");
        tutor.estimateAnswer(3, "correct");
        tutor.getStudentTestResult("John Doe", 1);

    }
}
