
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
            this.deconnection();
        }
    }

    public boolean connect(String nomjoueur){
        
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
            if ((this.serveurPuissance4.getJoueurDisponible().containsKey(nomjoueur)||this.serveurPuissance4.getPlayers().containsKey(nomjoueur))){
                this.writer.println("ERR nom deja present ");
                return false;
            }
            this.nomDuJoueur = nomjoueur;
            System.out.println(this.nomDuJoueur);
            return true;

        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
            this.deconnection();
            return false;
        }

    }

    

    

    public void deconnection(){
        try{
            System.out.println("fin de communication avec le client " + this.clientSocket );
            writer.println("quit");
            this.reader.close();  
            this.writer.close();
            this.clientSocket.close();
            if (this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)) {
                this.serveurPuissance4.getPlayers().remove(this.nomDuJoueur);
            }
            if (this.serveurPuissance4.getJoueurDisponible().containsKey(this.nomDuJoueur)) {
                this.serveurPuissance4.getJoueurDisponible().remove(this.nomDuJoueur);
            }
        }
        catch (Exception e){
            System.err.println("[erreur]" + e);
            this.deconnection();
        }
    }

    @Override
    public void run() {
        try {
            while (!this.connect(this.reader.readLine())) {
                System.out.println("Serveur en attente du nom de " + this.clientSocket );
            }
            Player player = new Player(this.nomDuJoueur,this.reader,this.writer);
            
            this.serveurPuissance4.getJoueurDisponible().put(this.nomDuJoueur, player);
            this.writer.println("OK connection etablie Bienvenue " + this.nomDuJoueur + " Que voulez vous faire");

            /*partie de la gestion des commande taper */
            String message;
            while ((message = this.reader.readLine()) != null ) {
                if (message.equalsIgnoreCase("LIST")) {
                    this.writer.println("Joueurs connectés : " + this.serveurPuissance4.getJoueurDisponible().keySet());
                }
                else if (message.startsWith("ASK ")) {
                    String[] parts = message.split(" ", 2);
                    if (parts.length == 2) {
                        if(this.serveurPuissance4.getJoueurDisponible().containsKey(parts[1]) && !parts[1].equals(this.nomDuJoueur)){
                            this.writer.println("Demande en cours");
                            this.serveurPuissance4.partie(this.serveurPuissance4.getJoueurDisponible().get(this.nomDuJoueur), this.serveurPuissance4.getJoueurDisponible().get(parts[1]));
                        }
                            //this.serveurPuissance4.getPlayers().put(this.nomDuJoueur, player);
                        else if(parts[1].equals(this.nomDuJoueur)){
                            this.writer.println("vous ne pouvez pas faire un partie contre vous meme pour le moment");
                        }

                        else if(this.serveurPuissance4.getPlayers().containsKey(parts[1])){
                            this.writer.println("vous ne pouvez pas faire un partie ce joueur est deja en partie");
                        }
                        
                        else if (!parts[1].equals(this.nomDuJoueur)){
                            this.writer.println("le joueur que vous chercher n existe pas");
                        }
                        //player.setData(parts[1]);
                    }
                }


                
                else if (message.equalsIgnoreCase("QUIT") || message.equalsIgnoreCase("EXIT")) {
                    this.serveurPuissance4.getPlayers().remove(this.nomDuJoueur, player);
                    this.writer.println("Au revoir !");
                    this.deconnection();
                    break;
                }
                
                else {
                    this.writer.println("Commande incorrecte vous pouvez faire: LIST,ASK [joueur],EXIT");
                }
                }
            

        } 
        catch (IOException e) {
            System.err.println("Erreur avec le client : " + e.getMessage());
        }
        finally {

            try {
                this.deconnection();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}