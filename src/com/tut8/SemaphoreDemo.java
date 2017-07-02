package com.tut8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
 * Semaphore maintains a set of permits
 * acquire()  -> If a permit is available then takes it
 * release() -> adds a permit
 * 
 * Semaphore just keeps a count of the number available
 * Sempahore(int permits, boolean fair)!!!
 */

enum Downloader {
	INSTANCE;
	private Semaphore semaphore = new Semaphore(3, true);
	
	public void downloadData(){
		try {
			System.out.println("Semaphore acquiring lock!!! "+Thread.currentThread().getName());
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("Semaphore releasing lock!!! "+Thread.currentThread().getName());
			semaphore.release();
		}
	}

	private void download() {
		System.out.println("*** Downloading data from web!!!"+Thread.currentThread().getName()+" ***");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



public class SemaphoreDemo {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i=0;i<10;i++){
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}

}
