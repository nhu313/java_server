package server;

public class Response {
    private final int code;
    private String body;

    public Response(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (code != response.code) return false;
        if (body != null ? !body.equals(response.body) : response.body != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }

    public String getBody() {
        return body;
    }
}
