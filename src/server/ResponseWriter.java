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

        Set<Map.Entry<String, String>> header = response.getHeader().entrySet();
        for (Map.Entry entry : header){
            headerResponse.append(entry.getKey() + ":" + entry.getValue());
        }
        return headerResponse.toString();
    }

    private void buildHeaderFirstLine(Response response, StringBuffer headerResponse) {
        int code = response.getCode();
        headerResponse.append(HTTP_VERSION);
        headerResponse.append(" " + code);
        headerResponse.append(" " + HTTP_RESPONSE_MESSAGE.get(code) + '\n');
    }

    //TODO content type and length is part of header, but it's only relevant when there's a body.....????
    private String buildBody(Response response) {
        String body = "";
        String bodyContent = response.getBody();
        if (bodyContent != null){
            body += "Content-Type: text/xml; charset=utf-8\n";
            body += "Content-Length:" + bodyContent.length() + '\n';
            body += "\r\n" + bodyContent;
        }
        return body;
    }

}