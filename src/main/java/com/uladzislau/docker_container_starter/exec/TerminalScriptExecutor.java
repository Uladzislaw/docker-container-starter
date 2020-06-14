package com.uladzislau.docker_container_starter.exec;

import lombok.SneakyThrows;

import java.io.File;

public class TerminalScriptExecutor {

    @SneakyThrows
    public static Process executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        String[] terminalCommands = prepareWholeCommand(command);

        Process start = processBuilder.command(terminalCommands).start();
        start.waitFor();

        return start;
    }

    @SneakyThrows
    public static Process executeWithRedirect(String command, File file) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        String[] terminalCommands = prepareWholeCommand(command);

        Process start = processBuilder.command(terminalCommands)
                .redirectInput(file)
                .redirectError(file)
                .start();
        start.waitFor();

        return start;
    }

    private static String[] prepareWholeCommand(String command) {
        String[] terminalCommands = DeveloperSystem.getTerminalOSDependArgs(3);
        terminalCommands[2] = command;
        return terminalCommands;
    }

    private static class DeveloperSystem {
        private static final String OS = System.getProperty("os.name").split("\\s+")[0];

        static String[] getTerminalOSDependArgs(int size) {
            String[] terminalCommands = new String[size];
            if (OS.equalsIgnoreCase("windows")) {
                terminalCommands[0] = "cmd";
                terminalCommands[1] = "/c";
            } else {
                terminalCommands[0] = "/bin/bash";
                terminalCommands[1] = "-c";
            }
            return terminalCommands;
        }
    }
}
