package etu.iuto;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServeurTcp {

    public static void main(String[] args) throws IOException{

        try{
            List<Thread> client = new ArrayList<Thread>();
            ServerSocket serverSoket = new ServerSocket(1111);//choix du port

            while(true){
                System.out.println("serveur en attente");
                Socket clientSocket = serverSoket.accept();


                Thread t = new Thread(new ClientTcp(clientSocket));
                t.start();
                System.out.println("connection etablie avec le client");


                BufferedReader reader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);
                String read ="";
                
                read =reader.readLine();
                System.out.println(read);
                writer.println("message bien reçu !");
                writer.println("quit");
                
                if (read != "quit") {
                    reader.close();  
                    writer.close();
                    clientSocket.close();
                    serverSoket.close();
                }
            }

        }
        catch (Exception e){
            System.err.println("[erreur]" + e);

        }
        
    }
    
}
