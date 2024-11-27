package org.unesp.services;

import org.unesp.entities.Delivery;
import org.unesp.monitors.MessageMonitor;

public class DeliveryService {
    private MessageMonitor messageMonitor;

    public void writeDeliveryAtDestination(Delivery delivery){
        String message = "Pacote "+delivery.getId()+" foi descarregado pelo veículo "+delivery.getAssociatedVehicle().getId()+" no centro de redistribuição "+delivery.getRedistributorDestination();
        messageMonitor.showInformation(message, delivery);
    }

    public void writeDeliveryTrack(Delivery delivery){
        String message = "Veículo "+delivery.getAssociatedVehicle().getId()+" saiu do centro de redistribuição "+delivery.getRedistributorDestination().getId()+" em direção ao centro de redistribuição "+delivery.getRedistributorDestination().getId()+" com o pacote "+delivery.getId();
        messageMonitor.showInformation(message, delivery);
    }

}
