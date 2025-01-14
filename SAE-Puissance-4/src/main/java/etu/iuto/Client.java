package etu.iuto;

import java.util.Scanner;

public class Client {
    private int score;
    private String nom;
    private int nbVictoires;
    private int nbPartiesJouees;
    private int nbDefaites;

    /**
     * Constructeur de la classe
     * @param nom le nom du client
     */
    public Client(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbVictoires = 0;
        this.nbPartiesJouees = 0;
        this.nbDefaites = 0;
    }

    /**
     * Choisis l'adversaire parmi les joueurs en attente
     * @param joueur l'adversaire
     */
    public void selectAdversaire(Client joueur) {
        // Permet de sélectionner un adversaire
    }

    /**
     * Demande au joueur de placer son pion dans une colonne
     * @return le choix de colonne du joueur
     */
    public int askColonne() {
        boolean estCorrecte;
        int choix = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une colonne : ");
        do{
            estCorrecte = true;
            try {
                choix = scanner.nextInt();
            } catch (Exception e) {
                estCorrecte = false;
                System.out.println("Votre choix n'est pas un nombre valide (entre 0 et 6)");
            }
            if (choix < -1 || choix > 7) {
                estCorrecte = false;
                System.out.println("Votre choix n'est pas un nombre valide (entre 0 et 6)");
            }
        } while (!estCorrecte);

        System.out.println("Le joueur " + nom + " à choisi la colonne " + choix);
        return choix;
    }

    /**
     * Quitte la partie
     */
    public void quitter() {
        // Permet au joueur de quitter la partie
    }

    /**
     * Affiche l'historique des parties du joueur
     * @param joueur un joueur
     */
    public static void historiqueParties(Client joueur) {
        // Affiche l'historique des parties du joueur
    }

    @Override
    public String toString(){
        return "Joueur " + this.nom + "\nScore: " + this.score + "\nNombre de parties jouées: " + this.nbPartiesJouees + "  Victoires: " + this.nbVictoires + "  Défaites: " + this.nbDefaites + "   Nuls: " + this.getPartiesNuls(); 
    }

    // Getters et Setters

    /**
     * Getter pour avoir le score du joueur
     * @return le score du joueur
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Change le score du joueur
     * @param score le nouveau score du joueur
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter pour avoir le nom du joueur
     * @return le nom du joueur
     */
    public String getNom() {
        return this.nom; 
    }

    /**
     * Change le nom du joueur
     * @param nom le nouveau nom du joueur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour avoir le nombre de victoire du joueur
     * @return le nombre de victoires du joueur
     */
    public int getNbVictoires(){
        return nbVictoires;
    }

    /**
     * Change le nombre de victoires du joueur
     * @param nbVictoires le nouveau nombre de victoires du joueur
     */
    public void setNbVictoires(int nbVictoires){
        this.nbVictoires=nbVictoires;
    }

    /**
     * Getter pour avoir le nombre de parties jouées par le joueur
     * @return le nombre de parties jouées par le joueur
     */
    public int getNbPartiesJouees(){
        return nbPartiesJouees;
    }


    /**
     * Change le nombre de parties jouées par le joueur
     * @param nbPartiesJouees le nouveau nombre de parties jouées
     */
    public void setNbPartiesJouees(int nbPartiesJouees){
        this.nbPartiesJouees=nbPartiesJouees;
    }

    /**
     * Getter pour avoir le nombre de défaites du joueur
     * @return le nombre de défaites du joueur
     */
    public int getNbDefaites(){
        return nbDefaites;
    }

    /**
     * Change le nombre de défaites du joueur
     * @param nbDefaites le nouveau nombre de défaites du joueur
     */
    public void setNbDefaites(int nbDefaites){
        this.nbDefaites=nbDefaites;
    }

    /**
     * Getter pour avoir le nombre de parties nul du joueur
     * @return les parties nuls du joueurs
     */
    public int getPartiesNuls(){
        return nbPartiesJouees -(nbDefaites + nbVictoires);
    }
}
