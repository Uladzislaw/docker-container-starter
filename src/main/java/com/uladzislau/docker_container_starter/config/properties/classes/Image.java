package com.uladzislau.docker_container_starter.config.properties.classes;

import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ConfigurationProperties(PropertiesConstants.IMAGE)
public class Image {
    private String name = "postgres-image";
    private String directory = ".";
}
