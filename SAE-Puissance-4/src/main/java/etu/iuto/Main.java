package main.java.etu.iuto;

public class Main {
    public static void main(String[] args) {
        Jeu j=new Jeu(new Client("Joueur 1"), new Client("Joueur 2"));
        j.jouer();
    }
}