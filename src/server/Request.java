package server;

import java.util.Map;

public class Request {
    private String method;
    private String path;
    private String body;
    private int contentLength;
    private String host;
    private Map<String, String> params;
    private int maxContentSize;

    public Request() {}

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (contentLength != request.contentLength) return false;
        if (body != null ? !body.equals(request.body) : request.body != null) return false;
        if (host != null ? !host.equals(request.host) : request.host != null) return false;
        if (method != null ? !method.equals(request.method) : request.method != null) return false;
        if (params != null ? !params.equals(request.params) : request.params != null) return false;
        if (path != null ? !path.equals(request.path) : request.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + contentLength;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", body='" + body + '\'' +
                ", contentLength=" + contentLength +
                ", host='" + host + '\'' +
                ", params=" + params +
                '}';
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setMaxContentSize(int maxContentSize) {
        this.maxContentSize = maxContentSize;
    }

    public int getMaxContentSize() {
        return maxContentSize;
    }
}
