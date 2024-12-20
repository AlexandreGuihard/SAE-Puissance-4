package etu.iuto;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    public ClientHandler(Socket clientSocket){
    // initialisation
    this.clientSocket = clientSocket;
    }

    public void run(){
    try{

    BufferedReader reader = new BufferedReader( new InputStreamReader(this.clientSocket.getInputStream()) );
    PrintWriter writer = new PrintWriter(this.clientSocket.getOutputStream(),true);
    String read = "";

    while (!"quit".equals(read)) {

        writer.println("quit");
        read=reader.readLine();
        System.out.println(read);
        
    }

    System.out.println("by");
    reader.close();  
    writer.close();
    clientSocket.close();
    
    }

    catch (Exception e){
        System.err.println("[erreur]" + e);

    }

    }
}
