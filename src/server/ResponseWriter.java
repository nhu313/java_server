package server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

public class ResponseWriter {
    public static final String HTTP_VERSION = "HTTP/1.1";

    public void write(OutputStream output, Response response) throws IOException {
        output.write(buildHeader(response).getBytes());
        if (response.getBody() != null){
            output.write("\r\n".getBytes());
            output.write(response.getBody());
        }
    }

    private String buildHeader(Response response) {
        StringBuffer headerResponse = new StringBuffer();
        buildHeaderFirstLine(response, headerResponse);
        buildHeaderContentInfo(response, headerResponse);
        buildOtherHeaderInfo(response, headerResponse);
        return headerResponse.toString();
    }

    private void buildOtherHeaderInfo(Response response, StringBuffer headerResponse) {
        Set<Map.Entry<String, String>> header = response.getHeader().entrySet();
        for (Map.Entry entry : header){
            headerResponse.append(entry.getKey() + ":" + entry.getValue() + "\n");
        }
    }

    private void buildHeaderFirstLine(Response response, StringBuffer headerResponse) {
        ResponseCode code = response.getCode();
        headerResponse.append(HTTP_VERSION);
        headerResponse.append(" " + code.getCode());
        headerResponse.append(" " + code.getDescription() + '\n');
    }

    private void buildHeaderContentInfo(Response response, StringBuffer headerResponse){
        if (response.getBody() != null) {
            String contentType = "Content-Type: ";
            if (response.getContentType() == null){
                contentType += "text/html";
            } else {
                contentType += response.getContentType();
            }
            headerResponse.append(contentType + "\n");
            headerResponse.append("Content-Length: " + response.getContentLength()+ '\n');
        }
    }
}