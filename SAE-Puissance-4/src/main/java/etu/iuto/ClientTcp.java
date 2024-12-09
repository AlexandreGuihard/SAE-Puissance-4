package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTcp {
    public static void main(String[] args) throws IOException{
        try{
        Socket clientSocket = new Socket("localhost",8080);

        BufferedReader reader = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()) );
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);

        writer.println("je suis le client");
        System.out.println(reader.readLine());

        reader.close();  
        writer.close();
        clientSocket.close();

    }

    catch(Exception e){
        System.err.println("[erreur]" + e);
    }
    }
}
