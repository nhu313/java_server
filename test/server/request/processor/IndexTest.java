package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Config;
import server.Request;
import server.Response;

public class IndexTest {
    private Processor processor;

    @Before
    public void setUp(){
        processor = new Index();
    }

    @Test
    public void testProcess(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, "./test/resource");
        Request request = new Request("GET", "/");

        Response response = new Response(200);
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
