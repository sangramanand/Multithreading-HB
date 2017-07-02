package com.tut6;

/*
 * Producer-Consumer Method
*/

class Processor {

	public void producer() throws InterruptedException {
		synchronized (this) {
			System.out.println("We are in Producer method");
			wait();
			// System.out.println("post wait() method");
			System.out.println("Again back in Producer method");
		}
	}

	public void consumer() throws InterruptedException {

		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("We are in consumer method");
			notify();
			// System.out.println("post notify() method");
		}
	}
}

public class ProducerConsumerA {

	public static void main(String[] args) {
		Processor processor = new Processor();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

		System.out.println("Completed!!!");
	}

}
