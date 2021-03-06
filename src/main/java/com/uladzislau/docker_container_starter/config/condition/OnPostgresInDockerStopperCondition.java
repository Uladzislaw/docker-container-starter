package com.uladzislau.docker_container_starter.config.condition;

import com.uladzislau.docker_container_starter.config.properties.constant.PropertiesConstants;
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class OnPostgresInDockerStopperCondition extends AnyNestedCondition {
    public OnPostgresInDockerStopperCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(value = PropertiesConstants.MODE, havingValue = PropertiesConstants.CREATE_STOP)
    public static class R {
    }

    @ConditionalOnProperty(value = PropertiesConstants.MODE, havingValue = PropertiesConstants.CREATE_REMOVE)
    public static class C {
    }
}
