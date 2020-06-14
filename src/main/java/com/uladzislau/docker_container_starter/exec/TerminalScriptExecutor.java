package com.uladzislau.docker_container_starter.exec;

import lombok.SneakyThrows;

public class TerminalScriptExecutor {
    @SneakyThrows
    public static Process executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        String[] terminalCommands = DeveloperSystem.getTerminalOSDependArgs(3);
        terminalCommands[2] = command;

        Process start = processBuilder.command(terminalCommands).start();
        start.waitFor();

        return start;
    }

    static class DeveloperSystem {
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
