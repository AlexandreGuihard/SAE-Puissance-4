package etu.iuto.historique;
import java.sql.*;

public class JDBC {
    private ConnexionBD connexion;

    public JDBC(){
        this.connexion=new ConnexionBD("DBguihard", "guihard", "guihard");
    }

    public Client getJoueurFromId(int id) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet joueur=st.executeQuery("select * from JOUEUR where id="+id);
        if(joueur.next()){
            String nomJoueur=joueur.getString(2);
            int nbVictoires=joueur.getInt(3);
            int nbDefaites=joueur.getInt(4);
            int nbNuls=joueur.getInt(5);
            Client joueurJava=new Client(nomJoueur);
            joueurJava.setNbVictoires(nbVictoires);
            joueurJava.setNbDefaites(nbDefaites);
            joueurJava.setNbNuls(nbNuls);
            return joueurJava;
        }
        return null;
    }

    // getPartieFromId : get la partie (faut savoir c'est quel objet)
    // insertPartie : Insert la partie dans la bd (joueurs, gagnant)
}
