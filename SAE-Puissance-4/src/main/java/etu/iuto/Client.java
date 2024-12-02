package etu.iuto;

public class Client {

    // Attributs privés
    private int score;
    private String nom;
    private int nbVictoire;
    private int nbPartiesJouees;
    private int nbDefaites;

    // Constructeur
    public Client(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbVictoire = 0;
        this.nbPartiesJouees = 0;
        this.nbDefaites = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return this.nom; 
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void selectAdversaire(Client joueur) {
        // Permet de sélectionner un adversaire
    }

    public void quitter() {
        // Permet au joueur de quitter la partie
    }

    public void historiqueParties(Client joueur) {
        // Affiche l'historique des parties du joueur
    }

    public int getPartiesNuls(){
        return nbPartiesJouees -(nbDefaites + nbVictoire);
    }

    @Override
    public String toString(){
        return "Joueur " + this.nom + "\nScore: " + this.score + "\nNombre de parties jouées: " + this.nbPartiesJouees + "  Victoires: " + this.nbVictoire + "  Défaites: " + this.nbDefaites + "   Nuls: " + this.getPartiesNuls(); 
    }
}
