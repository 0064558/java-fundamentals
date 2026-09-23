package com.rodrigo.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {
    // Lista de PrintWriters para enviar mensagens a todos os clientes conectados
    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        // Cria uma instância do servidor de chat e inicia o processo de escuta por conexões de clientes
        new SimpleChatServer().go();
    }

    // Inicia o servidor e aguarda conexões de clientes
    public void go() {
        // Cria um pool de threads para lidar com múltiplos clientes simultaneamente
        // Com threads, cada cliente conectado pode ser tratado de forma independente, permitindo que o servidor continue aceitando novas conexões enquanto processa mensagens de clientes existentes
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            // Cria um canal de servidor para aceitar conexões de clientes
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // Vincula o canal do servidor a uma porta específica (5000 neste caso)
            serverSocketChannel.bind(new InetSocketAddress(5000));

            // Enquanto o canal do servidor estiver aberto, aguarda conexões de clientes
            while (serverSocketChannel.isOpen()) {
                // Aceita uma conexão de cliente e cria um canal de socket para comunicação
                SocketChannel clientSocket = serverSocketChannel.accept();
                // Cria um PrintWriter para enviar dados ao cliente através do canal de socket
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, StandardCharsets.UTF_8));
                // Adiciona o PrintWriter à lista de clientes conectados para que possamos enviar mensagens a todos eles
                clientWriters.add(writer);
                // Submete uma nova tarefa ao pool de threads para lidar com o cliente conectado
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("got a connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para enviar uma mensagem a todos os clientes conectados
    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            // Garante que a mensagem seja enviada imediatamente, sem esperar por mais dados
            writer.flush();
        }
    }

    // Classe interna para lidar com a comunicação com um cliente específico
    public class ClientHandler implements Runnable {
        // BufferedReader para ler mensagens do cliente
        BufferedReader reader;
        SocketChannel socket;

        // Construtor que inicializa o BufferedReader com o canal de socket do cliente
        public ClientHandler(SocketChannel clientSocket) {
            socket = clientSocket;
            reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
        }

        // Método run() que será executado em uma thread separada para lidar com a comunicação com o cliente
        @Override
        public void run() {
            String message;

            try {
                // Lê mensagens do cliente em um loop até que a conexão seja encerrada
                while ((message = reader.readLine()) != null) {
                    // Quando uma mensagem é recebida, imprime no console e envia a todos os clientes conectados
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
