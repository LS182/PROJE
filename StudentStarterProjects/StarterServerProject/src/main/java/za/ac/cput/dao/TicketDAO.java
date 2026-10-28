/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.dao;

/**
 *
 * @author BURGERR
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.connection.DBConnection;
import za.ac.cput.domain.ParkingTicket;

/**
 * JDBC access for PARKING_TICKET.
 * Table: PLATE_NUMBER PK, DRIVER_NAME,
 * HOURS_PARKED, AMOUNT_DUE.
 */
public class TicketDAO {

    private Connection conn;

    // B1: Obtain DB connection.
    public TicketDAO() throws SQLException {
        conn = DBConnection.derbyConnection();
    }

    // B2: Insert one ticket.
    public void insertTicket(ParkingTicket ticket) throws SQLException {
        String sql = "INSERT INTO PARKING_TICKET "
                   + "(PLATE_NUMBER, DRIVER_NAME, HOURS_PARKED, AMOUNT_DUE) "
                   + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ticket.getPlateNumber());
            ps.setString(2, ticket.getDriverName());
            ps.setInt(3, ticket.getHoursParked());
            ps.setDouble(4, ticket.getAmountDue());
            ps.executeUpdate();
        }
    }

    // B3: Retrieve all tickets ordered.
    public List<ParkingTicket> getAllTickets() throws SQLException {
        List<ParkingTicket> list = new ArrayList<>();
        String sql = "SELECT PLATE_NUMBER, DRIVER_NAME, HOURS_PARKED, AMOUNT_DUE "
                   + "FROM PARKING_TICKET ORDER BY PLATE_NUMBER";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ParkingTicket(
                        rs.getString("PLATE_NUMBER"),
                        rs.getString("DRIVER_NAME"),
                        rs.getInt("HOURS_PARKED"),
                        rs.getDouble("AMOUNT_DUE")));
            }
        }
        return list;
    }

}
