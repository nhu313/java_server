package server.request.processor;

import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;
import server.Config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DirectoryReader {

    public static List<String> fileNames(){
//        CodeSource src = DirectoryReader.class.getProtectionDomain().getCodeSource();
//        if (src != null) {
//            URL jar = src.getLocation();
//            try {
//                ZipInputStream zip = new ZipInputStream(jar.openStream());
//                ZipEntry entry = zip.getNextEntry();
//                while (entry != null){
//                    if (entry.isDirectory())
//                    System.out.println(entry.getName());
//                    entry = zip.getNextEntry();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//        }



        String private_dir_name = Config.PRIVATE_PATH.substring(1);
        File dir = new File(Config.DIRECTORY_PATH);
        List<String> files = new ArrayList<String>();
        System.out.println(dir.exists());
        for (String fileName : dir.list()){
            if (!private_dir_name.equals(fileName)){
                files.add(fileName);
            }
        }
        return files;
    }
}
