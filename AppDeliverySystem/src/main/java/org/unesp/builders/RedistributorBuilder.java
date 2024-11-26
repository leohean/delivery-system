package org.unesp.builders;

import org.unesp.dataStructure.CircularList;
import org.unesp.entities.Redistributor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedistributorBuilder {
    private static List<Runnable> listRedistributors = Collections.synchronizedList(new ArrayList<>());
    private static CircularList circleList = new CircularList();

    public RedistributorBuilder(int numRedistributors){
        List<Thread> threadsRedistributors = new ArrayList<>();

        for(int i = 0; i < numRedistributors; i++){
            Redistributor redistributor = new Redistributor(null, null);

            listRedistributors.add(redistributor);
            circleList.add(redistributor);

            threadsRedistributors.add(new Thread(listRedistributors.get(i)));
            threadsRedistributors.get(i).start();
        }
    }

    public static List<Runnable> getListRedistributors() {
        return listRedistributors;
    }

    public static CircularList getCircularList() {
        return circleList;
    }

}
