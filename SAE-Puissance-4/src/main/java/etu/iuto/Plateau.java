package etu.iuto;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private final int NBCOLONNES=7;
    private final int NBLIGNES=6;
    private List<List<Character>> plateau;

    /**
     * Constructeur de la classe
     */
    public Plateau(){
        this.initPlateau();
    }

    /**
     * Initialise le plateau
     */
    private void initPlateau(){
        this.plateau=new ArrayList<>(NBLIGNES);
        for(int i=0;i<NBLIGNES;i++){
            this.plateau.add(new ArrayList<>(NBCOLONNES));
        }
    }

    /**
     * Clear le plateau de tous les caractères présents
     */
    public void clearPlateau(){
        this.initPlateau();
    }
}
