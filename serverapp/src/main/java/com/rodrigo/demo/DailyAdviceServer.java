package com.rodrigo.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class DailyAdviceServer {
    // Conselhos diários para o usuário
    final private String[] adviceList = {
            "Don't forget to take breaks while working.",
            "Stay hydrated and drink plenty of water.",
            "Exercise regularly to maintain good health.",
            "Get enough sleep to stay alert and focused.",
            "Practice mindfulness and meditation for mental well-being."
    };
    // Gerador de números aleatórios para selecionar conselhos
    private final Random random = new Random();


    // Inicia o servidor e aguarda conexões de clientes
    public void go() {
        // Cria um canal de servidor para aceitar conexões de clientes
        // ServerSocketChannel faz com que esta aplicação ouça as requisições na porta a qual está vinculada
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            // Vincula o canal do servidor a uma porta específica (5000 neste caso)
            serverChannel.bind(new InetSocketAddress(5000));

            // Enquanto o canal do servidor estiver aberto, aguarda conexões de clientes
            while (serverChannel.isOpen()) {
                // Aceita uma conexão de cliente e cria um canal de socket para comunicação
                // Quando não existe nenhum cliente conectado, o método accept() fica em espera até que um cliente se conecte
                SocketChannel clientChannel = serverChannel.accept();

                // Cria um PrintWriter para enviar dados ao cliente através do canal de socket
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(clientChannel));

                // Obtém um conselho aleatório da lista e envia ao cliente
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int nextAdvice = random.nextInt(adviceList.length);
        return adviceList[nextAdvice];
    }
}
