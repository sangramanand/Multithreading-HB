package com.tut5;

public class App {

	public static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				addOne();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				addTwo();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Finished Tasks :: " + Thread.currentThread().getName());
		System.out.println("Value of counter = " + counter);
	}

	public static synchronized void incrementCounter() {
		++counter;
	}

	public static void addOne() {
		synchronized (App.class) {
			System.out.println("Inside AddOne..");
			for (int i = 0; i < 1000; i++) {
				incrementCounter();
			}
		}
	}

	public static void addTwo() {
		synchronized (App.class) {
			System.out.println("Inside AddTwo..");
			for (int i = 0; i < 1000; i++) {
				incrementCounter();
			}
		}
	}

}
