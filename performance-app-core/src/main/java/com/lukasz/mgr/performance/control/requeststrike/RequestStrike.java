package com.lukasz.mgr.performance.control.requeststrike;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lukasz Karmanski
 */
public class RequestStrike {

    public String run() {
        long start = System.currentTimeMillis();
        long iterations = iterateElements();
        long time = System.currentTimeMillis() - start;
        return "<h4>Request Strike time: " + time + "ms<br>" +
                "Request Strike iterations: " + iterations + "</h4>";
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
