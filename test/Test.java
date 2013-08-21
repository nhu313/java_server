import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    @org.junit.Test
    public void test() throws UnsupportedEncodingException {
        String mess = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-" +
                "%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&" +
                "variable_2=stuff";
        System.out.println(URLDecoder.decode(mess, "UTF-8"));
        System.out.println(URLEncoder.encode("<", "UTF-8"));

        System.out.println("Something".substring(0, "Something".length() - 1));

    }
}
