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
        
        Thread t = new Thread (client1) ;
        t.start();
    }

    catch(Exception e){
        System.err.println("[erreur]" + e);
    }
    }
}