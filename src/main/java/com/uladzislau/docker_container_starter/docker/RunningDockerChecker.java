package com.uladzislau.docker_container_starter.docker;

import com.uladzislau.docker_container_starter.docker.DockerScriptConfigurator.DockerScript;
import com.uladzislau.docker_container_starter.exception.MissingDockerException;
import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;

import java.util.Collection;
import java.util.List;

public class RunningDockerChecker {

    public static void acquireRunningDocker() {
        LogCollector logCollector = new LogCollector();
        List<List<String>> possibleErrorLine = logCollector.collectError(TerminalScriptExecutor.executeCommand(
                DockerScriptConfigurator.buildScript(DockerScript.DOCKER_PS, "-a")));
        boolean notRunning = possibleErrorLine.stream()
                .flatMap(Collection::stream)
                .anyMatch(l -> l.contains("Cannot connect to the Docker daemon"));
        if (notRunning) {
            throw new MissingDockerException("Please, check is docker running on your system");
        }
    }
}
