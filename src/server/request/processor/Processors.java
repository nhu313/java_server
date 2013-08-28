package server.request.processor;

import server.Request;

import java.util.HashMap;
import java.util.Map;

public class Processors {

    private final static Processor DEFAULT_PROCESSOR = new NotFound();

    private final static Map<String, Processor> processors = new HashMap<String, Processor>();
    static
    {
        processors.put("/", new Index());
        processors.put("/form", new Form());
        processors.put("/redirect", new Redirect());
        processors.put("/parameters", new Parameters());
        processors.put("/image.gif", new FileProcessor());
        processors.put("/image.jpeg", new FileProcessor());
        processors.put("/image.png", new FileProcessor());
        processors.put("/file1", new FileProcessor());
    }

    public static Processor get(Request request){
        Processor processor = processors.get(request.getPath());
        if (processor == null){
            return DEFAULT_PROCESSOR;
        } else {
            return processor;
        }

    }
}
