package performance.control.algorithm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lukasz Karmanski
 */
public class PerformanceAlgorithm {

    public String run() {
        long start = System.currentTimeMillis();
        long iterations = iterateElements();
        long time = System.currentTimeMillis() - start;
        return "<h4>Algorithm time: " + time + "ms<br>" +
                "Algorithm iterations: " + iterations + "</h4>";
    }

    private long iterateElements() {
        long iterations = 0;
        int random = ThreadLocalRandom.current().nextInt(10000, 10000000);
        for(int i = 0; i < random; i++) {
            double someResult = i * 2 + 3;
            String.valueOf(someResult);
            iterations++;
        }
        return iterations;
    }

}
