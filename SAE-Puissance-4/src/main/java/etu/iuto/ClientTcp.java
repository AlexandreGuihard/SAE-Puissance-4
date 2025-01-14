package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientTcp {

    private String ip;
    private String nomjeur;
    private Socket clientSocket;
    private int port;



    public ClientTcp(String ip,String nomjeur,int port){
        this.ip = ip ;
        this.nomjeur = nomjeur ;
        this.port = port;

        try{
        this.clientSocket = new Socket(this.ip,port);
        }

        catch(Exception e){
            System.err.println("[erreur]" + e);
        }
    }


    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNomjeur() {
        return nomjeur;
    }

    public void setNomjeur(String nomjeur) {
        this.nomjeur = nomjeur;
    }
    
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {
        
    
        // Interaction avec le client

        try{
        InetAddress adrLocale = InetAddress.getLocalHost();
        String ip = adrLocale.getHostAddress();
        

        ClientTcp leclient = new ClientTcp(ip,"jj",1111);

        BufferedReader reader = new BufferedReader( new InputStreamReader(leclient.getClientSocket().getInputStream()) );
        PrintWriter writer = new PrintWriter(leclient.getClientSocket().getOutputStream(),true);
        System.out.println("marche");

        Scanner myObj = new Scanner(System.in);
        String read ="";


        while (!"quit".equals(read)) {
            read=reader.readLine();
            System.out.println(read);



                
            if ("name".equals(read)) {
                writer.println(leclient.getNomjeur());
                writer.println(leclient.getIp());
            }

            
            String ecrit = myObj.nextLine();
            writer.println(ecrit);
        
                
            
            
        }
        writer.println("quit");

        reader.close();  
        writer.close();
        leclient.getClientSocket().close();
        
        }

        catch(Exception e){
            System.err.println("[erreur]" + e);
        }

        }
    }


