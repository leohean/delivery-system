package org.unesp.services;

import org.unesp.entities.Redistributor;

public class RedistributorService {
    public int getRemainingDeliveriesNumber(Redistributor redistributor) {
        return redistributor.getListOfDeliveries().size();
    }
}
