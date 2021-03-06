/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Passenger extends Thread {
    private PassengerCounter passengerCounter;
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;
    private int passengerId;

    public Passenger(PassengerCounter passengerCounter, Semaphore mutex, 
            Semaphore bus, Semaphore boarded, int passengerId) {
        this.passengerCounter = passengerCounter;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.passengerId = passengerId;
    }
    
    public void run(){
        try {
            mutex.acquire(); // put a passenger to boardin area
        } catch (InterruptedException ex) {
            Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        passengerCounter.incrementCount(); // increment passenger counter
        System.out.println("Passenger with Id:" + passengerId + " added to boarding area");
        mutex.release();
        try {
            bus.acquire(); // wait for a bus
        } catch (InterruptedException ex) {
            Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        board(passengerId); // board the passenger to the bus
        boarded.release(); // notify that passenger boarded to the bus
    }
    public void board(int passengerId){
        System.out.println("Boarded Passenger Id:" + passengerId);
    }
}
