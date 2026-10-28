/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.starterserverproject;

/**
 *
 * @author BURGERR
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import za.ac.cput.dao.TicketDAO;
import za.ac.cput.domain.ParkingTicket;
import za.ac.cput.domain.Protocol;

/** Server main class. */
public class ParkingServer {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerSocket serverSocket;
    private Socket client;
    private TicketDAO dao;

    // A1: Create server socket.
    public ParkingServer() {
        try {
            dao = new TicketDAO();
            serverSocket = new ServerSocket(Protocol.PORT);
            System.out.println("Server ready on port " + Protocol.PORT);
            client = serverSocket.accept();
            System.out.println("Client connected.");
        } catch (IOException | SQLException e) {
            System.err.println("Constructor error: " + e.getMessage());
        }
    }

    // A2: Create object streams.
    public void getStreams() {
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            System.err.println("Stream error: " + e.getMessage());
        }
    }

    // A3: Handle client commands.
    public void processClient() {
        try {
            while (true) {
                String command = (String) in.readObject();
                if (Protocol.ADD.equals(command)) {
                    // Read ticket from client.
                    ParkingTicket ticket = (ParkingTicket) in.readObject();
                    try {
                        dao.insertTicket(ticket);
                        out.writeObject(Protocol.OK);
                    } catch (SQLException e) {
                        out.writeObject(Protocol.ERROR + e.getMessage());
                    }
                    out.flush();
                } else if (Protocol.RETRIEVE.equals(command)) {
                    // Send all tickets.
                    out.writeObject(dao.getAllTickets());
                    out.flush();
                } else if (Protocol.EXIT.equals(command)) {
                    break;
                }
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected.");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println("Process error: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    // A4: Close streams safely.
    private void closeConnection() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null) client.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            System.err.println("Close error: " + e.getMessage());
        }
    }

    // A5: Main entry point.
    public static void main(String[] args) {
        ParkingServer server = new ParkingServer();
        server.getStreams();
        server.processClient();
    }

}//end class
