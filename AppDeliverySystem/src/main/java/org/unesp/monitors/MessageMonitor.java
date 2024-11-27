package org.unesp.monitors;

import org.unesp.builders.DeliveryBuilder;
import org.unesp.entities.Delivery;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageMonitor {

    public void showInformation(String message, Delivery delivery) {
        printMessageOnScreen(message);
        writeMessageOnFile(message, delivery);
    }

    protected void printMessageOnScreen(String message) {
        System.out.println(message);
    }

    protected void writeMessageOnFile(String message, Delivery delivery) {
        File writeFile = null;
        for(File file : DeliveryBuilder.getListFiles()){
            if (file.getName().equals(delivery)) {
                writeFile = file;
                break;
            }
        }

        if (writeFile != null) {
            try (FileWriter escritor = new FileWriter(writeFile)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHHMMSS");
                String hour = LocalTime.now().format(formatter);

                escritor.append(hour).append(message);
                escritor.append("\n");

            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo " + delivery.getId() + " não encontrado na lista.");
        }

        // 15H30M23S - Pacote P65 foi descarregado pelo veículo V3 no centro de redistribuição C4
        // 14H28M49S - Veículo v3 saiu do centro de redistribuição C3 em direção ao centro de redistribuição C4 com o pacote P65
    }
}
