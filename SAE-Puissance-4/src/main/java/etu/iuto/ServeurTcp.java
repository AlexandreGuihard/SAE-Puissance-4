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

    public static void main(String[] args) throws IOException{

        try{
            List<Thread> client = new ArrayList<Thread>();
            
            ServerSocket serverSoket = new ServerSocket(1111);//choix du port
            System.out.println("serveur en attente");
            while(true){
            Socket clientSocket = serverSoket.accept();
            System.out.println("connection etablie avec le client");
            Thread t = new Thread(new ClientHandler(clientSocket));
            t.start();
            }
            //serverSoket.close();
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);

        }
        
    }
}
    
