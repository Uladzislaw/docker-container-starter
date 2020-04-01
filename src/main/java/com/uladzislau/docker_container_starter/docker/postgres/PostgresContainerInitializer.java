package com.uladzislau.docker_container_starter.docker.postgres;

import com.uladzislau.docker_container_starter.config.properties.classes.PostgresInDockerProperties;
import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import com.uladzislau.docker_container_starter.docker.DockerContainerLogCollector;
import com.uladzislau.docker_container_starter.docker.DockerExistingContainerInspector;
import com.uladzislau.docker_container_starter.docker.DockerScriptConfigurator;
import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresContainerInitializer {
    @SneakyThrows
    public static void initPostgresContainer() {
        log.info("Postgres container initialization started...");

        DockerContainerLogCollector logCollector = new DockerContainerLogCollector();
        PostgresInDockerProperties properties = PostgresInDockerProperties.getInstance();
        DockerExistingContainerInspector containerInspector = new DockerExistingContainerInspector();

        String image = properties.getImage().getName();
        String container = properties.getContainer().getName();
        String logFile = properties.getContainer().getLog_file();

        log.debug("Using image name: " + image);
        log.debug("Using container name: " + container);

        DockerExistingContainerInspector.ContainerStatus containerStatus = containerInspector.tryToFind(container);
        if (containerStatus.equals(DockerExistingContainerInspector.ContainerStatus.NOT_EXISTS)) {
            runAndBuildImage(properties, logCollector, image, container);
        } else if (containerStatus.equals(DockerExistingContainerInspector.ContainerStatus.STOPPED)) {
            reRunContainer(logCollector, container);
        }
        if (!logFile.equalsIgnoreCase(PropertiesConstants.NONE)) {
            logCollector.collectInternalContainerLogInFile(container,
                    logFile);
            log.debug("Container " + container + " inner log logged into file " + logFile);
        }
        log.info("Postgres container " + container + " initialization completed.");
    }

    private static void reRunContainer(DockerContainerLogCollector logCollector, String container) {
        log.info("Container with name " + container + " exists. Re-running...");
        logCollector.collectLog(TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.RE_RUN_CONTAINER, container)));
        log.info("Container " + container + " is up now.");
    }

    private static void runAndBuildImage(PostgresInDockerProperties properties,
                                         DockerContainerLogCollector logCollector,
                                         String image, String container) {
        log.info("Building image " + image + "...");
        logCollector.collectLog(TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.BUILD_IMAGE,
                image,
                properties.getImage().getDirectory())));
        log.info("Image " + image + " created.");

        log.info("Running container " + container + "...");
        logCollector.collectLog(TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.RUN_IMAGE,
                container,
                properties.getContainer().getPort(),
                image)));
        log.info("Container " + container + " created.");
    }
}
