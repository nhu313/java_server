package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.*;

public class IndexProcessorTest {
    private Processor processor;

    @Before
    public void setUp(){
        processor = new IndexProcessor();
    }

    @Test
    public void testProcess(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
        Request request = new Request(HttpMethod.GET.name(), "/");

        Response response = new Response(ResponseCode.OK);
        response.setBody(buildBody());

        Assert.assertEquals(response, processor.process(request));
    }

    private String buildBody() {
        StringBuffer body = new StringBuffer();
        body.append("<ul>");
        body.append("<li><a href=\"/file1\">file1</a></li>");
        body.append("<li><a href=\"/image.gif\">image.gif</a></li>");
        body.append("</ul>");
        return body.toString();
    }
}
