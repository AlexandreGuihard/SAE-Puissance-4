package etu.iuto.historique;

public class ClientBD{
    private int idClient;
    private String nom;
    private int nbVictoires;
    private int nbDefaites;
    private int nbNuls;

    /**
     * Constructeur utilisé pour un nouveau client
     * @param nom le nom du client
     */
    public ClientBD(String nom){
        this.nom=nom;
        this.nbVictoires=0;
        this.nbDefaites=0;
        this.nbNuls=0;
    }

    /**
     * Constructeur utilisé pour un client déjà présent dans la bd
     * @param idClient l'id du client
     * @param nom le nom du client
     * @param nbVictoires le nombre de victoires du client
     * @param nbDefaites le nombre de défaites du client
     * @param nbNuls le nombre de parties nuls du client
     */
    public ClientBD(int idClient, String nom, int nbVictoires, int nbDefaites, int nbNuls){
        
        this.idClient=idClient;
        this.nom=nom;
        this.nbVictoires=nbVictoires;
        this.nbDefaites=nbDefaites;
        this.nbNuls=nbNuls;
    }

    /**
     * Getter de l'id
     * @return l'id du client
     */
    public int getId(){
        return idClient;
    }

    /**
     * Getter du nom
     * @return le nom du client
     */
    public String getNom(){
        return nom;
    }

    /**
     * Getter du nombre de victoires
     * @return le nombre de victoires du client
     */
    public int getNbVictoires(){
        return nbVictoires;
    }

    /**
     * Getter du nombre de défaites
     * @return le nombre de défaites du client
     */
    public int getNbDefaites(){
        return nbDefaites;
    }

    /**
     * Getter du nombre de parties nuls
     * @return le nombre de parties nuls du client
     */
    public int getNbNuls(){
        return nbNuls;
    }

    /**
     * Setter du nom
     * @param nom le nom du client
     */
    public void setNom(String nom){
        this.nom=nom;
    }

    /**
     * Setter du nombre de victoires
     * @param nbVictoires le nombre de victoires du client
     */
    public void setNbVictoires(int nbVictoires){
        this.nbVictoires=nbVictoires;
    }

    /**
     * Setter du nombre de défaites
     * @param nbDefaites le nombre de défaites du client
     */
    public void setNbDefaites(int nbDefaites){
        this.nbDefaites=nbDefaites;
    }

    /**
     * Setter du nombre de parties nuls
     * @param nbNuls le nombre de parties nuls du client
     */
    public void setNbNuls(int nbNuls){
        this.nbNuls=nbNuls;
    }

    /**
     * Ajoute une victoire au client
     */
    public void addVictoire(){
        nbVictoires++;
    }

    /**
     * Ajoute une défaite au client
     */
    public void addDefaite(){
        nbDefaites++;
    }

    /**
     * Ajoute une partie nul au client
     */
    public void addNul(){
        nbNuls++;
    }
}