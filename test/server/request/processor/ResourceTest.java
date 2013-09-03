package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Config;
import server.Method;
import server.Request;
import server.Response;

import java.io.File;
import java.io.FileInputStream;

public class ResourceTest {
    private static final String DIRECTORY_PATH = "./test/resource";

    private Processor processor;

    @Before
    public void setUp(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, DIRECTORY_PATH);
        processor = new Resource();
    }

    @Test
    public void testProcess_whenRequestMethodIsNotGet() throws Exception {
        Request request = new Request();
        request.setMethod(Method.POST);

        Response response = new Response(405);
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_whenFileDoesNotExist(){
        Request request = createGetRequest("random path");

        Response response = new Response(404);
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_partialContent() throws Exception {
        final String path = "/file1";
        final int maxLength = 3;

        Request request = createPartialRequest(path, maxLength);
        Response response = createPartialResponse(path, maxLength);

        Assert.assertEquals(response, processor.process(request));
    }

    private Response createPartialResponse(String path, int maxLength) throws Exception {
        File file = new File(DIRECTORY_PATH + path);

        Response response = new Response(206);
        response.setFileLength(file.length());
        response.setBody(readFile(path, maxLength));
        return response;
    }

    private Request createPartialRequest(String path, int maxLength) {
        Request request = createGetRequest(path);
        request.setMaxContentSize(maxLength);
        return request;
    }

    @Test
    public void testProcess_whenFileExist() throws Exception {
        assertProcessReturns200Response("/file1", null);
    }

    @Test
    public void testProcess_whenImageFileExist() throws Exception{
        assertProcessReturns200Response("/image.gif", "image/gif");
    }

    private void assertProcessReturns200Response(String filePath, String contentType) throws Exception {
        Request request = createGetRequest(filePath);
        Response response = create200Response(filePath, contentType);

        Assert.assertEquals(response, processor.process(request));
    }

    private Response create200Response(String filePath, String contentType) throws Exception {
        Response response = new Response(200);
        response.setBody(readFile(filePath, 0));
        response.setContentType(contentType);
        response.setFileLength(response.getContentLength());
        return response;
    }

    private Request createGetRequest(String filePath) {
        Request request = new Request();
        request.setMethod(Method.GET);
        request.setPath(filePath);
        return request;
    }

    private byte[] readFile(String path, int maxLength) throws Exception {
        File file = new File(DIRECTORY_PATH + path);
        long byteLength = (maxLength == 0) ? file.length() : maxLength;
        byte[] fileBytes = new byte[(int) byteLength];

        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }
}