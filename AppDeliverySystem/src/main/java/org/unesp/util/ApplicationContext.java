package org.unesp.util;

import org.unesp.dataStructure.CircularList;

public class ApplicationContext {
    private static CircularList circularList;
    private static int remainingDelivery;

    public static void setCircularList(CircularList list) {
        circularList = list;
    }

    public static CircularList getCircularList() {
        return circularList;
    }

    public static int getRemainingDelivery() {
        return remainingDelivery;
    }

    public static void setRemainingDelivery(int remainingDelivery) {
        ApplicationContext.remainingDelivery = remainingDelivery;
    }
}
