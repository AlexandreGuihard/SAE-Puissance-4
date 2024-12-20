package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcp implements Runnable {

    private String ip;
    private String nomjeur;
    private Socket clientSocket;

    public ClientTcp(String ip,String nomjeur){
        this.ip = ip ;
        this.nomjeur = nomjeur ;
        try{
        this.clientSocket = new Socket(this.ip,1111);
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

    @Override
    public void run(){
        // Interaction avec le client

        try{

        BufferedReader reader = new BufferedReader( new InputStreamReader(this.getClientSocket().getInputStream()) );
        PrintWriter writer = new PrintWriter(this.getClientSocket().getOutputStream(),true);
        System.out.println("marche");

        Scanner myObj = new Scanner(System.in);
        String read ="";

        while (!"quit".equals(read)) {
            read=reader.readLine();
            System.out.println(read);

            if (read != "quit") {

                String ecrit = myObj.nextLine();
                writer.println(ecrit);
                
            
            }
        }
        writer.println("quit");

        reader.close();  
        writer.close();
        this.getClientSocket().close();
        
        }

        catch(Exception e){
            System.err.println("[erreur]" + e);
        }

        }

}
