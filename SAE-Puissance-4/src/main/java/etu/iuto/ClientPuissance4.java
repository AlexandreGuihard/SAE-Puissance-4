package main.java.etu.iuto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientPuissance4 extends Thread{
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1111;    

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader consoleInput;
    private String serverResponse;
    private Boolean enpartie;

    private String playerName;

    public ClientPuissance4() {
        try {
            this.socket = new Socket(SERVER_HOST, SERVER_PORT);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.consoleInput = new BufferedReader(new InputStreamReader(System.in));
            this.serverResponse ="";
            this.playerName ="";
            this.enpartie =false;
        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
            this.deconnection();
        }
    }

    public BufferedReader getReader(){
        return this.in;
    }

    public PrintWriter getWriter(){
        return this.out;
    }

    public void deconnection(){
        try{
            System.out.println("fin de communication avec le serveur");
            this.out.println("quit");
            this.in.close();  
            this.out.close();
            this.socket.close();
            System.exit(0);
        }

        catch (Exception e){
            System.err.println("[erreur]" + e);
            System.exit(1);

        }

    }

    public boolean EntrezNom(){
        try{

            String nomjoueur = this.consoleInput.readLine();
            this.out.println(playerName);

            // Vérifier la réponse du serveur
            this.serverResponse = in.readLine();
            if (this.serverResponse == null || this.serverResponse.startsWith("ERR ")){
                System.out.println("réessayer : " + this.serverResponse);
                this.deconnection();
                return false;
            }

            this.playerName =nomjoueur;
            System.out.println(serverResponse);
            return true;

        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            this.deconnection();
            return false;
        }
    } 

    @Override
    public void run(){
        try{
            while (!this.EntrezNom()) {
                
            }


            /* bloucle interaction */
            String userInput;
            while ((userInput = this.consoleInput.readLine()) != null ) {
                System.out.print("> "); // Prompt
                // Envoi de la commande au serveur
                this.out.println(userInput);

                //quitter le serveur
                if ("EXIT".equalsIgnoreCase(userInput) || "QUIT".equalsIgnoreCase(userInput) ) {
                    System.out.println("Déconnexion...");
                    this.deconnection();
                    break;
                }

                /*lecture serveur */
                String response;
                while ((response = this.in.readLine()) != null || (response = this.in.readLine()) != "") {
                    System.out.println(response);
                    if (!this.in.ready()) break;
                }
            }
            if (userInput == null) {
                this.deconnection();
            }
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            this.deconnection();
        }
    }

    public static void main(String[] args) {
        try {
            ClientPuissance4 clientPuissance4 = new ClientPuissance4();
            clientPuissance4.start();
            System.out.println("Connecté au serveur !");
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            
        }
    }
}
