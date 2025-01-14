package main.java.etu.iuto;

public class Main {
    public static void main(String[] args) {
        Jeu j=new Jeu(new Client(), new Client());
        j.jouer();
    }
}