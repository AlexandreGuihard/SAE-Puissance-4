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

    writer.println("name");
    while (!"quit".equals(read)) {

        writer.println("que voulez vous faire ?");
        read=reader.readLine();
        System.out.println(read);

        switch(read)
        {
        case "quit":
            writer.println("quit");
           break;
   
       case "liste gamme":
           writer.println("voici la liste des joeur disponible");
           break;
   
       case "partie":
           writer.println("veux tu jouer avec ?");
           break;

        case "oui":
            writer.println("voici la liste des joeur disponible");
            break;

        
        
       default:
           System.out.println("non pris en charge");
           break;
        }


        
    }

    System.out.println("fin de communication avec");
    writer.println("quit");
    reader.close();  
    writer.close();
    clientSocket.close();
    
    }

    catch (Exception e){
        System.err.println("[erreur]" + e);

    }

    }
}
