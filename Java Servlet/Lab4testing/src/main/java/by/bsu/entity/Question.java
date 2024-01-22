package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
@NamedQueries( {
        @NamedQuery(
                name = "GET_QUESTIONS",
                query = "SELECT q FROM Question q"
        ),
        @NamedQuery(
                name = "GET_QUESTIONS_BY_TEST_ID",
                query = "SELECT q FROM Question q WHERE q.test.id = :id"
        ),
        @NamedQuery(
                name = "GET_QUESTIONS_BY_SUBJECT",
                query = "SELECT q FROM Question q WHERE q.subject = :subject"
        ),
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @Column(name = "description")
    private String description;
    @Column(name = "subject")
    private String subject;

    public Question(int id, int testID, String description, String subject) {
        this.id = id;
        this.test.setId(testID);
        this.description = description;
        this.subject = subject;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestID() {
        return test.getId();
    }

    public void setTestID(int testID) {
        this.test.setId(testID);
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
                ", testID=" + getTestID() +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
