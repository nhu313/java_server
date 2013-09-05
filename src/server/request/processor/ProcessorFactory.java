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
        addProcessorMappingFromFile();
    }

    private void addProcessorMappingFromDirectory() {
        Processor processor = new FileProcessor();
        List<String> fileNames = DirectoryReader.fileNames();
        for (String name  : fileNames){
            processorsMapping.put("/" + name, processor);
        }
    }

    public Processor get(String path) {
        Processor processor = processorsMapping.get(path);
        if (processor == null){
            return DEFAULT_PROCESSOR;
        } else {
            return processor;
        }
    }

    private void addProcessorMappingFromFile() {
        try {
            Scanner scanner = new Scanner(new File(Config.ROUTE_PATH));
            skipHeader(scanner);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] values = line.split(":");

                Processor processor = (Processor) Class.forName(values[1].trim()).newInstance();
                processorsMapping.put(values[0].trim(), processor);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void skipHeader(Scanner scanner){
        scanner.nextLine();
    }
}