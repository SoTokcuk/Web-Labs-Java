package main.java.entity;

public class Answer {
    private int id;
    private String studentName;
    private String testName;
    private String answer;
    private String result;

    public Answer(int id, String studentName, String testName, String answer, String result) {
        this.id = id;
        this.studentName = studentName;
        this.testName = testName;
        this.answer = answer;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", testName='" + testName + '\'' +
                ", answer='" + answer + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
