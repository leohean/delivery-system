package org.unesp.util;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static Redistributor getRandomRedistributor(CircularList circularList) {
        int listSize = circularList.size();

        if (listSize == 0) {
            return null;
        }

        int randomPosition = ThreadLocalRandom.current().nextInt(listSize);
        Redistributor redistributor = circularList.head.getRedistributor();

        for (int i = 0; i < randomPosition; i++) {
            redistributor = circularList.getNextRedistributor(redistributor);
        }
        return redistributor;
    }

    public static void 
}