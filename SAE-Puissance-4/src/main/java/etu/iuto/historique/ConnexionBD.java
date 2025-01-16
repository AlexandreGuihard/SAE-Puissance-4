package etu.iuto.historique;
import java.sql.*;

public class ConnexionBD {
    Connection connection;
    boolean connected;

    public ConnexionBD(String database, String login, String password){
        try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
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

    public Statement createStatement() throws SQLException {
		return this.connection.createStatement();
	}

    public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.connection.prepareStatement(requete);
	}
}
