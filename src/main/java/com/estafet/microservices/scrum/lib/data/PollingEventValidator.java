package com.estafet.microservices.scrum.lib.data;

public abstract class PollingEventValidator {

	private static final int DELAY = 500;
	
	abstract public boolean success();
		
	public PollingEventValidator() {
		this(5000);
	}
	
	public PollingEventValidator(int timeout) {
		try {
			int wait = 0;
			while (!success()) {
				Thread.sleep(DELAY);
				wait+= DELAY;
				if (wait >= timeout) {
					throw new RuntimeException("Timeout after " + wait + "ms");
				} 
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
