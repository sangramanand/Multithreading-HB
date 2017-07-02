package com.tut4;

public class App {

	public static int counter = 0;
	public static void main(String[] args) throws InterruptedException {
		
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					incrementCounter();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					incrementCounter();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Finished Tasks :: "+Thread.currentThread().getName());
		System.out.println("Value of counter = "+counter );
	}
	
	public static synchronized  void incrementCounter(){
			++counter;
	}

}

