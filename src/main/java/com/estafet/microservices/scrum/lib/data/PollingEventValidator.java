package com.estafet.microservices.scrum.lib.data;

public abstract class PollingEventValidator {

	abstract public boolean success();
		
	public PollingEventValidator() {
		this(10000);
	}
	
	public PollingEventValidator(int timeout) {
		int wait = 0;
		while (!success()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			wait+= 500;
			if (wait >= timeout) {
				throw new RuntimeException("Timeout");
			}
		}
	}
	
}
