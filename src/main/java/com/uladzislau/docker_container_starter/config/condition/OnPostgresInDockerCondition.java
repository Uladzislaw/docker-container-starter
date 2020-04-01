package com.uladzislau.docker_container_starter.config.condition;

import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class OnPostgresInDockerCondition extends AllNestedConditions {
    public OnPostgresInDockerCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(value = PropertiesConstants.SCRIPT, havingValue = PropertiesConstants.CREATE_STOP)
    public static class R {
    }

    @ConditionalOnProperty(value = PropertiesConstants.SCRIPT, havingValue = PropertiesConstants.CREATE_REMOVE)
    public static class C {
    }
}
