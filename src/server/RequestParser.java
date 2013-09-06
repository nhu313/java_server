package server;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public Request parse(InputStream requestString) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(requestString));
        Request request = new Request();
        setHeader(request, reader);
        setBody(reader, request);
        return request;
    }

    private void setBody(BufferedReader reader, Request request) throws IOException {
        if (request.getContentLength() > 0){
            request.setBody(getBody(request, reader));
        }
    }

    private void setHeader(Request request, BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        String[] values = firstLine.split(" ");
        request.setMethod(values[0]);
        setPathAndParams(request, values[1]);

        Map<String, String> header = getHeader(reader);
        request.setContentLength(getContentLength(header));
        request.setHost(header.get("Host"));
        setMaxContentSize(request, header.get("Range"));
        setAuthentication(request, header.get("Authorization"));
    }

    private void setAuthentication(Request request, String authorization) {
        if (authorization != null){
            String header = "Basic ";
            int start = authorization.indexOf(header) + header.length();
            String base64Text = authorization.substring(start);
            byte[] authenticationText = DatatypeConverter.parseBase64Binary(base64Text);
            String[] values = new String(authenticationText).split(":");

            request.setUsername(values[0]);
            request.setPassword(values[1]);
        }
    }

    private void setMaxContentSize(Request request, String range) {
        if (range != null){
            String[] ra = range.split("-");
            request.setMaxContentSize(Integer.parseInt(ra[1]));
        }
    }

    private void setPathAndParams(Request request, String value) {
        String path = value;
        if (hasParam(value)){
            int endOfPath = value.indexOf("?");
            path = value.substring(0, endOfPath);

            String paramsString = value.substring(endOfPath + 1);
            setParams(request, paramsString);
        }
        request.setPath(path);
    }

    private void setParams(Request request, String value) {
        Map<String, String> params = new HashMap<String, String>();
        String[] paramValues = value.split("&");

        for (String paramString : paramValues){
            String[] param = paramString.split("=");
            params.put(param[0], getDecodedParam(param[1]));
        }

        request.setParams(params);
    }

    private String getDecodedParam(String param) {
        String decodedParam = null;
        try {
            decodedParam = URLDecoder.decode(param, Config.ENCODE);
        } catch (UnsupportedEncodingException e) {
            Logger.error("Cannot decode parameter" + param, e);
        }
        return decodedParam;
    }

    private boolean hasParam(String path) {
        return path.contains("?");
    }

    private int getContentLength(Map<String, String> header) throws IOException {
        String bodyLength = header.get("Content-Length");
        if (bodyLength != null && bodyLength.matches("\\d+")){
            return Integer.parseInt(bodyLength);
        }
        return 0;
    }

    private Map<String, String> getHeader(BufferedReader reader) throws IOException {
        Map<String, String> header = new HashMap<String, String>();

        String line = reader.readLine();
        while (!isEndOfSection(line)){
            int index = line.indexOf(':');
            header.put(line.substring(0, index), line.substring(index + 1).trim());
            line = reader.readLine();
        }
        return header;
    }

    private String getBody(Request request, BufferedReader reader) throws IOException {
        StringBuffer header = new StringBuffer();
        for (int i = request.getContentLength(); i > 0; i--){
        int value = reader.read();
            header.append((char) value);
        }
        return header.toString();
    }

    private boolean isEndOfSection(String line){
        return (line == null || "".equals(line));
    }
}