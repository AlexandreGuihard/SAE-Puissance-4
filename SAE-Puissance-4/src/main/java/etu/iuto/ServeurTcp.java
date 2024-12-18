package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServeurTcp {

    public static void main(String[] args) throws IOException{

        try{
            ServerSocket serverSoket = new ServerSocket(1111);//choix du port
            System.out.println("serveur en attente");

            Socket clientSocket = serverSoket.accept();
            System.out.println("connection etablie avec le client");


            BufferedReader reader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);

            System.out.println(reader.readLine());
            writer.println("message bien reçu !");

            writer.println("by");
            

            reader.close();  
            writer.close();
            clientSocket.close();
            serverSoket.close();
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);

        }
        
    }
    
}
