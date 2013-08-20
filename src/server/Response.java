package server;

public class Response {
    private final int code;

    public Response(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (code != response.code) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code;
    }

    public int getCode() {
        return code;
    }
}
