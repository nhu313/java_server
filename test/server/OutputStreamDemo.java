package server;

import java.io.*;

public class OutputStreamDemo {

    public static void main(String[] args) {
        byte[] b = {'h', 'e', 'l', 'l', 'o'};
        try {

            // create a new output stream
            OutputStream os = new FileOutputStream("test.txt");

            // craete a new input stream
            InputStream is = new FileInputStream("test.txt");

            // write something
            os.write(b);

            // read what we wrote
            for (int i = 0; i < b.length; i++) {
                System.out.print("" + (char) is.read());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
