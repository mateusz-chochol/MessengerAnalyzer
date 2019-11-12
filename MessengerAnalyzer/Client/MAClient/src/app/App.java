package app;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (
            Socket socket = new Socket("127.0.0.1", 9991);
            Scanner serverReader = new Scanner(new InputStreamReader(socket.getInputStream()));
            Scanner userReader = new Scanner(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            while (serverReader.hasNextLine()) {
                System.out.println("Server says: " + serverReader.nextLine());
                out.println(userReader.nextLine());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Connection has been closed.");
    }
}