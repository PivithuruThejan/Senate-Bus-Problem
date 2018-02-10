/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

/**
 *
 * @author User
 */
public class PassengerCounter {
    private int count = 0;// Passenger count
    
    public int getCount(){
		return count;
	}
	
    public void incrementCount(){
		count++;
	}
    public void decrementCount(){
		count--;
	}

    void setCount(int m) {
        count = m;
    }
}
