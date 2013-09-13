package server.request.processor;

import server.Processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessorFactory {
    private final static Processor DEFAULT_PROCESSOR = new NotFoundProcessor();

    private Map<String, Processor> processorsMapping = new ConcurrentHashMap<String, Processor>();

    public ProcessorFactory(){
        loadProcessorMapping();
    }

    public void loadProcessorMapping() {
        addProcessorMappingFromDirectory();
        addProcessors();
    }

    private void addProcessorMappingFromDirectory() {
        Processor processor = new FileProcessor();
        for (String name  : FileFactory.getPublicFileNames()){
            processorsMapping.put("/" + name, processor);
        }
    }

    private void addProcessors() {
        processorsMapping.put("/", new IndexProcessor());
        processorsMapping.put("/form", new FormProcessor());
        processorsMapping.put("/redirect", new RedirectProcessor());
        processorsMapping.put("/parameters", new ParametersProcessor());
        processorsMapping.put("/logs", new LogsProcessor());
    }

    public Processor get(String path) {
        Processor processor = processorsMapping.get(path);
        if (processor == null){
            return DEFAULT_PROCESSOR;
        } else {
            return processor;
        }
    }

}