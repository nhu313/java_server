package server.request.processor;

import server.Request;

import java.util.HashMap;
import java.util.Map;

public class Processors {

    private final static Processor DEFAULT_PROCESSOR = new FourOhFour();

    private final static Map<String, Processor> processors = new HashMap<String, Processor>();
    static
    {
        processors.put("/", new Index());
        processors.put("/form", new Form());
    }

    public static Processor getProcessor(Request request){
        Processor processor = processors.get(request.getPath());
        if (processor == null){
            return DEFAULT_PROCESSOR;
        } else {
            return processor;
        }

    }
}
