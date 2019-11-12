package app;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        try(ServerSocket serverSocket = new ServerSocket(9991)) {
            Socket connectionSocket = serverSocket.accept();

            Scanner scanner = new Scanner(connectionSocket.getInputStream(), "UTF-8");
            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream(), "UTF-8"), true);

            serverPrintOut.println("Enter anything and its going to be repeated. Enter \"quit\" to exit.");

            String clientMessage = "";

            while(!clientMessage.equals("quit") && scanner.hasNextLine()) {
                clientMessage = scanner.nextLine().toLowerCase().trim();
                serverPrintOut.println(clientMessage);
            }

            connectionSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connection has been closed.");
    }
}