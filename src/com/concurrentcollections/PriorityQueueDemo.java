package com.concurrentcollections;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/*
 * PriorityBlockingQueue !!!
 * 
 * It implements the BlockingQueue interface
 * 
 * --- unbounded concurrent queue
 * --- It uses the same ordering rules as the java.util.PriorityQueue class -> have
 * to implement the Comparable interface
 * The comparable interface will determine what will be the order in the queue
 * 
 * ---	The priority can be the same compare() == 0 case
 * 
 * --- no null items
 */

class FirstWorkker implements Runnable {

	BlockingQueue<String> queue;

	public FirstWorkker(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("inside First worker"+Thread.currentThread().getName());
		try {
			queue.put("H");
			queue.put("A");
			queue.put("N");
			queue.put("L");
			queue.put("F");
			queue.put("Z");
			queue.put("C");
			queue.put("D");
			queue.put("E");
			queue.put("I");
			System.out.println("Added all items in the queue");
			Thread.sleep(10000);
			System.out.println(queue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class SecondWorkker implements Runnable {

	BlockingQueue<String> queue;

	public SecondWorkker(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("inside second worker"+Thread.currentThread().getName());
		try {
			Thread.sleep(50000);
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			
			
//			Thread.sleep(10000);
			System.out.println(queue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class PriorityQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new PriorityBlockingQueue();
		Thread t1 = new Thread(new FirstWorkker(queue));
		Thread t2 = new Thread(new SecondWorkker(queue));

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All Tasks completed!!!");

	}
}
