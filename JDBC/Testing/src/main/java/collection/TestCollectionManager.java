package main.java.collection;

import main.java.entity.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestCollectionManager {
    public static void sortByID(List<Test> tests) {
        Collections.sort(tests, Comparator.comparing(Test::getId));
    }

    public static void sortByName(List<Test> tests) {
        Collections.sort(tests, Comparator.comparing(Test::getName));
    }

    public static Test findByName(List<Test> tests, String name) {
        for (Test test : tests) {
            if (test.getName().equals(name)) {
                return test;
            }
        }
        return null;
    }
}
