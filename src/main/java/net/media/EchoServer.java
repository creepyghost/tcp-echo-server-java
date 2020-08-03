package net.media;
import java.net.*;
import java.io.*;


public class EchoServer {
    private static final int SERVER_PORT = 9090;

    public static void main(final String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(SERVER_PORT));

            while (!serverSocket.isClosed()) {

                handleSingleClient(serverSocket);

            }
        } catch (final Exception e) {
            printException(e);
        }
    }

    private static void handleSingleClient(ServerSocket serverSocket) {
        try (Socket connectionSocket = serverSocket.accept();
                BufferedReader clientReader = new BufferedReader(
                        new InputStreamReader(connectionSocket.getInputStream()));

                PrintWriter clientWriter = new PrintWriter(connectionSocket.getOutputStream());) {
            clientWriter.println(String.format("Server Response: %s", clientReader.readLine()));
        } catch (final Exception e) {
            printException(e);
        }
    }

    private static void printException(final Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}