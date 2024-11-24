package com.spring.handbook.data.container;

import org.testcontainers.containers.PostgreSQLContainer;

public final class ContainerFactory {

    private ContainerFactory() {}

    public static PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:16")
                .withReuse(true);
    }

}
