package com.concurrentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * Concurrent Hashmap is a Concurrent Collection
 */

class FirstWorrker implements Runnable {

	Map<String, Integer> map;

	public FirstWorrker(Map<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {

		try {
			map.put("z", 26);
			map.put("g", 7);
			map.put("c", 3);
			Thread.sleep(1000);
			map.put("h", 8);
			map.put("a", 1);
			Thread.sleep(2000);
			map.put("n", 14);
			map.put("x", 24);
			Thread.sleep(2000);
			map.put("d", 4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class SecondWorrker implements Runnable {

	Map<String, Integer> map;

	public SecondWorrker(Map<String, Integer> map) {
		this.map = map;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(map.get("a"));
			System.out.println(map.get("c"));
			System.out.println(map.get("d"));
			System.out.println(map.get("y"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class ConcurrentMapsDemo {

	public static void main(String[] args) {
		
//		List<Integer> numList = new ArrayList<>();
//		List<Integer> synchronizedList = Collections.synchronizedList(numList);
		
		Map<String, Integer> concurHashMap = new ConcurrentHashMap<>();
		Thread t1 = new Thread(new FirstWorrker(concurHashMap));
		Thread t2 = new Thread(new SecondWorrker(concurHashMap));

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
