package com.uladzislau.docker_container_starter.config;

import com.uladzislau.docker_container_starter.config.condition.annotation.ConditionalOnPostgresInDockerScript;
import com.uladzislau.docker_container_starter.config.properties.classes.Container;
import com.uladzislau.docker_container_starter.config.properties.classes.DeveloperProperties;
import com.uladzislau.docker_container_starter.config.properties.classes.Image;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnPostgresInDockerScript
@EnableConfigurationProperties(
        {DeveloperProperties.class, Image.class, Container.class})
@ComponentScan("com.uladzislau.*")
public class StarterConfiguration {

//    @Bean
//    @ConditionalOnPostgresInDockerScript
//    public PostgresContainerStopper postgresContainerStopper() {
//        return new PostgresContainerStopper();
//    }

//    @Bean
//    @DependsOn("postgresContainerStopper")
//    @ConditionalOnProperty(name = PropertiesConstants.SCRIPT,
//            havingValue = PropertiesConstants.CREATE_REMOVE)
//    public PostgresContainerRemover postgresContainerRemover() {
//        return new PostgresContainerRemover();
//    }
}
