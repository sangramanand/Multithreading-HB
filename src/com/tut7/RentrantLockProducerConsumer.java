package com.tut7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockProducerConsumer {

	public static void main(String[] args) {

		Worker worker = new Worker();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					worker.consumer();
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
	}

}

class Worker {

	private Lock lock = new ReentrantLock();
	private Condition newCondition = lock.newCondition();

	public void producer() throws InterruptedException {

		lock.lock();
		System.out.println("inside Producer method");
		newCondition.await();
		System.out.println("Inside Producer method Again!!!");

		lock.unlock();
	}

	public void consumer() throws InterruptedException {

		lock.lock();
		Thread.sleep(500);
		System.out.println("inside Consumer method");
		newCondition.signal();
		System.out.println("before unlock - inside consumer");
		lock.unlock();
	}

}
