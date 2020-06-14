package com.uladzislau.docker_container_starter.docker;

import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class DockerContainerInspector {

    public ContainerStatus tryToFind(String container) {
        log.debug("Finding container with name: " + container);
        LogCollector logCollector = new LogCollector();
        List<List<String>> dockerResponse = logCollector.collectSuccess(
                TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.DOCKER_PS, "-a")));
        dockerResponse.remove(0);
        Optional<List<String>> containerCandidate = dockerResponse.stream()
                .filter(l -> containerExists(container, l))
                .findAny();
        if (containerCandidate.isPresent()) {
            ContainerStatus containerStatus = defineStatus(containerCandidate.get());
            log.debug("Container with name " + container + " found. Status: " + containerStatus.toString());
            return containerStatus;
        }
        log.debug("Container with name " + container + " didn't found.");
        return ContainerStatus.NOT_EXISTS;
    }

    private static boolean containerExists(String container, List<String> containers) {
        return containers.stream()
                .anyMatch(c -> c.equalsIgnoreCase(container));
    }

    private ContainerStatus defineStatus(List<String> container) {
        int statusWordIndex = 4;
        String status = container.get(statusWordIndex);
        if (status.split("\\s+")[0].equalsIgnoreCase("up"))
            return ContainerStatus.RUNNING;
        if (status.split("\\s+")[0].equalsIgnoreCase("exited"))
            return ContainerStatus.STOPPED;
        return ContainerStatus.NOT_EXISTS;
    }
}
