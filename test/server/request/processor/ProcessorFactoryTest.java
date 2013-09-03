package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Config;

public class ProcessorFactoryTest {
    private static final String DIRECTORY_PATH = "./test/resource";

    private ProcessorFactory factory;

    @Before
    public void setUp(){
        System.setProperty(Config.DIRECTORY_PATH_KEY, DIRECTORY_PATH);
        factory = new ProcessorFactory();
        factory.loadProcessorMapping();
    }

    @Test
    public void testGetProcessorForFilePath(){
        Processor processor = factory.get("/file1");
        Assert.assertTrue(processor instanceof Resource);
    }

    @Test
    public void testGetProcessorForImageFilePath(){
        Processor processor = factory.get("/image.gif");
        Assert.assertTrue(processor instanceof Resource);
    }

    @Test
    public void testGetProcessorForPathThatDoesNotExist(){
        Processor processor = factory.get("/nonexistencepath");
        Assert.assertTrue(processor instanceof NotFound);
    }

    @Test
    public void testGetProcessorForPrivatePath(){
        Processor processor = factory.get("/private");
        Assert.assertTrue(processor instanceof NotFound);
    }

    @Test
    public void testGetProcessorForPathInRoute(){
        Assert.assertTrue(factory.get("/") instanceof Index);
        Assert.assertTrue(factory.get("/form") instanceof Form);
    }
}
