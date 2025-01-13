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

public class ClientTcp implements Runnable {

    private String ip;
    private String nomjeur;
    private Socket clientSocket;
    private boolean ecrit;

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public ClientTcp(String ip,String nomjeur){
        this.ip = ip ;
        this.nomjeur = nomjeur ;
        this.ecrit = true;
        try{
        this.clientSocket = new Socket(this.ip,1111);
        }
        catch(Exception e){
            System.err.println("[erreur]" + e);
        }
    }

    public void bloquerComunnication(){
      lock.lock();
      try{
        condition1.await();
      }
      catch(InterruptedException e ){
        System.out.println("help");
      }
      finally{
        lock.unlock();
      }
    }        

    public void unlockCommunication(){
        lock.lock(); 
        condition1.signal();        
        lock.unlock();
    }  

    public void libererTout(){
        lock.lock(); 
        condition1.signalAll();        
        lock.unlock();
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
                this.ecrit = true;

                
                if ("name".equals(read)) {
                    this.ecrit = false;
                    writer.println(this.getNomjeur());
                    writer.println(this.getIp());
                }
                if (this.ecrit) {
                    String ecrit = myObj.nextLine();
                    writer.println(ecrit);
                }
                
            
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
