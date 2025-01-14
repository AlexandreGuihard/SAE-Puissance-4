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

    private List<Thread> client;
    private ServerSocket serverSoket;
    
    
    public ServeurTcp(int port) {
        try{
            this.serverSoket = new ServerSocket(port);
            System.out.println("serveur en attente");
            this.client = new ArrayList<Thread>();
        }
        catch (Exception e) {
            System.err.println("[erreur]" + e);
        }
    }

    public List<Thread> getClient() {
        return client;
    }
    
    public ServerSocket getServerSoket() {
        return serverSoket;
    }


    public String afficherClient(){
        String lesclient = "";
        int compteur=0;
        if (this.client == null || this.client.isEmpty()){
               return "pas de client en vue";
        }
        else{
            for (Thread element :this.client){

                if (compteur == this.client.size() || this.client.size() == 1) {
                    lesclient += "["+element+","+ compteur +"]";
                }
                else{
                    lesclient += "["+element+","+ compteur +"],";
                }
                compteur++;
                
            }
            return "la liste des client est "+ lesclient;
        }
    }


    public static void main(String[] args) {

        try{
            
            ServeurTcp serveurTcp = new ServeurTcp(1111);
            ServerSocket serverSoket = serveurTcp.getServerSoket();
            List<Thread> client = serveurTcp.getClient();

            while(true){

                Socket clientSocket = serverSoket.accept();
                System.out.println("connection etablie avec le client");
                Thread t = new Thread(new ClientHandler(clientSocket,serveurTcp));
                t.start();
            
            }
            //serverSoket.close();
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);

        }
        
    }
}
    
