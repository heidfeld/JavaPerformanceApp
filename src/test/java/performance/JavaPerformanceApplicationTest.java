package performance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import performance.control.algorithm.PerformanceAlgorithm;

/**
 * Created by Lukasz Karmanski
 */
public class JavaPerformanceApplicationTest {

    @InjectMocks
    private PerformanceAlgorithm performanceAlgorithm;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCDI() {
        String result = performanceAlgorithm.run();
        Assert.assertNotNull(performanceAlgorithm);
        Assert.assertNotNull(result);
    }

}
