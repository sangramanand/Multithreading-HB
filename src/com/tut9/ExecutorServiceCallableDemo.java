package com.tut9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceCallableDemo {

	public static void main(String[] args) {

		List<String> resultList = new ArrayList<String>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			Future<String> submit = executorService.submit(new Processor(i + 1));
			try {
				resultList.add(submit.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		resultList.forEach(System.out::println);
	}
}

class Processor implements Callable<String> {

	private Integer id;

	public Processor(Integer id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {

		return "CurrentThread - " + Thread.currentThread().getName() + ". TaskId - " + id;
	}

}