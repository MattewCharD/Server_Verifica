package it.ciardi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        int porta = 3000;

        try {
            ServerSocket ss2 = new ServerSocket(porta);
            do {
                Socket s = ss2.accept();
                MyThread t = new MyThread(s);
                t.start();
            } while (true);
        
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}