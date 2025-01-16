package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServeurPuissance4 {
    private static final int PORT = 1111;
    
    private  Map<String, Player> players = Collections.synchronizedMap(new HashMap<>());
    private  Map<String, Player> joueurDisponible = Collections.synchronizedMap(new HashMap<>());

    private  Map<String, ClientJoue> partieEncours = Collections.synchronizedMap(new HashMap<>());


    private ServerSocket serverSocket;
    
    public ServeurPuissance4(){
        try {
            this.serverSocket = new ServerSocket(PORT);
        }
        catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }

    public Map<String, Player> getPlayers() {
        return this.players;
    }

    public Map<String, Player> getJoueurDisponible() {
        return this.joueurDisponible;
    }

    public Map<String, ClientJoue> getPartieEncours() {
        return this.partieEncours;
    }


    public void connection(){
        try {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Nouveau joueur connecté : " + clientSocket.getInetAddress());
            new Thread(new ClientHandler(clientSocket,this)).start();
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }

    public void partie(Player player1,Player player2){

        PrintWriter writer2 = player2.getWriterDuJoueur();
        BufferedReader reader2 = player2.getReaderDuJoueur();
        PrintWriter writer1 = player1.getWriterDuJoueur();

        writer2.println("voulez vous faire une parti avec "+ player1.getName() + " ok ? sinon nimporte quoi d'autre");

        try{
            if ("ok".equals(reader2.readLine())) {
                ClientJoue partyjouer = new ClientJoue(player1,player2);
                new Thread(partyjouer).start();
                String nomParty = "partie " + (partieEncours.size()+1) ;
                partieEncours.put(nomParty, partyjouer);
                writer1.println("la partie vas commencer");
                return;
            }
            else{
                writer1.println("connection refuser avec "+ player1.getName());
                writer2.println("by");
                return;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        
        
    }

    public static void main(String[] args) {
        System.out.println("Démarrage du serveur...");
        ServeurPuissance4 serveurPuissance4 = new ServeurPuissance4();

        while (true) {
            serveurPuissance4.connection();
        }
       
    }

   

}
