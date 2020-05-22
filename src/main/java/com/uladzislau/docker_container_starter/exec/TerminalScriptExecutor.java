package com.uladzislau.docker_container_starter.exec;

import lombok.SneakyThrows;

public class TerminalScriptExecutor {
    @SneakyThrows
    public static Process executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        String[] terminalCommands = new String[3];
        if (DeveloperSystem.OS.equalsIgnoreCase("windows")) {
            terminalCommands[0] = "cmd";
            terminalCommands[1] = "/c";
        } else {
            terminalCommands[0] = "bash";
            terminalCommands[1] = "-c";
        }
        terminalCommands[2] = command;

        processBuilder.command(terminalCommands);

        Process process = processBuilder.start();
        process.waitFor();
        return process;
    }

    public static class DeveloperSystem {
        public static final String OS
                = System.getProperty("os.name").split("\\s+")[0];
    }
}
