package com.uladzislau.docker_container_starter.context;

import com.uladzislau.docker_container_starter.config.properties.classes.DeveloperProperties;
import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import com.uladzislau.docker_container_starter.docker.RunningDockerChecker;
import com.uladzislau.docker_container_starter.docker.postgres.PostgresContainerInitializer;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Arrays;

@AutoConfigureBefore(DataSource.class)
public class StarterPropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @SneakyThrows
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        DeveloperProperties defaultProps = DeveloperProperties.getInstance();
        String mode = environment.getProperty(PropertiesConstants.MODE);
        if (mode == null || mode.equalsIgnoreCase(PropertiesConstants.NONE)) {
            return;
        } else {
            defaultProps.setMode(mode);
        }
        if (containsNeededProfile(environment)) {
            setUpProperties(environment, defaultProps);
            RunningDockerChecker.acquireRunningDocker();
            PostgresContainerInitializer.initPostgresContainer();
        }
    }

    private void setUpProperties(ConfigurableEnvironment environment, DeveloperProperties defaultProps) {
        String imageName = environment.getProperty(PropertiesConstants.IMAGE_NAME);
        if (imageName != null) {
            defaultProps.getImage().setName(imageName);
        }
        //TODO: Find what purpose of this property below
//        String directory = environment.getProperty(PropertiesConstants.DOCKER_FILE_DIR);
//        if (directory != null) {
//            defaultProps.getImage().setDirectory(directory);
//        }
        String containerName = environment.getProperty(PropertiesConstants.CONTAINER_NAME);
        if (containerName != null) {
            defaultProps.getContainer().setName(containerName);
        }
        String port = environment.getProperty(PropertiesConstants.CONTAINER_PORT);
        if (port != null) {
            defaultProps.getContainer().setPort(Integer.parseInt(port));
        }
        //TODO: //TODO: Find what purpose of this property below
//        String logFile = environment.getProperty(PropertiesConstants.LOG_FILE);
//        if (logFile != null) {
//            defaultProps.getContainer().setLog_file(logFile);
//        }
    }

    private boolean containsNeededProfile(Environment env) {
        String profile = env.getProperty(PropertiesConstants.START_ON_PROFILE);
        if (profile == null) {
            profile = DeveloperProperties.getInstance().getProfile();
        }
        return Arrays.asList(env.getActiveProfiles()).contains(profile);
    }
}
