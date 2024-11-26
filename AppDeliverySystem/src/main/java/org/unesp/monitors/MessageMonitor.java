package org.unesp.monitors;

public class MessageMonitor {
    public void showInformation(String message) {
        printMessageOnScreen(message);
        writeMessageOnFile(message);
    }

    protected void printMessageOnScreen(String message) {
        System.out.println(message);
    }

    protected void writeMessageOnFile(String message) {
        // escrever no arquivo

        // 15H30M23S - Pacote P65 foi descarregado pelo veículo V3 no centro de redistribuição C4
        // 14H28M49S - Veículo v3 saiu do centro de redistribuição C3 em direção ao centro de redistribuição C4 com o pacote P65
    }
}
