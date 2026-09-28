package com.rodrigo.demo;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

// Classe separada: recebe as dependências do cliente pelo construtor.
public class IncomingReader implements Runnable {
    private final BufferedReader reader;
    private final JTextArea incoming;
    private final Runnable onDisconnect;

    public IncomingReader(BufferedReader reader, JTextArea incoming, Runnable onDisconnect) {
        this.reader = Objects.requireNonNull(reader, "reader");
        this.incoming = Objects.requireNonNull(incoming, "incoming");
        this.onDisconnect = Objects.requireNonNull(onDisconnect, "onDisconnect");
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("read " + message);
                String received = message;
                // A leitura acontece em outra thread; a interface pertence à thread do Swing.
                SwingUtilities.invokeLater(() -> incoming.append(received + "\n"));
            }
        } catch (IOException e) {
            System.err.println("Leitura encerrada: " + e.getMessage());
        } finally {
            SwingUtilities.invokeLater(onDisconnect);
        }
    }
}
