package etu.iuto.historique;
import java.sql.*;

public class ConnexionBD {
    Connection connection;
    boolean connected;

	/**
	 * Constructeur de la classe de la connexion au serveur sql. Tente d'établir une connexion à la bd
	 * @param nomServeur le nom du serveur sql
	 * @param database la base de données choisie
	 * @param login le login permettant de se connecter à la bd
	 * @param password le mot de passe permettant de se connecter à la bd
	 */
    public ConnexionBD(String nomServeur, String database, String login, String password){
        try {
			//Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("org.mariadb.jdbc.Driver"); // Driver à l'IUT
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver perso
		} catch (ClassNotFoundException e) {
			System.out.println("Driver MySQL non trouve\b ?");
			connection=null;
			return;
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://servinfo-maria:3306/"+database, login, password);
			connected=true;
		} catch (SQLException e) {
			System.out.println("Echec de connexion!"); 
			System.out.println(e.getMessage());
			connection=null;
			return;
		}
    }

	/**
	 * Crée un statement permettant ensuite d'effectuer des requêtes sql
	 * @return le statement
	 */
    public Statement createStatement() throws SQLException {
		return this.connection.createStatement();
	}

	/**
	 * Crée un prepared statement avec une requête préparée
	 * @return le prepared statement
	 */
    public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.connection.prepareStatement(requete);
	}
}
