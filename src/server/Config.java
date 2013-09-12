package server;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Config {
    public static final String ROOT_DIRECTORY = "/resources";

    public static final String PUBLIC_DIRECTORY = ROOT_DIRECTORY + "/public";
    public static final String PRIVATE_PATH = ROOT_DIRECTORY + "/private";
    public static final String ROUTE_PATH = PRIVATE_PATH + "/processor_locator.txt";
    public static final String LOG_PATH = PRIVATE_PATH + "/logs";
    public static final String LOG_USERNAME = "admin";
    public static final String LOG_PASSWORD = "hunter2";

    public static final String ENCODE = "UTF-8";

}
