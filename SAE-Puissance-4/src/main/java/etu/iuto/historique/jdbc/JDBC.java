package etu.iuto.historique;
import java.sql.*;

public class JDBC {
    private ConnexionBD connexion;

    /**
     * Constructeur: crée l'instance de la connexion sql
     * @param nomServeur le nom du serveur sql
     * @param database la base de données utilisée
     * @param login le login pour se connecter
     * @param password le mot de passe pour se connecter
     */
    public JDBC(String nomServeur, String database, String login, String password){
        this.connexion=new ConnexionBD(nomServeur, database, login, password);
    }

    /**
     * Getter d'un joueur dans la bd à partir de son id
     * @param id l'id du joueur
     * @return le joueur | null si pas de joueur avec cet id
     */
    public ClientBD getJoueurFromId(int id) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet joueur=st.executeQuery("select * from JOUEUR where idJoueur="+id);
        if(joueur.next()){
            String nomJoueur=joueur.getString(2);
            int nbVictoires=joueur.getInt(3);
            int nbDefaites=joueur.getInt(4);
            int nbNuls=joueur.getInt(5);
            ClientBD joueurJava=new ClientBD(id, nomJoueur, nbVictoires, nbDefaites, nbNuls);
            joueurJava.setNbVictoires(nbVictoires);
            joueurJava.setNbDefaites(nbDefaites);
            joueurJava.setNbNuls(nbNuls);
            return joueurJava;
        }
        return null;
    }

    /**
     * Getter d'une partie dans la bd à partir de son id
     * @param id l'id de la partie
     * @return la partie | null si pas de partie avec cet id
     */
    public PartieBD getPartieFromId(int id) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet partie=st.executeQuery("select * from PARTIE where idPartie="+id);
        if(partie.next()){
            int idJoueurRouge=partie.getInt(2);
            int idJoueurJaune=partie.getInt(3);
            int idGagnant=partie.getInt(4);
            ClientBD joueurRouge=getJoueurFromId(idJoueurRouge);
            ClientBD joueurJaune=getJoueurFromId(idJoueurJaune);
            ClientBD gagnant=null; // Reste à null si partie nul
            switch(idGagnant){
                case idJoueurRouge:
                    gagnant=joueurRouge;
                    joueurRouge.addVictoire();
                    joueurJaune.addDefaite();
                    break;
                case idJoueurJaune:
                    gagnant=joueurJaune;
                    joueurRouge.addDefaite();
                    joueurJaune.addVictoire();
                    break;
                default:
                    joueurRouge.addNul();
                    joueurJaune.addNul();      
            }
            PartieBD partieJava=new PartieBD(id, joueurRouge, joueurJaune, gagnant);
            return partieJava;
        }
        return null;
    }

    /**
     * Méthode pour obtenir le prochain id disponible pour un joueur dans la bd
     * @return le prochain id disponible
     */
    public Integer getAvailableIdForClient() throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet availableId=st.executeQuery("select getAvailableIdForJoueur()");
        if(availableId.next()){
            return availableId.getInt(1);
        }
        return null;
    }

    /**
     * Méthode pour obtenir le prochain id disponible pour une partie dans la bd
     * @return le prochain id disponible
     */
    public Integer getAvailableIdForPartie() throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet availableId=st.executeQuery("select getAvailableIdForPartie()");
        if(availableId.next()){
            return availableId.getInt(1);
        }
        return null;
    }

    /**
     * Insère un nouveau client dans la bd
     * @param joueur le nouveau client
     */
    public void insertClient(ClientBD joueur) throws SQLException{
        PreparedStatement st=connexion.prepareStatement("insert into JOUEUR values (?, ?, ?, ?, ?)");
        st.setInt(1, getAvailableIdForClient());
        st.setString(2, joueur.getNom());
        st.setInt(3, joueur.getNbVictoires());
        st.setInt(4, joueur.getNbDefaites());
        st.setInt(5, joueur.getNbNuls());
        st.executeUpdate();
    }

    /**
     * Insère une nouvelle partie dans la bd
     * @param partie la partie
     */
    public void insertPartie(PartieBD partie) throws SQLException{
        PreparedStatement st=connexion.prepareStatement("insert into PARTIE values (?, ?, ?, ?)");
        st.setInt(1, getAvailableIdForPartie());
        st.setInt(2, partie.getJoueurRouge().getId());
        st.setInt(3, partie.getJoueurJaune().getId());
        st.setInt(4, partie.getGagnant().getId());
        st.executeUpdate();
    }
}
