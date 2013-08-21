import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        File file = new File("./");
        for (String value : file.list()){
            System.out.println(value);
        }

    }
}
