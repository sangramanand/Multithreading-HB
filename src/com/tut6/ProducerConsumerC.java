package com.tut6;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerC {

	public static void main(String[] args) {

		ListProcessorA listProcessor = new ListProcessorA();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					listProcessor.producer();
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
					listProcessor.consumer();
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

		System.out.println();
	}

}

class ListProcessorA {

	public List<Integer> numList = new ArrayList<Integer>();
	private int minSize = 0;
	private int maxSize = 5;
	private int value = 0;

	private Object lock = new Object();

	public void producer() throws InterruptedException {

		synchronized (lock) {
			System.out.println("Inside producer...");
			while (true) {
				if (numList.size() == maxSize) {
					System.out.println("List is full, wait() to be applied for list consumption ");
					lock.wait();
				}
				System.out.println("after wait...");
				numList.add(value);
				System.out.println(value + " added, the size of list is " + numList.size());
				value++;
				lock.notify();
				
				Thread.sleep(500);
			}
		}
	}

	public void consumer() throws InterruptedException {

		
		System.out.println("Inside consumer...");
		synchronized (lock) {
			System.out.println("after wait...");
			while (true) {
				if (numList.size() == minSize) {
					System.out.println("List is empty, wait() to be applied for list production ");
					lock.wait();
				}
				System.out.println("before remove...");
				numList.remove(value - 1);
				System.out.println(value + " removed, the size of list is " + numList.size());
				value--;
				lock.notify();
				
				Thread.sleep(500);
			}
		}
	}

}
