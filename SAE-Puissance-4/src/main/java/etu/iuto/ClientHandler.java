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
    private ServeurTcp serveuractuel;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket clientSocket,ServeurTcp serveuractuel){
        // initialisation
        this.clientSocket = clientSocket;
        this.serveuractuel =serveuractuel;
        try{
            this.reader = new BufferedReader( new InputStreamReader(this.clientSocket.getInputStream()) );
            this.writer = new PrintWriter(this.clientSocket.getOutputStream(),true);
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);
        }

    }

    public boolean connect(BufferedReader reader,PrintWriter writer){
        writer.println("donnee votre nom");
        try {
            String read = reader.readLine();
            
            if ( read.isEmpty()) {
                writer.println("donnee votre nom");
                return false;
            }

            if (read.length() < 3 || read.length() > 10 ) {
                writer.println("la taille doit etre entre 3 et 10 caratere ");
                return false;
            }
            
            if ( read.indexOf(" ") != -1) {
                writer.println("il y a un espace present ");
                return false;
            }

            if


            System.out.println(read);
            return true;

        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
            return false;
        }
    }






    public void envoyerPlatreauActuel(BufferedReader reader,PrintWriter writer){
        writer.println("demande du nom");

    }
    

    public void run(){
        try{

        

        
        
        while (!this.connect(reader, writer)) {
            System.out.println("serveur en attente");
        }

        this.serveuractuel.getClient().add(this.clientSocket);
        
        


        while (!"quit".equals(read)) {

            writer.println("que voulez vous faire ?");
            read=reader.readLine();
            System.out.println(read);

            switch(read)
            {
            case "quit":
                writer.println("quit");
            break;

            case "liste joueur":
                writer.println(this.serveuractuel.afficherClient());
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
