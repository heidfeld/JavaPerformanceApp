package com.lukasz.mgr.performance.control.requeststrike;

import com.lukasz.mgr.api.users.User;
import com.lukasz.mgr.performance.db.MongoDatabaseService;

import javax.inject.Inject;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lukasz Karmanski
 */
public class RequestStrike {

    @Inject
    private MongoDatabaseService mongoDatabaseService;

    public UsersResult getRandomUser(String collectionName) {
        long dbStart = System.currentTimeMillis();
        User user = mongoDatabaseService.getRandomUser(collectionName);
        long dbEnd = System.currentTimeMillis();
        long dbTime = dbEnd - dbStart;

        UsersResult result = new UsersResult();
        result.setAlgorithmName("User Request");
        result.setDbTime(dbTime);
        return result;
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
