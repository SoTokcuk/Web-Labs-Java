package by.bsu;

import by.bsu.dao.StudentDAO;
import by.bsu.dao.TestDAO;

public class App {
    public static void main(String[] args) {
        StudentDAO.setTestForStudent(1, "TRTRTR");
    }
}
