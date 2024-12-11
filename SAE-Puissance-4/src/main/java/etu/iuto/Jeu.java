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

    /**
     * @return true si il y a 4 pions de la même couleur consécutifs sur la même ligne sinon false 
     */
    public boolean ckeckHorizontal(){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int i=0;i<plateau.getNbColonnes();i++){
            for(int j=0;j<plateau.getNbLignes();j++){
                String pion=plateau.getPlateau()[i][j];
                if(!(pion.equals(" . ")) && pion.equals(suitePion)){
                    suitePionsIdentiques++;
                }
                else if(suitePionsIdentiques==0 && !(pion.equals(" . "))){
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
     * @param colonne la colonne où le dernier pion a été posé
     * @return true si il y a 4 pions de la même couleur consécutifs sur la même colonne sinon false
     */
    public boolean checkVertical(int colonne){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int j=0;j<plateau.getNbLignes();j++){
            String pion=plateau.getPlateau()[colonne][j];
            if(!(pion.equals(" . ") && pion.equals(suitePion))){
                suitePionsIdentiques++;
            }
            else if(suitePionsIdentiques==0 && !(pion.equals(" . "))){
                suitePion=pion;
                suitePionsIdentiques++;
            }
            else{
                suitePion="";
                suitePionsIdentiques=0;
            }
            if(suitePionsIdentiques==4){
                return true;
            }
        }
        return false;
    }

    /**
     * @return true si il y a 4 pions de la même couleur consécutifs sur une diagonale sinon false
     */
    public boolean checkDiagonale(){
        String suitePion="";
        int suitePionsIdentiques=0;
        for(int i=0;i<plateau.getNbColonnes();i++){
            for(int j=0;j<plateau.getNbLignes();j++){
                String suite = "";
                if (i+4 <= plateau.getNbColonnes() && j + 4 <= plateau.getNbLignes()){
                    for (int k = 0; k < 4; k++){
                        suite += plateau.getPlateau()[i+k][j+k];
                    }
                    // Verifier si la suite est gagnante
                    char couleur = ' ';
                    for (char c : suite.toCharArray()){

                    }
                }
                if (i+4 <= plateau.getNbColonnes() && j - 3 >= 0){
                    for (int k = 0; k < 4; k++){
                        suite += plateau.getPlateau()[i+k][j-k];
                    }
                }
                if (i-3 >= 0 && j + 4 <= plateau.getNbLignes()){
                    for (int k = 0; k < 4; k++){
                        suite += plateau.getPlateau()[i-k][j+k];
                    }
                }
                if (i-3 >= 0 && j-3 >= 0){
                    for (int k = 0; k < 4; k++){
                        suite += plateau.getPlateau()[i-k][j-k];
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
