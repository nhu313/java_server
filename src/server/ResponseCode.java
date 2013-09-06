package server;

public enum ResponseCode {
    OK(200, "OK"),
    AUTHENTICATION_FAIL(401, "Authentication required"),
    NOT_FOUND(404, "Not Found"),
    MOVED(301, "Moved Permanently"),
    PARTIAL_CONTENT(206, "Partial Content"),
    METHOD_NOT_ALLOW(405, "HttpMethod not allowed");

    private final String description;
    private final int code;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
