package com.uladzislau.docker_container_starter.config.properties.classes;

import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ConfigurationProperties(PropertiesConstants.CONTAINER)
public class Container {
    @Min(1025)
    @Max(65536)
    private int port = 5432;
    private String name = "postgres-container";
    private String log_file = PropertiesConstants.NONE;
}
