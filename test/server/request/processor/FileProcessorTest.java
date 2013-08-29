package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Request;
import server.Response;

import java.io.File;
import java.io.FileInputStream;

public class FileProcessorTest {
    private static final String PUBLIC_DIRECTORY = "./test/resource";

    private Processor processor;

    @Before
    public void setUp(){
        System.setProperty("public_directory", PUBLIC_DIRECTORY);
        processor = new FileProcessor();
    }

    @Test
    public void testProcess_whenRequestMethodIsNotGet() throws Exception {
        Request request = new Request();
        request.setMethod("POST");

        Response response = new Response(405);
        Assert.assertEquals(response, processor.process(request));
    }

    @Test
    public void testProcess_whenFileDoesNotExist(){
        Request request = new Request();
        request.setMethod("GET");
        request.setPath("random path");

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
        File file = new File(PUBLIC_DIRECTORY + path);

        Response response = new Response(206);
        response.setBody("");
        response.setFileLength(file.length());
        response.setBody(readFile(path, maxLength));
        return response;
    }

    private Request createPartialRequest(String path, int maxLength) {
        Request request = new Request();
        request.setMethod("GET");
        request.setMaxContentSize(maxLength);
        request.setPath(path);
        return request;
    }

    @Test
    public void testProcess_whenFileExist() throws Exception {
        assertProcessFile("/file1", null);
    }

    @Test
    public void testProcess_whenImageFileExist() throws Exception{
        assertProcessFile("/image.gif", "image/gif");
    }

    public void assertProcessFile(String filePath, String contentType) throws Exception {
        Request request = new Request();
        request.setMethod("GET");
        request.setPath(filePath);

        Response response = new Response(200);
        response.setBody(readFile(filePath, 0));
        response.setContentType(contentType);
        response.setFileLength(response.getContentLength());

        Assert.assertEquals(response, processor.process(request));
    }

    private byte[] readFile(String path, int maxLength) throws Exception {

        File file = new File(PUBLIC_DIRECTORY + path);
        long byteLength = (maxLength == 0) ? file.length() : maxLength;
        byte[] fileBytes = new byte[(int) byteLength];

        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }
}
