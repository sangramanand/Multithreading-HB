package com.tut2;

public class App {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner4());
		Thread t2 = new Thread(new Runner5());
		Thread t3 = new Thread(new Runner4());
		Thread t4 = new Thread(new Runner5());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

}

class Runner4 implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<10;++i){
			System.out.println("Runner4 :"+i+" Thread Name" +Thread.currentThread().getName());
		}
	}
}

class Runner5 implements Runnable{
	@Override
	public void run(){
		for(int i=0;i<10;++i){
			System.out.println("Runner5 :"+i+" Thread Name" +Thread.currentThread().getName());
		}
	}
}
