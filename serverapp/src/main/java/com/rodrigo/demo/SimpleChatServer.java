package com.rodrigo.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {
    // Conexões e desconexões podem alterar a lista enquanto outra thread envia mensagens.
    private final List<PrintWriter> clientWriters = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new SimpleChatServer().go();
    }

    public void go() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(5000));
            System.out.println("Chat server listening on port 5000");
            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(
                        Channels.newWriter(clientSocket, StandardCharsets.UTF_8), true);
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket, writer));
                System.out.println("got a connection");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdownNow();
        }
    }

    // Mantém a mesma ordem de mensagens para todos os clientes.
    private synchronized void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    public class ClientHandler implements Runnable {
        private final SocketChannel socket;
        private final PrintWriter writer;

        public ClientHandler(SocketChannel clientSocket, PrintWriter writer) {
            this.socket = clientSocket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try (socket;
                 BufferedReader reader = new BufferedReader(
                         Channels.newReader(socket, StandardCharsets.UTF_8))) {
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                System.err.println("Cliente desconectado: " + e.getMessage());
            } finally {
                clientWriters.remove(writer);
                writer.close();
            }
        }
    }
}
