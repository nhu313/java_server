package server.request.processor;

import server.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessorFactory {
    private final static Processor DEFAULT_PROCESSOR = new NotFoundProcessor();

    private Map<String, Processor> processorsMapping = new ConcurrentHashMap<String, Processor>();

    public ProcessorFactory(){
        loadProcessorMapping();
    }

    public void loadProcessorMapping(){
        addProcessorMappingFromDirectory();
        addProcessors();
    }

    private void addProcessorMappingFromDirectory() {
        Processor processor = new FileProcessor();
        List<String> fileNames = DirectoryReader.fileNames();
        for (String name  : fileNames){
            processorsMapping.put("/" + name, processor);
        }
    }

    private void addProcessors() {
        try {
            loadAndAddProcessor();
        } catch (Exception e) {
            //If reading or loading the classes failed, the server cannot behave correctly.
            //TODO ...........is this right?
            throw new RuntimeException(e);
        }
    }

    private void loadAndAddProcessor() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Scanner scanner = new Scanner(new File(Config.ROUTE_PATH));
        skipHeader(scanner);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(":");
            processorsMapping.put(values[0].trim(), loadProcessor(values[1].trim()));
        }
    }

    private Processor loadProcessor(String value) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Processor) Class.forName(value).newInstance();
    }

    private void skipHeader(Scanner scanner){
        scanner.nextLine();
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