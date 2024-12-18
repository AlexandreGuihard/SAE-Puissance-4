package etu.iuto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Executable{
    public static void main(String[] args) throws IOException{
        try{
        ClientTcp client1 = new ClientTcp("localhost","jj");
        
        BufferedReader reader = new BufferedReader( new InputStreamReader(client1.getClientSocket().getInputStream()) );
        PrintWriter writer = new PrintWriter(client1.getClientSocket().getOutputStream(),true);
        System.out.println("marche");
       
        String read = "";
        while (reader.readLine() != "quit") {
            Scanner myObj = new Scanner(System.in);
            String ecrit = myObj.nextLine();
            writer.println(ecrit);
            
            read = reader.readLine();
            System.out.println(read);
            System.out.println("marche");
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