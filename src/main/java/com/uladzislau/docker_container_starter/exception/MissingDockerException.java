package com.uladzislau.docker_container_starter.exception;

public class MissingDockerException extends RuntimeException {
    public MissingDockerException(String message) {
        super(message);
    }
}
