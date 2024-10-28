package it.ciardi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MyThread extends Thread {

    private Socket s;

    public MyThread(Socket s) {
        this.s = s;
    }

    public void run() {

        System.out.println("Qualcuno si è collegato");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String stringaRicevuta;
            String returnMessage = "";
            ArrayList<String> lista = new ArrayList<String>();

            do {

                stringaRicevuta = in.readLine();

                System.out.println(stringaRicevuta);

                switch (stringaRicevuta) {
                    case "!":
                        out.writeBytes(returnMessage + "\n");
                        returnMessage = "!";
                        break;

                    case "?":
                    
                        for (int i = 0; i < lista.size(); i++) {
                            returnMessage = lista.get(i) + "\n";
                            System.out.println(returnMessage);
                            out.writeBytes(returnMessage + "\n");
                        }
                        out.writeBytes("@" + "\n");
                        break;

                    default:
                        lista.add(stringaRicevuta);
                        returnMessage = "OK";
                        System.out.println(returnMessage);
                        out.writeBytes(returnMessage + "\n");
                        break;
                }
            } while (!stringaRicevuta.equals("!"));

            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}