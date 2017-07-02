package com.tut5;

public class App3 {

	public static int counter1 = 0;
	public static int counter2 = 0;

	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				compute();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				compute();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Count1 : " + counter1);
		System.out.println("Count2 : " + counter2);

	}

	public static void compute() {
		for (int i = 0; i < 1000; i++) {
			add();
			addAgain();
		}
	}

	public static synchronized void add() {
//		synchronized (App3.class) {
			System.out.println("Inside Lock1 -"+Thread.currentThread().getName());
			counter1++;
//		}
	}

	public static synchronized void addAgain() {
//		synchronized (App3.class) {
			System.out.println("Inside Lock2 -"+Thread.currentThread().getName());
			counter2++;
//		}
	}
	
	public static void counter() {
		synchronized (lock2) {
			System.out.println("Inside Lock2 "+Thread.currentThread().getName());
			counter2++;
		}
	}
	

}
