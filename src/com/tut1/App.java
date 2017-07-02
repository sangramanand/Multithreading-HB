package com.tut1;

public class App {

	public static void main(String[] args) {
		
		Runner1 runner1 = new Runner1();
		runner1.startRun();
		
		Runner2 runner2 = new Runner2();
		runner2.startRun();
		
		
	}

}


class Runner1 {
	public void startRun(){
		for(int i=0;i<10;++i){
			System.out.println("Runner1 :"+i);
		}
	}
}

class Runner2 {
	public void startRun(){
		for(int i=0;i<10;++i){
			System.out.println("Runner2 :"+i);
		}
	}
}
