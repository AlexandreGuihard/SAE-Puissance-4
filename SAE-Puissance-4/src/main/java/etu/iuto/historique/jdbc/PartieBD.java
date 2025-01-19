public class PartieBD{
    private int idPartie;
    private ClientBD joueurRouge;
    private ClientBD joueurJaune;
    private ClientBD gagnant;

    /**
     * Constructeur de la classe pour une partie non présente dans la bd
     * @param joueurRouge le joueur avec les pions O
     * @param joueurJaune le joueur avec les pions X
     * @param gagnant le gagnant | null si partie nul
     */
    public PartieBD(ClientBD joueurRouge, ClientBD joueurJaune, ClientBD gagnant){
        this.joueurRouge=joueurRouge;
        this.joueurJaune=joueurJaune;
        this.gagnant=gagnant;
    }

    /**
     * Constructeur de la classe pour une partie déjà présente dans la bd
     * @param idPartie l'id de la partie
     * @param joueurRouge le joueur avec les pions O
     * @param joueurJaune le joueur avec les pions X
     * @param gagnant le gagnant | null si partie nul
     */
    public PartieBD(int idPartie, ClientBD joueurRouge, ClientBD joueurJaune, ClientBD gagnant){
        this.idPartie=idPartie;
        this.joueurRouge=joueurRouge;
        this.joueurJaune=joueurJaune;
        this.gagnant=gagnant;
    }

    /**
     * Getter de l'id
     * @return l'id de la partie
     */
    public int getId(){
        return idPartie;
    }

    /**
     * Getter du joueur rouge/O
     * @return le joueur rouge/O
     */
    public ClientBD getJoueurRouge(){
        return joueurRouge;
    }

    /**
     * Getter du joueur jaune/X
     * @return le joueur jaune/X
     */
    public ClientBD getJoueurJaune(){
        return joueurJaune;
    }

    /**
     * Getter du gagnant
     * @return le gagnant | null si partie nul
     */
    public ClientBD getGagnant(){
        return gagnant;
    }

    /**
     * Setter du joueur rouge/O
     * @param joueurRouge le joueur rouge/O
     */
    public void setJoueurRouge(ClientBD joueurRouge){
        this.joueurRouge=joueurRouge;
    }

    /**
     * Setter du joueur jaune/X
     * @param joueurJaune le joueur jaune/X
     */
    public void setJoueurJaune(ClientBD joueurJaune){
        this.joueurJaune=joueurJaune;
    }

    /**
     * Setter du gagnant
     * @param gagnant le gagnant
     */
    public void setGagnant(ClientBD gagnant){
        this.gagnant=gagnant;
    }
}