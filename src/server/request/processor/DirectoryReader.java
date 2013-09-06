package server.request.processor;

import server.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {

    public static List<String> fileNames(){
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
