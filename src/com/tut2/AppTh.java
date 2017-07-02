package com.tut2;

public class AppTh {

	public static void main(String[] args) {
		Runner1 t1 = new Runner1();
		Runner1 t2 = new Runner1();
		Runner2 t3 = new Runner2();
		Runner2 t4 = new Runner2();
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

}

class Runner1 extends Thread{
	public void run() {
		for(int i=0;i<100;++i){
			System.out.println("Runner4 :"+i+" Thread Name" +Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Runner2 extends Thread{
	public void run(){
		for(int i=0;i<100;++i){
			System.out.println("Runner5 :"+i+" Thread Name" +Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
