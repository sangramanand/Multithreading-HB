package com.tut9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {

	/*
	 * 1) ExecutorService executorService = Executors.newCachedThreadPool();
	 * gong to run an executorService that can dyanamically reuse threads.
	 * before starting a job -> it going to check whether there are any threads
	 * that finished the job.. reuse them If there are no waiting threads -> it
	 * is going to create another one good for the processor..effective solution
	 * 
	 * 2) ExecutorService executorService = Executors.newFixedThreadPool(N);
	 * -maximize the number of threads -if we want to start a job -> if all
	 * threads are busy, we have to wait for one to terminate
	 * 
	 * 3) ExecutorService executorService = Executors.newSingleThreadExecutor();
	 * It uses a single thread for the job
	 * execute() -> runnable + callable
	 * submit()  -> runnable
	 * 
	 */

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i=0;i<5;i++){
			executorService.submit(new Worker());
		}
	}

}

class Worker implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(i+" - "+Thread.currentThread().getName());
		}
	}
}
