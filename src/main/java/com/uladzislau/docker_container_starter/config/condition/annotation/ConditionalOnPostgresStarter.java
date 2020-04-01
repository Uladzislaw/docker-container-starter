package com.uladzislau.docker_container_starter.config.condition.annotation;

import com.uladzislau.docker_container_starter.config.condition.OnPostgresInDockerCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Conditional(OnPostgresInDockerCondition.class)
public @interface ConditionalOnPostgresStarter {
}
