package server;

public class Config {
    public static final String DIRECTORY_PATH_KEY = "directory_path";
    public static final String DIRECTORY_PATH = System.getProperty(DIRECTORY_PATH_KEY);
    public static final String PRIVATE_PATH = "private";
    public static final String ROUTE_PATH = DIRECTORY_PATH + "/" + PRIVATE_PATH + "/processor_locator.txt";
}
