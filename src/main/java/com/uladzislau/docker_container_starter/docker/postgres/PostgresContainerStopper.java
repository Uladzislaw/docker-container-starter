package com.uladzislau.docker_container_starter.docker.postgres;

import com.uladzislau.docker_container_starter.config.condition.annotation.ConditionalOnPostgresInDockerScript;
import com.uladzislau.docker_container_starter.config.properties.classes.DeveloperProperties;
import com.uladzislau.docker_container_starter.docker.DockerScriptConfigurator;
import com.uladzislau.docker_container_starter.docker.LogCollector;
import com.uladzislau.docker_container_starter.exec.TerminalScriptExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnPostgresInDockerScript
public class PostgresContainerStopper implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        String container = DeveloperProperties.getInstance().getContainer().getName();
        log.info("Container " + container + " stopping started.");
        LogCollector containerLogger = new LogCollector();
        containerLogger
                .collectSuccess(TerminalScriptExecutor.executeCommand(DockerScriptConfigurator.buildScript(DockerScriptConfigurator.DockerScript.STOP_CONTAINER,
                        container)));
        log.info("Container " + container + " has been stopped.");
    }
}
