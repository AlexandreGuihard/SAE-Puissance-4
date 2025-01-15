
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientPuissance4 extends Thread{
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1111;    

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader consoleInput;
    private String serverResponse;

    public ClientPuissance4() {
        try {
            this.socket = new Socket(SERVER_HOST, SERVER_PORT);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.consoleInput = new BufferedReader(new InputStreamReader(System.in));
            this.serverResponse ="";
        } 
        catch (Exception e) {
            System.err.println("[erreur]" + e);
        }
    }

    public boolean EntrezNom(){
        try{
            String playerName = this.consoleInput.readLine();
            this.out.println(playerName);

            // Vérifier la réponse du serveur
            this.serverResponse = in.readLine();
            if (this.serverResponse == null || this.serverResponse.startsWith("ERR ")){
                
                System.out.println("réessayer : " + this.serverResponse);
                return false;
            }
            System.out.println(serverResponse);
            return true;
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            return false;
        }
    } 

    @Override
    public void run(){
        try{
            System.out.print("Entrez votre nom : ");
            while (!this.EntrezNom()) {
                System.out.print("Entrez votre nom : ");
            }

            /* bloucle interaction */
            String userInput;
            while (true) {
                System.out.print("> "); // Prompt
                userInput = this.consoleInput.readLine();

                // Envoi de la commande au serveur
                this.out.println(userInput);

                //quitter le serveur
                if ("EXIT".equalsIgnoreCase(userInput)) {
                    System.out.println("Déconnexion...");
                    break;
                }

                /*lecture serveur */
                String response;
                while ((response = this.in.readLine()) != null) {
                    System.out.println(response);
                    if (!this.in.ready()) break;
                }
            }
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            ClientPuissance4 clientPuissance4 = new ClientPuissance4();
            System.out.println("Connecté au serveur !");
            clientPuissance4.start();
        }
        catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
