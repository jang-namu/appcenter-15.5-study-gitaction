package com.example.todo.common;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MariaDBTestContainer {

    private static final String MARIADB_VERSION = "mariadb:10.6.16";

    @Container
    public static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>(MARIADB_VERSION);

}
