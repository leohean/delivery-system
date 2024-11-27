package org.unesp.builders;

import org.unesp.entities.Delivery;
import org.unesp.entities.Redistributor;
import org.unesp.entities.Vehicle;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeliveryBuilder {

    private static List<File> listFiles = Collections.synchronizedList(new ArrayList<>());

    public DeliveryBuilder(int numDeliveries){

        List<Runnable> listDeliveries = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threadsDelivery = new ArrayList<>();

        int origin = ThreadLocalRandom.current().nextInt(0, RedistributorBuilder.getListRedistributors().size());
        int dest = ThreadLocalRandom.current().nextInt(0, RedistributorBuilder.getListRedistributors().size());

        Redistributor redistributorOrigin = RedistributorBuilder.getCircularList().getRedistributorAtPosition(origin);
        Redistributor redistributorDest = RedistributorBuilder.getCircularList().getRedistributorAtPosition(origin);

        for(int i = 0; i < numDeliveries; i++){
            Delivery delivery = new Delivery(redistributorOrigin, redistributorDest, LocalDate.now(), null, null, null);
            listDeliveries.add(delivery);

            File file = new File(delivery.getId()+".csv");
            listFiles.add(file);

            threadsDelivery.add(new Thread(listDeliveries.get(i)));
            threadsDelivery.get(i).start();
        }
    }

    public static List<File> getListFiles() {
        return listFiles;
    }

}
