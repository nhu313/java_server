package server.request.processor;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Config;

public class ProcessorFactoryTest {

    private ProcessorFactory factory;

    @Before
    public void setUp(){
        factory = new ProcessorFactory();
    }

    @Test
    public void testGetProcessorForFilePath(){
        Processor processor = factory.get("/file1");
        Assert.assertTrue(processor instanceof FileProcessor);
    }

    @Test
    public void testGetProcessorForImageFilePath(){
        Processor processor = factory.get("/image.gif");
        Assert.assertTrue(processor instanceof FileProcessor);
    }

    @Test
    public void testGetProcessorForPathThatDoesNotExist(){
        Processor processor = factory.get("/nonexistencepath");
        Assert.assertTrue(processor instanceof NotFoundProcessor);
    }

    @Test
    public void testGetProcessorForPrivatePath(){
        Processor processor = factory.get("/private");
        Assert.assertTrue(processor instanceof NotFoundProcessor);
    }

    @Test
    public void testGetProcessorForPathInRoute(){
        Assert.assertTrue(factory.get("/") instanceof IndexProcessor);
        Assert.assertTrue(factory.get("/form") instanceof FormProcessor);
    }
}
