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

    private ServerSocket serverSoket;
    
    
    public ServeurTcp(int port) {
        try{
            this.serverSoket = new ServerSocket(port);
            System.out.println("serveur en attente");
        }
        catch (Exception e) {
            System.err.println("[erreur]" + e);
        }
    }


    public void serveurContinue(){
        try{
            Socket clientSocket = this.serverSoket.accept();
            System.out.println("connection etablie avec le client");
            Thread t = new Thread(new ClientHandler(clientSocket,this));
            t.start();
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);
        }
    }
    

    public ServerSocket getServerSoket() {
        return this.serverSoket;
    }




    public static void main(String[] args) {

        try{
            
            ServeurTcp serveurTcp = new ServeurTcp(1111);
            while(true){
                
                serveurTcp.serveurContinue();

            
            }
            
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);
        }
        
    }
}
    
