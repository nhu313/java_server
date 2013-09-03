import server.Method;

import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    @org.junit.Test
    public void testSet(){

    }


//    @org.junit.Test
    public void testMethod(){
        byte[] bytes = DatatypeConverter.parseBase64Binary("QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        System.out.println(new String(bytes));
    }

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
//        InputStream stream = this.getClass().getResourceAsStream("/resource/file1");
//
//
//        File file = new File("./");
//        for (String value : file.list()){
//            System.out.println(value);
//        }
        String[] values = "           :   server.request.processor.Index".split(":");
        for (String v : values){
            System.out.println(v);
        }

    }
}
