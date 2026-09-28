package com.rodrigo.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatClientA {
    private JTextField outgoing;
    private PrintWriter writer;
    private BufferedReader reader;
    private JTextArea incoming;
    private JButton sendButton;
    private SocketChannel socket;
    private ExecutorService executor;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleChatClientA().go());
    }

    // Monta a interface antes de iniciar a leitura de mensagens.
    public void go() {
        JScrollPane scroller = createScrollableTextArea();
        outgoing = new JTextField(20);
        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());
        outgoing.addActionListener(e -> sendMessage());

        JPanel sendPanel = new JPanel();
        sendPanel.add(outgoing);
        sendPanel.add(sendButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scroller, BorderLayout.CENTER);
        mainPanel.add(sendPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame("Simple Chat Client");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeNetworking();
            }
        });
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        try {
            setUpNetworking();
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new IncomingReader(reader, incoming, this::handleDisconnect));
        } catch (IOException e) {
            closeNetworking();
            outgoing.setEnabled(false);
            sendButton.setEnabled(false);
            incoming.append("Não foi possível conectar a localhost:5000.\n"
                    + "Inicie SimpleChatServer e abra o cliente novamente.\n");
            System.err.println("Falha na conexão: " + e.getMessage());
        }
    }

    private JScrollPane createScrollableTextArea() {
        // Atribui ao campo da classe, sem criar outra variável com o mesmo nome.
        incoming = new JTextArea(15, 30);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroller;
    }

    private void setUpNetworking() throws IOException {
        socket = SocketChannel.open(new InetSocketAddress("localhost", 5000));
        // Atribui o leitor ao campo da classe, sem criar uma variável local.
        reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
        writer = new PrintWriter(Channels.newWriter(socket, StandardCharsets.UTF_8), true);
        System.out.println("networking established");
    }

    private void sendMessage() {
        if (writer == null || !sendButton.isEnabled()) {
            return;
        }
        String message = outgoing.getText();
        if (message.isBlank()) {
            return;
        }
        // autoFlush=true envia a linha imediatamente.
        writer.println(message);
        if (writer.checkError()) {
            incoming.append("Não foi possível enviar a mensagem.\n");
            handleDisconnect();
            return;
        }
        outgoing.setText("");
        outgoing.requestFocusInWindow();
    }

    private void handleDisconnect() {
        boolean wasConnected = sendButton.isEnabled();
        outgoing.setEnabled(false);
        sendButton.setEnabled(false);
        closeNetworking();
        if (wasConnected) {
            incoming.append("Conexão encerrada.\n");
        }
    }

    private void closeNetworking() {
        // Fechar o socket desbloqueia readLine(), permitindo encerrar a thread.
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        if (executor != null) {
            executor.shutdownNow();
        }
    }
}
