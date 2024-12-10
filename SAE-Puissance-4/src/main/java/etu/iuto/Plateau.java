package etu.iuto;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final int NBCOLONNES=7;
    private final int NBLIGNES=6;
    private String[][] plateau;

    /**
     * Constructeur de la classe
     */
    public Plateau(){
        this.plateau=new String[NBCOLONNES][NBLIGNES];
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

    /**
     * @param x le numéro de colonne
     * @param pion le caractère pour représenter le pion du joueur
     * @return true si le pion a été placé sinon false
     * Place le pion dans la colonne x
     */
    public boolean ajouterPion(int colonne, String pion){
        for(int j=NBLIGNES-1;j<=0;--j){
            if(!plateau[j][colonne].equals(" . ")){
                plateau[j][colonne]=" "+pion+" ";
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "Plateau Puissance 4: NbColonnes="+NBCOLONNES+", NbLignes="+NBLIGNES;
    }

    // Getters des attributs

    /**
     * @return le nombre de colonnes du plateau
     */
    public int getNbColonnes(){
        return NBCOLONNES;
    }

    /**
     * @return le nombre de lignes du plateau
     */
    public int getNbLignes(){
        return NBLIGNES;
    }

    public String[][] getPlateau(){
        return plateau;
    }
}
