package org.unesp.util;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static Redistributor getRandomRedistributor() {
        int listSize = ApplicationContext.getCircularList().size();

        int randomPosition = ThreadLocalRandom.current().nextInt(1, listSize);
        return ApplicationContext.getCircularList().getRedistributorAtPosition(randomPosition);
    }

    public static int generateRandomTravelTime() {
        return ThreadLocalRandom.current().nextInt(3000, 15000);
    }

    public static int generateRandomLoadTime() {
        return ThreadLocalRandom.current().nextInt(1000, 5000);
    }
}
