package etu.iuto;

public class Client {
    private int score;
    private String nom;
    private int nbVictoire;
    private int nbPartiesJouees;
    private int nbDefaites;

    /**
     * @param nom le nom du client
     * Constructeur de la classe
     */
    public Client(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbVictoire = 0;
        this.nbPartiesJouees = 0;
        this.nbDefaites = 0;
    }

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

    /**
     * @return les parties nuls du joueurs
     */
    public int getPartiesNuls(){
        return nbPartiesJouees -(nbDefaites + nbVictoire);
    }

    @Override
    public String toString(){
        return "Joueur " + this.nom + "\nScore: " + this.score + "\nNombre de parties jouées: " + this.nbPartiesJouees + "  Victoires: " + this.nbVictoire + "  Défaites: " + this.nbDefaites + "   Nuls: " + this.getPartiesNuls(); 
    }
}
