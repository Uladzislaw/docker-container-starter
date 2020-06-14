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
public class DeveloperProperties {

    @Getter
    @Setter
    private String mode = PropertiesConstants.NONE;
    @Getter
    @Setter
    private String profile = "dev";
    @Getter
    @Setter
    private Container container = new Container();
    @Getter
    @Setter
    private Image image = new Image();


    private static DeveloperProperties INSTANCE;

    public static synchronized DeveloperProperties getInstance() {
        return Objects.requireNonNullElseGet(INSTANCE, () ->
                INSTANCE = new DeveloperProperties()
        );
    }
}
