package com.uladzislau.docker_container_starter.docker;

import com.uladzislau.docker_container_starter.exception.MissingDockerException;
import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;
import com.uladzislau.docker_container_starter.util.BufferedInfoFileHandlingHelper;

import java.io.File;

public class RunningDockerChecker {

    public static void acquireRunningDocker() {
        BufferedInfoFileHandlingHelper helper = new BufferedInfoFileHandlingHelper("docker-run-check.txt");
        File file = helper.createFile();
        TerminalScriptExecutor.executeWithRedirect(
                DockerScriptConfigurator.buildScript(
                        DockerScriptConfigurator.DockerScript.DOCKER_PS, "-a"), file);
        boolean notRunning = helper.contains("Cannot connect to the Docker daemon");
        helper.deleteFile();
        if (notRunning) {
            throw new MissingDockerException("Please, check is docker running on your system");
        }
    }
}
