package etu.iuto;

public class Client {
    private int score;
    private String nom;
    private int nbVictoires;
    private int nbPartiesJouees;
    private int nbDefaites;

    /**
     * @param nom le nom du client
     * Constructeur de la classe
     */
    public Client(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbVictoires = 0;
        this.nbPartiesJouees = 0;
        this.nbDefaites = 0;
    }

    /**
     * @param joueur l'adversaire
     * Choisis l'adversaire parmi les joueurs en attente
     */
    public void selectAdversaire(Client joueur) {
        // Permet de sélectionner un adversaire
    }

    /**
     * Quitte la partie
     */
    public void quitter() {
        // Permet au joueur de quitter la partie
    }

    /**
     * @param joueur un joueur
     * Affiche l'historique des parties du joueur
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
     * @return le score du joueur
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @param score le nouveau score du joueur
     * Change le score du joueur
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return le nom du joueur
     */
    public String getNom() {
        return this.nom; 
    }

    /**
     * @param nom le nouveau nom du joueur
     * Change le nom du joueur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return le nombre de victoires du joueur
     */
    public int getNbVictoires(){
        return nbVictoires;
    }

    /**
     * @param nbVictoires le nouveau nombre de victoires du joueur
     * Change le nombre de victoires du joueur
     */
    public void setNbVictoires(int nbVictoires){
        this.nbVictoires=nbVictoires;
    }

    /**
     * @return le nombre de parties jouées par le joueur
     */
    public int getNbPartiesJouees(){
        return nbPartiesJouees;
    }


    /**
     * @param nbPartiesJouees le nouveau nombre de parties jouées
     * Change le nombre de parties jouées par le joueur
     */
    public void setNbPartiesJouees(int nbPartiesJouees){
        this.nbPartiesJouees=nbPartiesJouees;
    }

    /**
     * @return le nombre de défaites du joueur
     */
    public int getNbDefaites(){
        return nbDefaites;
    }

    /**
     * @param nbDefaites le nouveau nombre de défaites du joueur
     * Change le nombre de défaites du joueur
     */
    public void setNbDefaites(int nbDefaites){
        this.nbDefaites=nbDefaites;
    }

    /**
     * @return les parties nuls du joueurs
     */
    public int getPartiesNuls(){
        return nbPartiesJouees -(nbDefaites + nbVictoires);
    }
}
