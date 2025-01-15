import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServeurPuissance4 {
    private static final int PORT = 1111;
    
    private  Map<String, Player> players = Collections.synchronizedMap(new HashMap<>());
    private  Map<String, Player> joueurDisponible = Collections.synchronizedMap(new HashMap<>());
    private ServerSocket serverSocket;
    
    public ServeurPuissance4(){
        try {
            this.serverSocket = new ServerSocket(PORT); 
        }
        catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Map<String, Player> getJoueurDisponible() {
        return joueurDisponible;
    }


    public void connection(){
        try {
            Socket clientSocket = this.serverSocket.accept();
            System.out.println("Nouveau joueur connecté : " + clientSocket.getInetAddress());
            new Thread(new ClientHandler(clientSocket,this)).start();
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Démarrage du serveur...");
        ServeurPuissance4 serveurPuissance4 = new ServeurPuissance4();

        while (true) {
            serveurPuissance4.connection();
        }
       
    }

   

}
