package com.estafet.microservices.scrum.lib.data;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class PollingEventValidator {

	abstract public boolean success();
		
	public PollingEventValidator() {
		this(10000);
	}
	
	public PollingEventValidator(int timeout) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(new Callable<String>() {
		    public String call() throws Exception {
		    	while (!success()) {
					Thread.currentThread().sleep(100);
				}
		        return "OK";
		    }
		});
		try {
			future.get(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
