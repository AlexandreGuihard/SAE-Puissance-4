package main.java.etu.iuto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientJoue implements Runnable{
    private Client joueur1;
    private Client joueur2;

    private BufferedReader readerDuJoueur1;
    private PrintWriter WriterDuJoueur1;

    private BufferedReader readerDuJoueur2;
    private PrintWriter WriterDuJoueur2;

    private Jeu jeu;


    public ClientJoue(Client joueur1 ,Client joueur2 ){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

        this.readerDuJoueur1 = this.joueur1.getReader();
        this.WriterDuJoueur1 = this.joueur1.getWriter();

        this.readerDuJoueur2 = this.joueur2.getReader();
        this.WriterDuJoueur2 = this.joueur2.getWriter();

        this.jeu = new Jeu(joueur1,joueur2);


    }

    public void run(){
        this.WriterDuJoueur1.println("votre party avec "+ this.joueur2.getNom() + " vas commencer !");
        this.WriterDuJoueur2.println("votre parti avec "+ this.joueur1.getNom() + " vas commencer !");
        this.jeu.jouer();


    }

}