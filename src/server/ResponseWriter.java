package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResponseWriter {
    public static final String HTTP_VERSION = "HTTP/1.1";

    private static final Map<Integer, String> HTTP_RESPONSE_MESSAGE = new HashMap<Integer, String>();
    static {
        HTTP_RESPONSE_MESSAGE.put(200, "OK");
        HTTP_RESPONSE_MESSAGE.put(301, "Moved Permanently");
    }

    public void write(OutputStream output, Response response) throws IOException {
        output.write(buildHeader(response).getBytes());
        if (response.getBody() != null){
            output.write(response.getBody());
        }
    }

    private String buildHeader(Response response) {
        StringBuffer headerResponse = new StringBuffer();
        buildHeaderFirstLine(response, headerResponse);
        buildHeaderContent(response, headerResponse);
        buildOtherHeaderInfo(response, headerResponse);
       headerResponse.append("\r\n");
        return headerResponse.toString();
    }

    private void buildOtherHeaderInfo(Response response, StringBuffer headerResponse) {
        Set<Map.Entry<String, String>> header = response.getHeader().entrySet();
        for (Map.Entry entry : header){
            headerResponse.append(entry.getKey() + ":" + entry.getValue() + "\n");
        }
    }

    private void buildHeaderFirstLine(Response response, StringBuffer headerResponse) {
        int code = response.getCode();
        headerResponse.append(HTTP_VERSION);
        headerResponse.append(" " + code);
        headerResponse.append(" " + HTTP_RESPONSE_MESSAGE.get(code) + '\n');
    }

    private void buildHeaderContent(Response response, StringBuffer headerResponse){
        if (response.getBody() != null) {
            headerResponse.append("Content-Type: text/html\n");
            headerResponse.append("Content-Length: " + response.getContentLength()+ '\n');
        }
    }
}