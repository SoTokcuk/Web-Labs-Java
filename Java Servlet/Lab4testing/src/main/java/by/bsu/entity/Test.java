package by.bsu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tests")
@NamedQueries( {
        @NamedQuery(
                name = "GET_TESTS",
                query = "SELECT t FROM Test t"
        ),
})
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;



    public Test() {}

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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
