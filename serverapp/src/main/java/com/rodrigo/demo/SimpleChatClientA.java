package com.rodrigo.demo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SimpleChatClientA {
    private JTextField outgoing;
    private PrintWriter writer;

    static void main(String[] args) {
        new SimpleChatClientA().go();
    }

    // Inicia o cliente de chat e configura a interface gráfica
    public void go() {
        // Configura a rede para se conectar ao servidor de chat
        setUpNetworking();

        outgoing = new JTextField(20);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        JPanel mainPanel = new JPanel();
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        JFrame frame = new JFrame("Simple Chat Client");
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Configura a rede para se conectar ao servidor de chat
    private void setUpNetworking() {
        try {
            // Cria um endereço de socket para o servidor de chat (localhost na porta 5000)
            InetSocketAddress serverAddress = new InetSocketAddress("localhost", 5000);

            // Abre um canal de socket para se conectar ao servidor de chat
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            // Cria um PrintWriter para enviar mensagens ao servidor de chat através do canal de socket
            writer = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));
            System.out.println("networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Envia a mensagem digitada pelo usuário para o servidor de chat
    private void sendMessage() {
        // Obtém o texto digitado pelo usuário no campo de entrada e envia para o servidor de chat
        writer.println(outgoing.getText());
        // Garante que a mensagem seja enviada imediatamente, sem esperar por mais dados
        writer.flush();
        // Limpa o campo de entrada e coloca o foco de volta nele para que o usuário possa digitar outra mensagem
        outgoing.setText("");
        // Coloca o foco de volta no campo de entrada para que o usuário possa digitar outra mensagem
        outgoing.requestFocus();
    }
}
