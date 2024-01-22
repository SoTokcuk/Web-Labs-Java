package main.java.collection;

import main.java.entity.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCollection {
    private List<Test> tests;

    public TestCollection() {
    tests = new ArrayList<>();
    }

    public void addTest(Test test) {
    tests.add(test);
    }

    public List<Test> getTests() {
        return tests;
    }
}
