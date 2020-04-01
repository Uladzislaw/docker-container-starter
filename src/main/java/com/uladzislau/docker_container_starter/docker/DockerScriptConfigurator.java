package com.uladzislau.docker_container_starter.docker;

public class DockerScriptConfigurator {

    public static String buildScript(DockerScript dockerScript, Object... params) {
        return String.format(dockerScript.getPattern(), params);
    }

    public enum DockerScript {
        BUILD_IMAGE("docker build -t %s %s"),
        RUN_IMAGE("docker run -d --name %s -p %d:5432 %s"),
        RE_RUN_CONTAINER("docker start %s"),
        STOP_CONTAINER("docker stop %s"),
        REMOVE_CONTAINER("docker rm %s"),
        LOG_CONTAINER_INFO_CONSOLE("docker logs %s"),
        LOG_CONTAINER_INTO_FILE("docker logs %s > %s"),
        DOCKER_PS("docker ps %s");

        private String s;

        DockerScript(String s) {
            this.s = s;
        }

        public String getPattern() {
            return s;
        }
    }
}
