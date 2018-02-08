/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class SenateBusProblem {

    /**
     * @param args the command line arguments
     */
    private static Semaphore mutex = new Semaphore(1);
    private static Semaphore bus = new Semaphore(0);
    private static Semaphore boarded = new Semaphore(0);
    private static PassengerCounter passengerCounter = new PassengerCounter();
    
    
    
    public static void main(String[] args) {
        long diff_bus=0;
        long diff_passenger=0;
        long time_curr=0;
        long time_prev_bus=System.currentTimeMillis();
        long time_prev_passenger=System.currentTimeMillis();
        double mean_passenger=30000;
        double mean_bus=1200000;
        double rand_bus =  new Random().nextDouble();
        double wait_time_bus = Math.round(Math.log10(rand_bus)*-1*mean_bus)%1000;
        double rand_passenger = new Random().nextDouble();
        double wait_time_passenger = Math.round(Math.log10(rand_passenger)*-1*mean_passenger)%10;
        
        int i = 0;
        while (true){
            time_curr=System.currentTimeMillis();
            diff_passenger=time_curr-time_prev_passenger;
            diff_bus=time_curr-time_prev_bus;
            if(diff_passenger >= wait_time_passenger){
                (new Passenger(passengerCounter, mutex, bus, boarded, i)).
                    start();
                time_prev_passenger=time_curr;
                rand_passenger  = new Random().nextDouble();   
                wait_time_passenger = Math.round(Math.log10(rand_passenger)*-1*mean_passenger)%10;
                i++;
            }
            
            if (diff_bus >= wait_time_bus) {
                (new Bus(passengerCounter, mutex, bus, boarded)).start();
                time_prev_bus=time_curr;
                rand_bus  = new Random().nextDouble();
                wait_time_bus = Math.round(Math.log10(rand_bus)*-1*mean_bus)%1000;
            }
        }
    }
    
}
