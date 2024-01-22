package main.java.builder;

import main.java.entity.Test;

public interface Builder {
    Builder setID(int id);
    Builder setName(String name);

    Test build();
}
