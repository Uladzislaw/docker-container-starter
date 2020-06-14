package com.uladzislau.docker_container_starter.docker;

import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogCollector {
    @SneakyThrows
    public void collectInternalContainerLogInFile(String container, String filePath) {
        TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.LOG_CONTAINER_INTO_FILE, container, filePath));
    }

    @SneakyThrows
    public List<List<String>> collectSuccess(Process process) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

        return parseTerminalOutput(reader);
    }

    @SneakyThrows
    public List<List<String>> collectError(Process process) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));

        return parseTerminalOutput(reader);
    }

    private List<List<String>> parseTerminalOutput(BufferedReader reader) throws IOException {
        String line;
        List<List<String>> terminalOutput = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            terminalOutput.add(Arrays.asList(line.strip().split("\\s\\s+")));
        }
        return terminalOutput;
    }
}
