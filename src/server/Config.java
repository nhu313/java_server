package server;

public class Config {
    public static final String PUBLIC_DIRECTORY = getRootDirectory();

    private static String getRootDirectory() {
        String directory = System.getProperty("resource_directory");
        if (directory == null) {
            directory = Config.class.getResource("/resources").getPath();
        }
        return directory;
    }

    public static final String LOG_PATH = "/logs";
    public static final String LOG_USERNAME = "admin";
    public static final String LOG_PASSWORD = "hunter2";

    public static final String ENCODE = "UTF-8";

}
