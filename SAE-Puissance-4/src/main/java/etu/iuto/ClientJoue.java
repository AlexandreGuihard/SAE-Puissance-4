package etu.iuto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientJoue implements Runnable{
    private Player joueur1;
    private Player joueur2;

    private BufferedReader readerDuJoueur1;
    private PrintWriter WriterDuJoueur1;

    private BufferedReader readerDuJoueur2;
    private PrintWriter WriterDuJoueur2;

    //private Jeu jeu;


    public ClientJoue(Player joueur1 ,Player joueur2 ){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.readerDuJoueur1 = this.joueur1.getReaderDuJoueur();
        this.WriterDuJoueur1 = this.joueur1.getWriterDuJoueur();

        this.readerDuJoueur2 = this.joueur2.getReaderDuJoueur();
        this.WriterDuJoueur2 = this.joueur2.getWriterDuJoueur();

        //this.jeu = new Jeu();


    }

    public void run(){
        this.WriterDuJoueur1.println("votre party avec "+ this.joueur2.getName() + " vas commencer !");
        this.WriterDuJoueur2.println("votre vous faire une parti avec "+ this.joueur1.getName() + " vas commencer !");

    }

}