package etu.iuto;

import java.util.Arrays;

public class Jeu {
    private Plateau plateau;
    private Client joueurJ;
    private Client joueurR;
    private boolean gagne;

    /**
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
    public void affichePlateau(){
        String[][] lePlateau=plateau.getPlateau();
        for(int i=0;i<plateau.getNbLignes();i++){
            for (int j=0;j<plateau.getNbColonnes();j++){
                System.out.print(lePlateau[i][j]);
            }
            System.out.println();
        }
    }

    public void jouer(){
        while (!gagne){
            affichePlateau();
            poserPion(0, joueurR);
            poserPion(1, joueurJ);
            poserPion(1, joueurR);
            poserPion(2, joueurR);
            poserPion(2, joueurJ);
            poserPion(2, joueurR);
            poserPion(3, joueurJ);
            poserPion(3, joueurR);
            poserPion(3, joueurJ);
            poserPion(3, joueurR);
            gagne = detecterVictoire(0);
        }
    }

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

    public void finPartie(){}

    /**
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
                else if(suitePionsIdentiques==0 && (pion.equals(" . "))){
                    suitePion=pion;
                    suitePionsIdentiques++;
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
