package server.request.processor;

import server.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryReader {

    public static List<String> fileNames(){
        File dir = new File(Config.DIRECTORY_PATH);
        List<String> files = new ArrayList<String>();
        for (String fileName : dir.list()){
            if (!Config.PRIVATE_PATH.equals(fileName)){
                files.add(fileName);
            }
        }
        return files;
    }
}
