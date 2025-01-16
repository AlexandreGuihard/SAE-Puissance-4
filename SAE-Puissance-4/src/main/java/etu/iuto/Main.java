package main.java.etu.iuto;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Jeu j=new Jeu(new Client("Joueur 1"), new Client("Joueur 2"));
        j.jouer();
    }
}