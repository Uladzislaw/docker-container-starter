package com.uladzislau.docker_container_starter.context;

import com.uladzislau.docker_container_starter.config.properties.classes.PostgresInDockerProperties;
import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import com.uladzislau.docker_container_starter.docker.postgres.PostgresContainerInitializer;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@AutoConfigureBefore(DataSource.class)
public class ScriptInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @SneakyThrows
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        PostgresInDockerProperties defaultProps = PostgresInDockerProperties.getInstance();
        String scriptProperty = environment.getProperty(PropertiesConstants.SCRIPT);
        if (scriptProperty == null || scriptProperty.equalsIgnoreCase(PropertiesConstants.NONE)) {
            return;
        } else {
            defaultProps.setScript(scriptProperty);
        }
        String startProfile = setUpProperties(environment, defaultProps);
        if (Arrays.asList(environment.getActiveProfiles()).contains(startProfile)) {
            PostgresContainerInitializer.initPostgresContainer();
//            TimeUnit.SECONDS.sleep(3);
        }
    }

    private String setUpProperties(ConfigurableEnvironment environment, PostgresInDockerProperties defaultProps) {
        String startProfile = environment.getProperty(PropertiesConstants.START_ON_PROFILE);
        if (startProfile == null) {
            startProfile = defaultProps.getStart_on_profile();
        }
        String imageName = environment.getProperty(PropertiesConstants.IMAGE_NAME);
        if (imageName != null) {
            defaultProps.getImage().setName(imageName);
        }
        String directory = environment.getProperty(PropertiesConstants.DOCKER_FILE_DIR);
        if (directory != null) {
            defaultProps.getImage().setDirectory(directory);
        }
        String containerName = environment.getProperty(PropertiesConstants.CONTAINER_NAME);
        if (containerName != null) {
            defaultProps.getContainer().setName(containerName);
        }
        String port = environment.getProperty(PropertiesConstants.CONTAINER_PORT);
        if (port != null) {
            defaultProps.getContainer().setPort(Integer.parseInt(port));
        }
        String logFile = environment.getProperty(PropertiesConstants.LOG_FILE);
        if (logFile != null) {
            defaultProps.getContainer().setLog_file(logFile);
        }
        return startProfile;
    }
}
