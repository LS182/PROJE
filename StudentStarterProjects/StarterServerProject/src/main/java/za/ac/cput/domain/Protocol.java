/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.domain;

/**
 *
 * @author BURGERR
 */

/**
 * PROVIDED - do not modify.
 * The text commands and replies that the client and server exchange.
 */
public final class Protocol {
    public static final int PORT = 5050;

    // Client -> Server commands (sent with writeObject as a String)
    public static final String ADD      = "ADD";
    public static final String RETRIEVE = "RETRIEVE";
    public static final String EXIT     = "EXIT";

    // Server -> Client reply prefixes for ADD
    public static final String OK    = "OK";
    public static final String ERROR = "ERROR: ";   // followed by a short message

    private Protocol() { }
}
