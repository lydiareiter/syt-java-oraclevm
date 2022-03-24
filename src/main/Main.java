import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final int port = 9999;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Starting Serversocket on port " + port);

            while(true) {
                Socket socket = serverSocket.accept();

                Thread thread = new Thread() {
                    public void run() {
                        System.out.println("Client connected");
                        PrintWriter writer = null;
                        try {
                            writer = new PrintWriter(socket.getOutputStream(), true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        writer.println("wer bist du?");

                        Scanner sc = null;
                        try {
                            sc = new Scanner(socket.getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String message = "";

                        while (! message.toUpperCase().equals("Q")) {
                            message = sc.nextLine();
                            System.out.println(message);

                            if(message.toUpperCase().equals("Q")) {
                                writer.println("Goodbye");
                            } else {
                                writer.println(message.toUpperCase());
                            }
                        }

                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        writer.close();
                    }
                };
                thread.start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing ServerApp");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
