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
public class Bus extends Thread {
    private PassengerCounter passengerCounter;
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;
    
    public Bus(PassengerCounter passengerCounter, Semaphore mutex, Semaphore bus,
            Semaphore boarded){
        this.passengerCounter = passengerCounter;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
    }
   
    public void run(){
        try {
            mutex.acquire();
            System.out.println("+++++++++++Bus Arrived!+++++++++++++");
        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = Math.min(50, passengerCounter.getCount());
        for (int i = 0 ; i < n ; i++){
            bus.release();
            try {
                boarded.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int m = Math.max((passengerCounter.getCount() - 50), 0);
        passengerCounter.setCount(m);
        depart();
        mutex.release();
    }

    private void depart() {
        System.out.println("+++Bus Departed++");
        System.out.println("No of Remaining Passengers:" +
                passengerCounter.getCount());
    }
    
    
}
