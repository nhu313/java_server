package server.request.processor;

import junit.framework.Assert;
import org.junit.Test;

public class FourOhFourTest {

    @Test
    public void testProcess(){
        Processor processor = new FourOhFour();
        Assert.assertEquals(404, processor.process(null).getCode());
    }
}
