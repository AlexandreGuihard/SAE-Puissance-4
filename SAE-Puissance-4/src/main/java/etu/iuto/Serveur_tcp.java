package etu.iuto;
import java.io.IOException;
import java.net.ServerSocket;

public class Serveur_tcp {

    public static void main(String[] args) throws IOException{

        try{
            ServerSocket se = new ServerSocket();
        }
        catch (Exception e){
            System.out.println("[erreur]" + e);

        }
        
    }
    
}
