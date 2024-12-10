package etu.iuto;

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
            System.out.println(lePlateau[i]);
        }
    }

    public void jouer(){
        affichePlateau();
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

    public boolean ckeckHorizontal(){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int i=0;i<plateau.getNbColonnes();i++){
            for(int j=0;j<plateau.getNbLignes();j++){
                if(!(plateau.getPlateau()[i][j].equals(" . ")) && plateau.getPlateau()[i][j].equals(suitePion)){
                    suitePionsIdentiques++;
                }
                else if(suitePionsIdentiques==0 && plateau.getPlateau()[i][j].equals(" . ")){
                    suitePion=plateau.getPlateau()[i][j];
                    suitePionsIdentiques++;
                }
                if(suitePionsIdentiques==4){
                    return true;
                }
            }
            suitePion="";
            suitePionsIdentiques=0;
        }
    }

    public boolean detecterVictoire(){
        return true;
    }
    
}
