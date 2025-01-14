package etu.iuto;

public class Plateau {
    private final int NBCOLONNES=7;
    private final int NBLIGNES=6;
    private String[][] plateau;

    /**
     * Constructeur de la classe Plateau
     */
    public Plateau(){
        this.plateau=new String[NBLIGNES][NBCOLONNES];
    }

    /**
     * Initialise le plateau
     */
    public void initPlateau(){
        for(int i=0;i<NBLIGNES;i++){
            for(int j=0;j<NBCOLONNES;j++){
                plateau[i][j]=" . ";
            }
        }
    }

    /**
     * Vide le plateau de tous les caractères présents
     */
    public void clearPlateau(){
        this.initPlateau();
    }


    // Getters des attributs
    /**
     * Getter pour avoir le nombre de colonnes du plateau
     * @return le nombre de colonnes du plateau
     */
    public int getNbColonnes(){
        return NBCOLONNES;
    }

    /**
     * Getter pour avoir le nombre de lignes du plateau
     * @return le nombre de lignes du plateau
     */
    public int getNbLignes(){
        return NBLIGNES;
    }

    public String[][] getPlateau(){
        return plateau;
    }

    /**
     * Place le pion dans la colonne choisi
     * @param colonne la colonne où sera posé le pion
     * @param pion le caractère pour représenter le pion du joueur
     * @return true si le pion a été placé sinon false
     */
    public boolean ajouterPion(int colonne, String pion){
        int x=NBLIGNES-1;
        while (x>=0) {
            if (plateau[x][colonne].equals(" . ")) {
                plateau[x][colonne]=" " + pion + " ";
                return true;
            }
            --x;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Plateau Puissance 4: NbColonnes="+NBCOLONNES+", NbLignes="+NBLIGNES;
    }
}
