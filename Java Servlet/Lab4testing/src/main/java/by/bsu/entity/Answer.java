package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
@NamedQueries( {
        @NamedQuery(
                name = "GET_STUDENT_ANSWERS",
                query = "Select a FROM Answer a WHERE a.student.id = :st_id AND a.test.id = :test_id"
        ),
        @NamedQuery(
                name = "UPDATE_ANSWER",
                query = "UPDATE Answer a SET a.answer = :answer WHERE a.id = :id"
        ),
        @NamedQuery(
                name = "UPDATE_RESULT",
                query = "UPDATE Answer a SET a.result = :result WHERE a.id = :id"
        ),
        @NamedQuery(
                name = "COUNT_RIGHT_ANSWERS",
                query = "SELECT COUNT(a) AS correct_count FROM Answer a WHERE a.student.id = :id AND a.test.id = :test_id AND a.result = 'correct'"
        ),
        @NamedQuery(
                name = "GET_ANSWERS",
                query = "SELECT a FROM Answer a"
        ),
})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @Column(name = "answer")
    private String answer;
    @Column(name = "result")
    private String result;

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTestId(int testID) {
        this.test.setId(testID);
    }
    public void setStudentId(int studentId) {
        this.student.setId(studentId);
    }

    public int getTestId() {
        return this.test.getId();
    }

    public int getStudentId() {
        return this.student.getId();
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", studentID=" + getStudentId() +
                ", testID=" + getTestId() +
                ", answer='" + answer + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
