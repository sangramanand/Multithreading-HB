package com.concurrentcollections;

import java.util.concurrent.Exchanger;

/*
 * With the help of Exchange -> two threads can exchange objects
 * 
 * exchange() -> exchanging objects is done via one of the two exchange() methods
 * 
 * For example: genetic algorithms, training neural networks
 */

class FirstWorrkker implements Runnable {

	private Integer counter = 0;
	private Exchanger<Integer> exchanger;

	public FirstWorrkker(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {
			counter++;
			System.out.println("First Thread increments the counter: " + counter + " Thread name - "
					+ Thread.currentThread().getName());

			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class SecondWorrkker implements Runnable {

	private Integer counter = 100;
	private Exchanger<Integer> exchanger;

	public SecondWorrkker(Exchanger<Integer> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		while (true) {
			counter--;
			System.out.println("Second Thread decrements the counter: " + counter + " Thread name - "
					+ Thread.currentThread().getName());

			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ExchangerDemo {

	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		Thread t1 = new Thread(new FirstWorrkker(exchanger));
		Thread t2 = new Thread(new SecondWorrkker(exchanger));
		
		t1.start();
		t2.start();
	}

}
