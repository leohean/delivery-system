package org.unesp.util;

import org.unesp.dataStructure.CircularList;

public class ApplicationContext {
    private static CircularList circularList;

    public static void setCircularList(CircularList list) {
        circularList = list;
    }

    public static CircularList getCircularList() {
        return circularList;
    }
}
