package by.bsu.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries( {
        @NamedQuery(
                name = "GET_STUDENTS",
                query = "SELECT s FROM Student s"
        ),
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Student() {}

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        return test.getId();
    }

    public void setTestID(int testID) {
        this.test.setId(testID);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testID=" + getTestID() +
                '}';
    }
}
