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
     * Vide le plateau de tous les caractères présents
     */
    public void clearPlateau(){
        this.initPlateau();
    }

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

    @Override
    public String toString(){
        return "Plateau Puissance 4: NbColonnes="+NBCOLONNES+", NbLignes="+NBLIGNES;
    }
}
