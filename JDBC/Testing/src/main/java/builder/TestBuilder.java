package main.java.builder;

import main.java.entity.Test;

public class TestBuilder implements Builder{
    private Test test;

    public TestBuilder() { this.test = new Test();}

    @Override
    public Builder setID(int id) {
        test.setId(id);
        return this;
    }

    @Override
    public Builder setName(String name) {
        test.setName(name);
        return this;
    }

    @Override
    public Test build() { return test; }
}
