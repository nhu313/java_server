package server;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestParserTest {
    RequestParser parser;

    @Before
    public void setUp(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
        parser = new RequestParser();
    }

    @Test
    public void parseGetIndexRequest(){
        assertRequestDataIsSameAsInput(Method.GET, "/");
    }

    @Test
    public void parseGetSpecificPathRequest(){
        assertRequestDataIsSameAsInput(Method.GET, "/board");
    }

    @Test
    public void parseEmptyPostRequest(){
        assertRequestDataIsSameAsInput(Method.POST, "/form");
    }

    @Test
    public void testParseRequest_with1Param(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("food", "noodle");
        assertParseRequestWithParam(params);
    }

    @Test
    public void testParseRequest_with2Params(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("food", "noodle");
        params.put("place", "home");
        assertParseRequestWithParam(params);
    }

    @Test
    public void testParseRequest_withEncodingParam(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("weird", "<>");
        assertParseRequestWithParam(params);
    }

    private void assertParseRequestWithParam(Map<String, String> params){
        Method method = Method.GET;
        String simplePath = "/parameters";

        String path = simplePath + buildParamString(params);

        Request expectedRequest = createHttpRequest(method, simplePath, null);
        expectedRequest.setParams(params);
        assertRequestDataIsSameAsInput(method, path, null, expectedRequest);
    }

    private String buildParamString(Map<String, String> params) {
        String paramsString = "?";
        for (Map.Entry<String, String> entry : params.entrySet()){
            try {
                paramsString += entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), Config.ENCODE) + "&";
            } catch (UnsupportedEncodingException e) {

            }
        }
        paramsString = paramsString.substring(0, paramsString.length() - 1);
        return paramsString;
    }

    @Test
    public void testParseRequest_withAunthentication() throws IOException {
        String username = "Aladdin";
        String password = "open sesame";
        String usernamePassword = username + ":" + password;
        String requestString = "GET /logs HTTP/1.1\n" +
                "Authorization: Basic " + DatatypeConverter.printBase64Binary(usernamePassword.getBytes());
        InputStream input = new ByteArrayInputStream(requestString.getBytes());

        Request request = new Request("GET", "/logs");
        request.setUsername(username);
        request.setPassword(password);
        Assert.assertEquals(request, parser.parse(input));
    }

    @Test
    public void testParseRequest_withBody(){
        String textBody = "request body";
        assertRequestDataIsSameAsInput(Method.POST, "/file", textBody);
    }

    @Test
    public void testParseRequest_withNewLineInBody(){
        String textBody =
                "Vivian: Philip, when I met you, you were into James Brown.\n" +
                "Will: He liked James Brown?\n" +
                "Vivian: He even wore his hair like him.\n" +
                "Will: [laughs] He had hair?";
        assertRequestDataIsSameAsInput(Method.POST, "/file", textBody);
    }

    @Test
    public void testParseRequest_parseHost() throws IOException {
        String host = "www.example.com";
        String requestString = "GET / HTTP/1.1\nHost: " + host;
        InputStream input = new ByteArrayInputStream(requestString.getBytes());
        Request request = new Request("GET", "/");
        request.setHost(host);
        Assert.assertEquals(request, parser.parse(input));
    }

    @Test
    public void testParseRequest_parseHostWithPort() throws IOException {
        String host = "localhost:5000";
        String requestString = "GET / HTTP/1.1\nHost: " + host;
        InputStream input = new ByteArrayInputStream(requestString.getBytes());
        Request request = new Request("GET", "/");
        request.setHost(host);
        Assert.assertEquals(request, parser.parse(input));
    }

    private void assertRequestDataIsSameAsInput(Method method, String path) {
        assertRequestDataIsSameAsInput(method, path, null);
    }

    private void assertRequestDataIsSameAsInput(Method method, String path, String body) {
        Request expectedRequest = createHttpRequest(method, path, body);
        assertRequestDataIsSameAsInput(method, path, body, expectedRequest);
    }

    private void assertRequestDataIsSameAsInput(Method method, String path, String body, Request expectedRequest) {
        InputStream input = new ByteArrayInputStream(buildRequestString(method, path, body).getBytes());
        try {
            Assert.assertEquals(expectedRequest, parser.parse(input));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    private String buildRequestString(Method method, String path, String body) {
        StringBuffer request = new StringBuffer();
        request.append(method.name() + ' ' + path + " HTTP/1.1\n");
        setBodyContent(body, request);
        return request.toString();
    }

    private void setBodyContent(String body, StringBuffer request) {
        if (body != null){
            int contentLength = getContentLength(body);
            request.append("Content-Length: " + contentLength);
        }
        request.append("\n\r" + body);
    }

    private int getContentLength(String body) {
        return (body == null) ? 0 : body.length();
    }

    private Request createHttpRequest(Method method, String path, String body) {
        Request expectedRequest = new Request();
        expectedRequest.setMethod(method);
        expectedRequest.setPath(path);
        expectedRequest.setBody(body);
        expectedRequest.setContentLength(getContentLength(body));
        return expectedRequest;
    }
}
