package etu.iuto;

import java.util.ArrayList;
import java.util.List;

public class Joueurs {
    private List<Client> joueurs;

    /**
     * Constructeur de la classe
     * Initialise la liste des joueurs à une nouvelle ArrayList
     */
    public Joueurs(){
        this.joueurs=new ArrayList<>();
    }

    /** 
     * Ajoute un joueur à la liste des joueurs
     */
    public void ajouterJoueur(Client joueur){
        this.joueurs.add(joueur);
    }
}
