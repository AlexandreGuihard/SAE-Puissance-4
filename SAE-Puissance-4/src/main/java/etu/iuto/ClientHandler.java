import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


 public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nomDuJoueur;
    private ServeurPuissance4 serveurPuissance4;

    public ClientHandler(Socket clientSocket,ServeurPuissance4 serveurPuissance4) {
        try {
            this.clientSocket = clientSocket;
            this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.serveurPuissance4 = serveurPuissance4;
            this.nomDuJoueur ="";
        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
        }
    }

    public boolean connect(String nomjoueur){
        writer.println("donnee votre nom");
        try {
            if ( nomjoueur.isEmpty()) {
                this.writer.println("donnee votre nom");
                return false;
            }

            if (nomjoueur.length() < 3 || nomjoueur.length() > 10 ) {
                this.writer.println("ERR la taille doit etre entre 3 et 10 caratere ");
                return false;
            }
            
            if ( nomjoueur.indexOf(" ") != -1) {
                this.writer.println("ERR il y a un espace present ");
                return false;
            }

            if ((this.serveurPuissance4.getPlayers().contains(nomjoueur))){
                this.writer.println("ERR nom deja present ");
                return false;
            }

            this.writer.println("OK connection etablie ");
            this.nomDuJoueur = nomjoueur;
            System.out.println(this.nomDuJoueur);
            
            return true;

        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
            return false;
        }
    }


    public void deconnection(){

        try{

            System.out.println("fin de communication avec le client");
            writer.println("quit");
            this.reader.close();  
            this.writer.close();
            this.clientSocket.close();
        }

        catch (Exception e){
            System.err.println("[erreur]" + e);
    
        }
    }

    @Override
    public void run() {
        try {

            while (!this.connect(this.reader.readLine())) {
                System.out.println("serveur en attente");
            }

            Player player = new Player(this.nomDuJoueur);

            this.serveurPuissance4.getPlayers().put(this.nomDuJoueur, player);
            this.serveurPuissance4.getJoueurDisponible().put(this.nomDuJoueur, player);

            this.writer.println("Bienvenue " + this.nomDuJoueur);

            /*partie de la gestion des commande taper */
            String message;
            while ((message = this.reader.readLine()) != null ) {

                if (message.equalsIgnoreCase("LIST")) {
                    this.writer.println("Joueurs connectés : " + this.serveurPuissance4.getPlayers().keySet());
                }

                else if (message.startsWith("ASK ")) {
                    String[] parts = message.split(" ", 2);
                    if (parts.length == 2) {

                        player.setData(parts[1]);
                        this.writer.println("Demande en cours");
                    }
                    else {
                        this.writer.println("Commande incorrecte");
                    }
                }
                
                else if (message.equalsIgnoreCase("QUIT")) {
                    this.serveurPuissance4.getPlayers().remove(this.nomDuJoueur, player);
                    this.writer.println("Au revoir !");
                    break;
                }
                
                else {
                    this.writer.println("Commande inconnue");
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Erreur avec le client : " + e.getMessage());
        } 
        finally {
            try {
                this.deconnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}