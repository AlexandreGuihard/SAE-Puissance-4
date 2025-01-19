package main.java.etu.iuto;

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
    private Boolean enpartie;

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
            if ( this.serveurPuissance4.getListinterdite().contains(nomjoueur)) {
                this.writer.println("nom de commande ne peut pas etre un nom");
                return false;
            }
            if (nomjoueur.matches(".*" + this.serveurPuissance4.getListCaractereInterdie() + ".*")) {
                this.writer.println("nom avec caractere interdite");
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
        } catch (Exception e){
            System.err.println("[erreur]" + e);
            this.deconnection();
        }
    }



    public void Listfonction(){
        this.writer.println("Joueurs connectés : " + this.serveurPuissance4.getJoueurDisponible().keySet());
    }



    public void AskFonction(String[] parts){
        if (parts.length == 2) {
            if(this.serveurPuissance4.getJoueurDisponible().containsKey(parts[1]) && !parts[1].equals(this.nomDuJoueur)){

                this.writer.println("Demande en cours");
                if (this.serveurPuissance4.partie(this.serveurPuissance4.getJoueurDisponible().get(this.nomDuJoueur), this.serveurPuissance4.getJoueurDisponible().get(parts[1]))){
                    this.writer.println("partie accepter");
                }
                else{
                    this.writer.println("partie non accepter");
                }
            }
            else if(parts[1].equals(this.nomDuJoueur)){
                this.writer.println("vous ne pouvez pas faire un partie contre vous meme");
            }
            else if(this.serveurPuissance4.getPlayers().containsKey(parts[1])){
                this.writer.println("vous ne pouvez pas faire un partie ce joueur est deja en partie");
            }
            else if (!parts[1].equals(this.nomDuJoueur)){
                this.writer.println("le joueur que vous chercher n existe pas");
            }
        }
    }

    public Client creationJoueur(){
        Client player = new Client(this.nomDuJoueur,this.reader,this.writer);
        this.serveurPuissance4.getJoueurDisponible().put(this.nomDuJoueur, player);
        this.writer.println("OK connection etablie Bienvenue " + this.nomDuJoueur + " Que voulez vous faire vous pouvez faire: LIST,ASK [joueur],EXIT");
        return player;
    }

    @Override
    public void run() {
        try {
            while (!this.connect(this.reader.readLine())) {
                System.out.println("Serveur en attente du nom de " + this.clientSocket );
            }

            Client player =this.creationJoueur();
            /*partie de la gestion des commande taper */
            String message;
            while ((message = this.reader.readLine()) != null) {
                if (message.equalsIgnoreCase("LIST")) {
                    this.Listfonction();
                }
                else if (message.startsWith("ASK ") && !this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)) {
                    String[] parts = message.split(" ", 2);
                    this.AskFonction(parts);
                }
                else if (message.startsWith("ASK ") && this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)) {
                    this.writer.println("quitter cette partie pour demander une autre partie");
                }

                else if (message.equalsIgnoreCase("ASK")) {
                    this.writer.println("il faut rajouter un espace et le joueur");
                }
                else if (message.equalsIgnoreCase("QUIT") && !this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur) || message.equalsIgnoreCase("EXIT") && !this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur))  {
                    this.serveurPuissance4.getPlayers().remove(this.nomDuJoueur, player);
                    this.writer.println("Au revoir !");
                    break;
                }

                else if (message.equalsIgnoreCase("QUIT") && this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur) || message.equalsIgnoreCase("EXIT") && this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)){

                }

                else if (message.startsWith("> null")) {
                    this.deconnection();
                }

                else if(!this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)){
                    this.writer.println("Commande incorrecte vous pouvez faire: LIST,ASK [joueur],EXIT");
                }

                else if(this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)){
                    this.writer.println("help");
                }


            }
        } catch (IOException e) {
            System.err.println("Erreur avec le client : " + e.getMessage());
        }
        finally {
            try {
                if (this.clientSocket.isConnected() || !this.clientSocket.isClosed()) {
                    this.deconnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}