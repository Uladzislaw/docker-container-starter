package com.uladzislau.docker_container_starter.config.properties.classes;

import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(PropertiesConstants.LIB_NAME)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostgresInDockerProperties {

    private static PostgresInDockerProperties INSTANCE;
    @Getter
    @Setter
    private String script = PropertiesConstants.NONE;
    @Getter
    @Setter
    private String start_on_profile = "dev";
    @Getter
    @Setter
    private Container container = new Container();
    @Getter
    @Setter
    private Image image = new Image();

    public static synchronized PostgresInDockerProperties getInstance() {
        return Objects.requireNonNullElseGet(INSTANCE, () ->
                INSTANCE = new PostgresInDockerProperties()
        );
    }
}
