package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientTcp {

    private String ip;
    private String nomjeur;
    private Socket clientSocket;

    public ClientTcp(String ip,String nomjeur ){
        this.ip = ip ;
        this.nomjeur = nomjeur ;
        try{
        this.clientSocket = new Socket("localhost",1111);
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


    public static void main(String[] args) throws IOException{
        try{
        ClientTcp client1 = new ClientTcp("192.168.1.10","jj");

        BufferedReader reader = new BufferedReader( new InputStreamReader(client1.getClientSocket().getInputStream()) );
        PrintWriter writer = new PrintWriter(client1.getClientSocket().getOutputStream(),true);
        
        while (reader.readLine() != "quit") {
            System.out.println(reader.readLine());
            System.out.println("marche");
            writer.println("quit");
            
        }
        
        System.out.println("fin");

        

        reader.close();  
        writer.close();
        client1.getClientSocket().close();

    }

    catch(Exception e){
        System.err.println("[erreur]" + e);
    }
    }
}
