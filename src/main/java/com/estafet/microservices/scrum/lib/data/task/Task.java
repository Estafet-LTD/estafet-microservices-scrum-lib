package com.estafet.microservices.scrum.lib.data.task;


public class Task {

	private Integer id;

	private String title;

	private String description;

	private Integer initialHours;

	private Integer remainingHours;

	private String status;

	private String remainingUpdated;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getInitialHours() {
		return initialHours;
	}

	public Integer getRemainingHours() {
		return remainingHours;
	}

	public String getStatus() {
		return status;
	}

	public String getRemainingUpdated() {
		return remainingUpdated;
	}

	Task setTitle(String title) {
		this.title = title;
		return this;
	}

	Task setDescription(String description) {
		this.description = description;
		return this;
	}

	Task setInitialHours(Integer initialHours) {
		this.initialHours = initialHours;
		return this;
	}

	
}
