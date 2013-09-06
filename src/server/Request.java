package server;

import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String path;
    private String body;
    private int contentLength;
    private String host;
    private Map<String, String> params;
    private int maxContentSize;
    private String authentication;
    private String username;
    private String password;

    public Request() {}

    public Request(String method, String path) {
        this.httpMethod = HttpMethod.valueOf(method);
        this.path = path;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = HttpMethod.valueOf(httpMethod);
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

    public Map<String, String> getParams() {
        return params;
    }

    public void setMaxContentSize(int maxContentSize) {
        this.maxContentSize = maxContentSize;
    }

    public int getMaxContentSize() {
        return maxContentSize;
    }

    public void setMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (contentLength != request.contentLength) return false;
        if (maxContentSize != request.maxContentSize) return false;
        if (authentication != null ? !authentication.equals(request.authentication) : request.authentication != null)
            return false;
        if (body != null ? !body.equals(request.body) : request.body != null) return false;
        if (host != null ? !host.equals(request.host) : request.host != null) return false;
        if (httpMethod != request.httpMethod) return false;
        if (params != null ? !params.equals(request.params) : request.params != null) return false;
        if (password != null ? !password.equals(request.password) : request.password != null) return false;
        if (path != null ? !path.equals(request.path) : request.path != null) return false;
        if (username != null ? !username.equals(request.username) : request.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = httpMethod != null ? httpMethod.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + contentLength;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + maxContentSize;
        result = 31 * result + (authentication != null ? authentication.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "authentication='" + authentication + '\'' +
                ", httpMethod=" + httpMethod +
                ", path='" + path + '\'' +
                ", body='" + body + '\'' +
                ", contentLength=" + contentLength +
                ", host='" + host + '\'' +
                ", params=" + params +
                ", maxContentSize=" + maxContentSize +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }
}
