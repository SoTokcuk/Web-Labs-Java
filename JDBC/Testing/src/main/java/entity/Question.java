package main.java.entity;

public class Question {
    private int id;
    private int testID;
    private String description;
    private String subject;

    public Question(int id, int testID, String description, String subject) {
        this.id = id;
        this.testID = testID;
        this.description = description;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", testID=" + testID +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
