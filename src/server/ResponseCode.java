package server;

public enum ResponseCode {
    OK(200, "OK"),
    AUTHENTICATION_FAIL(401, "Authentication required"),
    NOT_FOUND(404, "Not Found"),
    MOVED(301, "Moved Permanently"),
    PARTIAL_CONTENT(206, "Partial Content");

    private final String message;
    private final int code;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
