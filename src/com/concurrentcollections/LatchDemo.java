package com.concurrentcollections;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * This is used to synchronize one or more tasks by forcing
 * them to wait for the completion of a set of operations being
 * performed by other tasks
 * 
 * - You give an initial count to a CountDownLatch object, and any task
 * that calls await  on that object will block until the count reaches zero
 * 
 * - Other tasks may call countDown() on the object to reduce the count, presumably
 * when a task finishes its job.
 * 
 * - a CountDownLatch --> the count cannot be reset !!!
 * - If you need a version that resets the count, you can use a CyclicBarrier instead
 * 
 * The tasks that call countDown() are not blocked when they make that call. Only the call to await() is
 * blocked until the count reaches zero.
 * 
 * A typical use is to divide a problem into N independently solvable tasks and create a CountDownLatch with a 
 * value of N, when each task if finished it calls countDown() on the latch. Tasks waiting for the problem
 * to be solved call await() on the latch to hold themselves back until it is completed
 * For example - you want to trigger something after 10000 users download an image/file!!!
 * 
 * 
 */

public class LatchDemo {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CountDownLatch countDownLatch = new CountDownLatch(5);
		//CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Worker(i+1, countDownLatch));
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All the pre-requisites are done!!!");
		executorService.shutdown();
	}

}

class Worker implements Runnable {

	private int id;
	private CountDownLatch countDownLatch;
	public Worker(int id, CountDownLatch countDownLatch) {
		this.id = id;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		doWork();
		System.out.println("Thread with Id " + this.id + " in run() method before call countDown()!!");
		countDownLatch.countDown();
		System.out.println("Thread with Id " + this.id + " in run() method after call countDown()!!");
	}

	private void doWork() {
		
		System.out.println("Thread with Id " + this.id + " is working!!!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}