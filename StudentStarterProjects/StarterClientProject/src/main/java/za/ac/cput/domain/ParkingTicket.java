/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.domain;

/**
 *
 * @author BURGERR
 */
import java.io.Serializable;

/**
 * PROVIDED - do not modify.
 * Worker class for one parking ticket. It is sent between the client and the server.
 * The client project and the server project must contain an identical copy of this class
 * (same package name, same serialVersionUID).
 */
public class ParkingTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    private String plateNumber;
    private String driverName;
    private int hoursParked;
    private double amountDue;

    public ParkingTicket(String plateNumber, String driverName, int hoursParked, double amountDue) {
        this.plateNumber = plateNumber;
        this.driverName = driverName;
        this.hoursParked = hoursParked;
        this.amountDue = amountDue;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getDriverName()  { return driverName; }
    public int getHoursParked()    { return hoursParked; }
    public double getAmountDue()   { return amountDue; }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %3d hrs   R%9.2f",
                plateNumber, driverName, hoursParked, amountDue);
    }
}
