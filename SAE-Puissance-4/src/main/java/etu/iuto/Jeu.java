package main.java.etu.iuto;

import java.io.IOException;
import java.io.PrintWriter;

public class Jeu {
    private Plateau plateau;
    private Client joueurJ;
    private Client joueurR;
    private boolean gagne;

    /**
     * Constructeur de la classe Jeu qui créer le plateau et l'initialise
     * @param joueurJ le joueur avec les pions jaunes
     * @param joueurR le joueur avec les pions rouges
     * Constructeur de la classe
     */
    public Jeu(Client joueurJ, Client joueurR){
        this.joueurJ=joueurJ;
        this.joueurR=joueurR;
        this.plateau=new Plateau();
        this.initPlateau();
        this.gagne=false;
    }

    /**
     * Initialise le plateau à un plateau vide
     */
    public void initPlateau(){
        plateau.initPlateau();
    }

    /**
     * Affiche le plateau après que chaque pion soit posé
     */
    public void affichePlateau(int tour){
        PrintWriter writerJoueurJ = this.joueurJ.getWriter();
        PrintWriter writerJoueurR = this.joueurR.getWriter();
        String[][] lePlateau=plateau.getPlateau();
        writerJoueurJ.println("------- [Tour n°" + tour + "] -------");
        writerJoueurJ.flush();
        writerJoueurR.println("------- [Tour n°" + tour + "] -------");
        writerJoueurR.flush();
        for(int i=0;i<plateau.getNbLignes();i++){
            for (int j=0;j<plateau.getNbColonnes();j++){
                writerJoueurJ.print(lePlateau[i][j]);
                writerJoueurJ.flush();
                writerJoueurR.print(lePlateau[i][j]);
                writerJoueurR.flush();
            }
            writerJoueurJ.println();
            writerJoueurJ.flush();
            writerJoueurR.println();
            writerJoueurR.flush();
        }
        writerJoueurJ.println("---------------------");
        writerJoueurJ.flush();
        writerJoueurR.println("---------------------");
        writerJoueurR.flush();
        for (int numcol = 0; numcol < lePlateau[0].length; numcol++){
            writerJoueurJ.print(" " + numcol + " ");
            writerJoueurJ.flush();
            writerJoueurR.print(" " + numcol + " ");
            writerJoueurR.flush();
        }
        writerJoueurJ.println();
        writerJoueurJ.flush();
        writerJoueurR.println();
        writerJoueurR.flush();
    }

    /**
     * Méthode pour lancer le jeu
     */
    public void jouer(){
        int tour = 1;
        int choix;
        PrintWriter writerJoueurJ = this.joueurJ.getWriter();
        PrintWriter writerJoueurR = this.joueurR.getWriter();
        affichePlateau(tour);
        do {
            // Joueur J
            // Vérification si le joueur peut poser le pion et redemande si
            do {
                // Ici demander le choix de l'utilisateur
                writerJoueurJ.println("Au tour du Joueur " + joueurJ.getNom() + " (O)");
                writerJoueurJ.flush();
                writerJoueurR.println("Au tour du Joueur " + joueurJ.getNom() + " (O)");
                writerJoueurR.flush();
                try {
                    choix = joueurJ.askColonne();
                    writerJoueurJ.println("Le joueur " + this.joueurJ.getNom() + " à choisi la colonne " + choix);
                    writerJoueurJ.flush();
                    writerJoueurR.println("Le joueur " + this.joueurJ.getNom() + " à choisi la colonne " + choix);
                    writerJoueurR.flush();
                } catch (IOException e) {
                    writerJoueurR.println("Problème de connexion avec le joueur " + joueurJ.getNom());
                    writerJoueurR.flush();
                    return;
                }
                affichePlateau(tour);
            } while (!poserPion(choix, joueurJ));
            affichePlateau(tour);
            // Si victoire du joueur J
            if (detecterVictoire(choix)) {
                writerJoueurJ.println("Le joueur " + joueurJ.getNom() + " a gagné");
                writerJoueurJ.flush();
                writerJoueurR.println("Le joueur " + joueurJ.getNom() + " a gagné");
                writerJoueurR.flush();
                break;
            }

            // Joueur R
            // Vérification si le joueur peut poser le pion et redemande si
            do {
                // Ici demander le choix de l'utilisateur
                writerJoueurJ.println("Au tour du Joueur " + joueurR.getNom() + " (O)");
                writerJoueurJ.flush();
                writerJoueurR.println("Au tour du Joueur " + joueurR.getNom() + " (O)");
                writerJoueurR.flush();
                try {
                    choix = joueurR.askColonne();
                    writerJoueurJ.println("Le joueur " + this.joueurR.getNom() + " à choisi la colonne " + choix);
                    writerJoueurJ.flush();
                    writerJoueurR.println("Le joueur " + this.joueurR.getNom() + " à choisi la colonne " + choix);
                    writerJoueurR.flush();
                } catch (IOException e) {
                    writerJoueurJ.println("Problème de connexion avec le joueur " + joueurR.getNom());
                    writerJoueurJ.flush();
                    return;
                }
                affichePlateau(tour);
            } while (!poserPion(choix, joueurR));
            affichePlateau(tour);
            tour++;
            // Si victoire du joueur R
            if (detecterVictoire(choix)) {
                writerJoueurJ.println("Le joueur " + joueurR.getNom() + " a gagné");
                writerJoueurJ.flush();
                writerJoueurR.println("Le joueur " + joueurR.getNom() + " a gagné");
                writerJoueurR.flush();
                break;
            }
        } while (!gagne);
    }

    /**
     * Pose un pion sur le plateau
     * @param colonne la colonne où poser le pion
     * @param joueur le joueur qui pose le pion
     * @return true si le pion à été posé sinon false
     */
    public boolean poserPion(int colonne, Client joueur){
        String pion;
        if(joueur.equals(joueurR)){
            pion="O";
        }
        else{
            pion="X";
        }
        return plateau.ajouterPion(colonne, pion);
    }

    /**
     * Messages affichés en fin de partie
     */
    public void finPartie(){}

    /**
     * Vérifie s'il y a une suite de 4 pions identique horizontalement
     * @return true si il y a 4 pions de la même couleur consécutifs sur la même ligne sinon false 
     */
    public boolean ckeckHorizontal(){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int i=0;i<plateau.getNbLignes();i++){
            for(int j=0;j<plateau.getNbColonnes();j++){
                String pion=plateau.getPlateau()[i][j];
                if(!(pion.equals(" . ")) && pion.equals(suitePion)){
                    suitePionsIdentiques++;
                }
                else if (!(pion.equals(suitePion))) {
                    suitePion=pion;
                    suitePionsIdentiques = 1;
                }
                if(suitePionsIdentiques==4){
                    return true;
                }
            }
            suitePion="";
            suitePionsIdentiques=0;
        }
        return false;
    }

    /**
     * Vérifie s'il y a une suite de 4 pions identique verticalement
     * @param colonne la colonne dans laquelle le dernier pion a été posé
     * @return true s'il y a 4 pions de la même couleur consécutifs sur la même colonne sinon false
     */
    public boolean checkVertical(int colonne){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int j=plateau.getNbLignes()-1;j>=0;j--) {
            String pion=plateau.getPlateau()[j][colonne];
            // Si la case est vide
            if (pion.equals(" . ")){
                // il ne peut pas y avoir de suite
                return false;
            }
            // Si c'est une autre couleur revenir à zero
            else if (!(pion.equals(suitePion))){
                suitePion = pion;
                suitePionsIdentiques = 1;
            // Sinon le pion est le même donc on incrémente la suite de pion identique
            } else {
                suitePionsIdentiques++;
            }
            // Si on a une suite de 4 pions
            if(suitePionsIdentiques==4){
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si il y a une suite de 4 pions dans les diagnales bas droit et haut droit
     * @return true s'il y a 4 pions de la même couleur consécutifs sur une diagonale sinon false
     */
    public boolean checkDiagonale() {
        int nbLignes = plateau.getNbLignes();
        int nbColonnes = plateau.getNbColonnes();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                String pion = plateau.getPlateau()[i][j];

                // Si la case est vide
                if (!(pion.equals(" . "))) {
                    // Bas droit
                    if (i + 3 < nbLignes && j + 3 < nbColonnes) {
                        if (pion.equals(plateau.getPlateau()[i + 1][j + 1]) &&
                                pion.equals(plateau.getPlateau()[i + 2][j + 2]) &&
                                pion.equals(plateau.getPlateau()[i + 3][j + 3])) {
                            return true;
                        }
                    }

                    // Haut droite
                    if (i - 3 >= 0 && j + 3 < nbColonnes) {
                        if (pion.equals(plateau.getPlateau()[i - 1][j + 1]) &&
                                pion.equals(plateau.getPlateau()[i - 2][j + 2]) &&
                                pion.equals(plateau.getPlateau()[i - 3][j + 3])) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    public boolean detecterVictoire(int colonne){
        return ckeckHorizontal() || checkVertical(colonne) || checkDiagonale();
    }
    
}
