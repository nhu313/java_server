package server.request.processor;

import server.Config;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;
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
        try {
            loadAndAddProcessor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAndAddProcessor() throws FileNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException, URISyntaxException {
        Scanner scanner = new Scanner(FileFactory.getFile(Config.ROUTE_PATH));
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