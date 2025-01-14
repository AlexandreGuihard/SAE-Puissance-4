package main.java.etu.iuto;

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
    public void affichePlateau(){
        String[][] lePlateau=plateau.getPlateau();
        for(int i=0;i<plateau.getNbLignes();i++){
            for (int j=0;j<plateau.getNbColonnes();j++){
                System.out.print(lePlateau[i][j]);
            }
            System.out.println();
        }
        for (int numcol = 0; numcol < lePlateau[0].length; numcol++){
            System.out.print(" " + numcol + " ");
        }
        System.out.println();
    }

    /**
     * Méthode pour lancer le jeu
     */
    public void jouer(){
        int tour = 1;
        int choix;
        System.out.println("------- [Tour n°" + tour + "] -------");
        affichePlateau();
        do {
            // Joueur J
            // Vérification si le joueur peut poser le pion et redemande si
            do {
                // Ici demander le choix de l'utilisateur
                System.out.println("Au tour du Joueur " + joueurR.getNom() + " (O)");
                choix = joueurJ.askColonne();
            } while (!poserPion(choix, joueurJ));
            System.out.println("------- [Tour n°" + tour + "] -------");
            affichePlateau();
            // Si victoire du joueur J
            if (detecterVictoire(choix)) {
                System.out.println("Le joueur " + joueurR.getNom() + " a gagné");
                break;
            }

            // Joueur R
            // Vérification si le joueur peut poser le pion et redemande si
            do {
                // Ici demander le choix de l'utilisateur
                System.out.println("Au tour du Joueur " + joueurR.getNom() + " (O)");
                choix = joueurR.askColonne();
            } while (!poserPion(choix, joueurR));
            tour++;
            System.out.println("------- [Tour n°" + tour + "] -------");
            affichePlateau();
            // Si victoire du joueur R
            if (detecterVictoire(choix)) {
                System.out.println("Le joueur " + joueurR.getNom() + " a gagné");
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
