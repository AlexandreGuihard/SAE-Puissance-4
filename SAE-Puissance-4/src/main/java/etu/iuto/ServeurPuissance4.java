package main.java.etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServeurPuissance4 {
    private static final int PORT = 1111;
    
    private  Map<String, Client> players = Collections.synchronizedMap(new HashMap<>());
    private  Map<String, Client> joueurDisponible = Collections.synchronizedMap(new HashMap<>());

    private  Map<String, ClientJoue> partieEncours = Collections.synchronizedMap(new HashMap<>());
    private List<String> listinterdite;
    private  String symbols ;

    private ServerSocket serverSocket;
    
    public ServeurPuissance4(){
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.listinterdite = new ArrayList<>(List.of("LIST", "ASK", "EXIT","QUIT","PLAY"));
            this.symbols = "[!@#$%^&*()\\-_=+\\[\\]{}\\\\|;:'\",<.>/?~`§°€£¥¤©®™¶µ•¿¡÷×±∞Ωπ√≠≈≤≥←→↑↓↔↕∂∑∏∫∴∵∧∨∩∪⊂⊃⊆⊇⊕⊗⊥∅∆∇⋅∝∈∉∋∌∠∀∃∴⟨⟩«»¢‰…†‡¤÷×ØøÅåÆæßƒ]";
        }
        catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }

    public List<String> getListinterdite(){
        return this.listinterdite;
    }

    public String getListCaractereInterdie(){
        return this.symbols;
    }


    public Map<String, Client> getPlayers() {
        return this.players;
    }

    public Map<String, Client> getJoueurDisponible() {
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

    public boolean partie(Client player1, Client player2){

        PrintWriter writer2 = player2.getWriter();
        BufferedReader reader2 = player2.getReader();
        PrintWriter writer1 = player1.getWriter();
        BufferedReader reader1 = player1.getReader();

        writer2.println("Si voulez vous faire une parti avec "+ player1.getNom() + " écrivez : 'ok'. sinon non");
        try{
            String response = reader2.readLine();
            while (!"ok".equals(response) && !"non".equals(response)) {
                writer2.println("ces ok ou non !");
                response = reader2.readLine();
            }

            if ("ok".equals(response)) {

                this.getPlayers().put(player1.getNom(), player1);
                this.getPlayers().put(player2.getNom(), player2);

                this.getJoueurDisponible().remove(player2.getNom());
                this.getJoueurDisponible().remove(player1.getNom());

                ClientJoue partyjouer = new ClientJoue(player1, player2);
                new Thread(partyjouer).start();
                String nomParty = "partie " + (partieEncours.size()+1) ;
                partieEncours.put(nomParty, partyjouer);
                return true;
            }
            else{
                writer2.println("by");
                return false;
            }
            
        }
        catch(Exception e){
            System.out.println(e);
            return false;
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
