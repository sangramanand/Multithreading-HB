package com.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * BlockingQueue -> an interface that represents a queue that is thread safe Put
 * items or take items from it...
 * 
 * For example: one thread putting items into the queue and another thread
 * taking items from it at the same time!!! We can do it with Producer-Consumer
 * pattern !!!
 * 
 * 
 * put() putting items to the queue take() taking items from the queue
 * 
 *
 */

class FirstWorker implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public FirstWorker(BlockingQueue<Integer> blockingQueue) {
		super();
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {

		int counter = 0;

		while (true) {
			try {
				System.out.println("Adding item from the Queue... " + counter+" Thread: "+Thread.currentThread().getName());
				blockingQueue.put(counter);
				counter++;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}

class SecondWorker implements Runnable {

	private BlockingQueue<Integer> blockingQueue;

	public SecondWorker(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {

		while (true) {
			try {
				int number = blockingQueue.take();
				System.out.println("Taking item from the Queue... " + number+" Thread: "+Thread.currentThread().getName());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueueDemo {

	public static void main(String[] args) {

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);
		
		new Thread(firstWorker).start();
		new Thread(secondWorker).start();
		
	}

}
