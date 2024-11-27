package org.unesp.monitors;

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
        File writeFile = delivery.getFile();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'H'mm'M'ss'S'");
        if (writeFile != null) {
            try (FileWriter escritor = new FileWriter(writeFile, true)) {
                String horario = LocalTime.now().format(formatter);

                escritor.append(horario).append(" - ").append(message);
                escritor.append("\n");

            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo " + delivery.getId() + " não encontrado na lista.");
        }
    }
}
