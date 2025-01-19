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

    public ClientHandler(Socket clientSocket, ServeurPuissance4 serveurPuissance4) {
        this.clientSocket = clientSocket;
        this.serveurPuissance4 = serveurPuissance4;
        this.nomDuJoueur = "";
        this.enpartie = false;
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("[Erreur] " + e);
            this.deconnection();
        }
    }

    public boolean connect(String nomjoueur) {
        if (nomjoueur.isEmpty()) {
            this.writer.println("Donnez votre nom");
            return false;
        }
        if (this.serveurPuissance4.getListinterdite().contains(nomjoueur)) {
            this.writer.println("Nom de commande ne peut pas être un nom");
            return false;
        }
        if (nomjoueur.matches(".*" + this.serveurPuissance4.getListCaractereInterdie() + ".*")) {
            this.writer.println("Nom avec caractère interdit");
            return false;
        }
        if (nomjoueur.length() < 3 || nomjoueur.length() > 10) {
            this.writer.println("ERR la taille doit être entre 3 et 10 caractères");
            return false;
        }
        if (nomjoueur.indexOf(" ") != -1) {
            this.writer.println("ERR il y a un espace présent");
            return false;
        }
        if (this.serveurPuissance4.getJoueurDisponible().containsKey(nomjoueur) || this.serveurPuissance4.getPlayers().containsKey(nomjoueur)) {
            this.writer.println("ERR nom déjà présent");
            return false;
        }
        this.nomDuJoueur = nomjoueur;
        System.out.println(this.nomDuJoueur);
        return true;
    }

    public void deconnection() {
        try {
            System.out.println("Fin de communication avec le client " + this.clientSocket);
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
        } catch (Exception e) {
            System.err.println("[Erreur] " + e);
            this.deconnection();
        }
    }

    public void Listfonction() {
        this.writer.println("Joueurs connectés : " + this.serveurPuissance4.getJoueurDisponible().keySet());
    }

    public void AskFonction(String[] parts) {
        if (parts.length == 2) {
            if (this.serveurPuissance4.getJoueurDisponible().containsKey(parts[1]) && !parts[1].equals(this.nomDuJoueur)) {
                this.writer.println("Demande en cours");
                if (this.serveurPuissance4.partie(this.serveurPuissance4.getJoueurDisponible().get(this.nomDuJoueur), this.serveurPuissance4.getJoueurDisponible().get(parts[1]))){
                    this.writer.println("partie accepter");
                }
                else{
                    this.writer.println("partie non accepter");
                }
            } else if (parts[1].equals(this.nomDuJoueur)) {
                this.writer.println("Vous ne pouvez pas faire une partie contre vous-même.");
            } else if (this.serveurPuissance4.getPlayers().containsKey(parts[1])) {
                this.writer.println("Ce joueur est déjà en partie.");
            } else {
                this.writer.println("Le joueur que vous cherchez n'existe pas.");
            }
        }
    }

    public Client creationJoueur() {
        Client player = new Client(this.nomDuJoueur, this.reader, this.writer);
        this.serveurPuissance4.getJoueurDisponible().put(this.nomDuJoueur, player);
        this.writer.println("OK connexion établie. Bienvenue " + this.nomDuJoueur + ". Que voulez-vous faire? Vous pouvez faire: LIST, ASK [joueur], EXIT");
        return player;
    }

    @Override
    public void run() {
        try {
            while (!this.connect(this.reader.readLine())) {
                System.out.println("Serveur en attente du nom de " + this.clientSocket);
            }

            Client player = this.creationJoueur();
            String message;
            while ((message = this.reader.readLine()) != null) {
                if (message.equalsIgnoreCase("LIST")) {
                    this.Listfonction();
                } else if (message.startsWith("ASK ") && !this.enpartie) {
                    String[] parts = message.split(" ", 2);
                    this.AskFonction(parts);
                } else if (message.startsWith("ASK ") && this.enpartie) {
                    this.writer.println("Quittez cette partie pour demander une autre partie");
                } else if (message.equalsIgnoreCase("ASK")) {
                    this.writer.println("Il faut rajouter un espace et le joueur");
                } else if (message.equalsIgnoreCase("QUIT") && !this.enpartie || message.equalsIgnoreCase("EXIT") && !this.enpartie) {
                    this.serveurPuissance4.getPlayers().remove(this.nomDuJoueur, player);
                    this.writer.println("Au revoir !");
                    break;
                } else if (message.equalsIgnoreCase("QUIT") && this.enpartie || message.equalsIgnoreCase("EXIT") && this.enpartie) {
                } else if (message.startsWith("> null")) {
                    this.deconnection();
                } else if (!this.serveurPuissance4.getPlayers().containsKey(this.nomDuJoueur)) {
                    this.writer.println("Commande incorrecte. Vous pouvez faire: LIST, ASK [joueur], EXIT");
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur avec le client : " + e.getMessage());
        } finally {
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