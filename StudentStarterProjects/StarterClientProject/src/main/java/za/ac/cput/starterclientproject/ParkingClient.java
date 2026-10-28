/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.starterclientproject;

/**
 *
 * @author BURGERR
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import za.ac.cput.domain.ParkingTicket;
import za.ac.cput.domain.Protocol;

/**
 * STUDENT TASK (Part C) - all the networking code for the client goes in this class.
 * The GUI calls the public methods below. Do not change the method signatures.
 */
public class ParkingClient {

    private final String host;
    private final int port;

    // Declare socket and streams.
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ParkingClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // C1: Connect to server.
    public void connect() throws IOException {
        socket = new Socket("127.0.0.1", Protocol.PORT);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
    }

    // C2: Send ADD command.
    public String addTicket(ParkingTicket ticket) throws IOException, ClassNotFoundException {
        out.writeObject(Protocol.ADD);
        out.writeObject(ticket);
        out.flush();
        return (String) in.readObject();
    }

    // C3: Send RETRIEVE command.
    @SuppressWarnings("unchecked")
    public List<ParkingTicket> retrieveTickets() throws IOException, ClassNotFoundException {
        out.writeObject(Protocol.RETRIEVE);
        out.flush();
        return (List<ParkingTicket>) in.readObject();
    }

    // C4: Send EXIT, close all.
    public void disconnect() {
        try {
            if (out != null) {
                out.writeObject(Protocol.EXIT);
                out.flush();
            }
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Disconnect error: " + e.getMessage());
        }
    }

}
