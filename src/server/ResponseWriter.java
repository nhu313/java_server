package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
        String message = buildResponseMessage(response);
        PrintWriter outputWriter = new PrintWriter(output, true);
        outputWriter.println(message);
    }

    private String buildResponseMessage(Response response) {
        return buildHeader(response) + buildBody(response);
    }

    private String buildHeader(Response response) {
        StringBuffer headerResponse = new StringBuffer();
        buildHeaderFirstLine(response, headerResponse);
        buildHeaderContent(response, headerResponse);
        buildOtherHeaderInfo(response, headerResponse);
        return headerResponse.toString();
    }

    private void buildOtherHeaderInfo(Response response, StringBuffer headerResponse) {
        Set<Map.Entry<String, String>> header = response.getHeader().entrySet();
        for (Map.Entry entry : header){
            headerResponse.append(entry.getKey() + ":" + entry.getValue());
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
            headerResponse.append("Content-Type: text/xml; charset=utf-8\n");
            headerResponse.append("Content-Length:" + response.getContentLength()+ '\n');
        }
    }

    private String buildBody(Response response) {
        if (response.getBody() == null){
            return "";
        } else {
            return "\r\n" + response.getBody();
        }
    }

}