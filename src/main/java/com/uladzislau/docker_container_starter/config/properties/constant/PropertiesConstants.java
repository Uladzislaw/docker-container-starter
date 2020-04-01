package com.uladzislau.docker_container_starter.config.properties.constant;

public class PropertiesConstants {
    public static final String LIB_NAME = "postgres-in-docker";
    public static final String CREATE = "create";
    public static final String CREATE_REMOVE = "create-remove";
    public static final String CREATE_STOP = "create-stop";
    public static final String NONE = "none";
    public static final String CONSOLE = "console";
    private static final String DOT = ".";
    public static final String START_ON_PROFILE = LIB_NAME + DOT + "start_on_profile";
    public static final String SCRIPT = LIB_NAME + DOT + "script";
    public static final String LOG = LIB_NAME + DOT + "logging";
    public static final String LOG_ENABLED = LOG + DOT + "enabled";
    public static final String CONTAINER = LIB_NAME + DOT + "container";
    public static final String CONTAINER_NAME = CONTAINER + DOT + "name";
    public static final String CONTAINER_PORT = CONTAINER + DOT + "port";
    public static final String LOG_FILE = CONTAINER + DOT + "log-file";

    public static final String IMAGE = LIB_NAME + DOT + "image";
    public static final String IMAGE_NAME = IMAGE + DOT + "name";
    public static final String DOCKER_FILE_DIR = IMAGE + DOT + "directory";
}
