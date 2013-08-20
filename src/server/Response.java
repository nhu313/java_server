package server;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private final int code;
    private String body;
    private Map<String, String> header = new HashMap<String, String>();

    public Response(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void addHeader(String type, String value) {
        header.put(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (code != response.code) return false;
        if (body != null ? !body.equals(response.body) : response.body != null) return false;
        if (header != null ? !header.equals(response.header) : response.header != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", body='" + body + '\'' +
                ", header=" + header +
                '}';
    }

    public Map<String, String> getHeader() {
        return header;
    }
}
