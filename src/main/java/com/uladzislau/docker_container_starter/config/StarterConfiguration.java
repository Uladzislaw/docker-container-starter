package com.uladzislau.docker_container_starter.config;

import com.uladzislau.docker_container_starter.config.condition.annotation.ConditionalOnPostgresInDockerScript;
import com.uladzislau.docker_container_starter.config.properties.classes.Container;
import com.uladzislau.docker_container_starter.config.properties.classes.Image;
import com.uladzislau.docker_container_starter.config.properties.classes.PostgresInDockerProperties;
import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import com.uladzislau.docker_container_starter.docker.postgres.PostgresContainerRemover;
import com.uladzislau.docker_container_starter.docker.postgres.PostgresContainerStopper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnPostgresInDockerScript
@EnableConfigurationProperties(
        {PostgresInDockerProperties.class, Image.class, Container.class})
public class StarterConfiguration {

    @Bean
    @ConditionalOnPostgresInDockerScript
    public PostgresContainerStopper postgresContainerStopper() {
        return new PostgresContainerStopper();
    }

    @Bean
    @DependsOn("postgresContainerStopper")
    @ConditionalOnProperty(name = PropertiesConstants.SCRIPT,
            havingValue = PropertiesConstants.CREATE_REMOVE)
    public PostgresContainerRemover postgresContainerRemover() {
        return new PostgresContainerRemover();
    }
}
