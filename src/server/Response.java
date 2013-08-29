package server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private int code;
    private byte[] body;
    private Map<String, String> header = new HashMap<String, String>();
    private String contentType;
    private long fileLength;

    public Response(){}

    public Response(int code) {
        this.code = code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setBody(String body) {
        if (body != null) {
            this.body = body.getBytes();
        }
    }

    public void setBody(byte[] body){
        this.body = body;
    }

    public byte[] getBody() {
        return body;
    }

    public void addHeader(String type, String value) {
        header.put(type, value);
    }

    public int getContentLength(){
        return (body == null) ? 0 : body.length;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setFileLength(long fileSize) {
        this.fileLength = fileSize;
    }

    public long getFileLength() {
        return fileLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (code != response.code) return false;
        if (fileLength != response.fileLength) return false;
        if (!Arrays.equals(body, response.body)) return false;
        if (contentType != null ? !contentType.equals(response.contentType) : response.contentType != null)
            return false;
        if (header != null ? !header.equals(response.header) : response.header != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (body != null ? Arrays.hashCode(body) : 0);
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (int) (fileLength ^ (fileLength >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", body=" + Arrays.toString(body) +
                ", header=" + header +
                ", contentType='" + contentType + '\'' +
                ", fileLength=" + fileLength +
                '}';
    }
}