package etu.iuto;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Player {
    private String name;
    private String data;
 
    private BufferedReader readerDuJoueur;
    private PrintWriter WriterDuJoueur;

    public Player(String name,BufferedReader readerDuJoueur,PrintWriter WriterDuJoueur) {
        this.name = name;
        this.data = "";
        this.WriterDuJoueur = WriterDuJoueur;
        this.readerDuJoueur = readerDuJoueur;
    }

    public String getName() {
        return name;
    }

    public PrintWriter getWriterDuJoueur() {
        return WriterDuJoueur;
    }

    public BufferedReader getReaderDuJoueur() {
        return readerDuJoueur;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "le joueur " + name + " a " + data;
    }
}