package com.estafet.microservices.scrum.lib.data.task;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

	private Integer id;

	private String title;

	private String description;

	private Integer initialHours;

	private Integer remainingHours;

	private String status = "Not Started";

	private String remainingUpdated;
	
	private Integer sprintId;

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
	
	private String getLastSprintDay() {
		List<String> days = new RestTemplate().getForObject(System.getenv("SPRINT_API_SERVICE_URI") + "/sprint/{id}/days",
				List.class, sprintId);
		return days.get(days.size() - 1);
	}
	
	public void claim() {
		new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}/claim", null,
				Task.class, id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void complete() {
		new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}/complete", getLastSprintDay(),
				Task.class, id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
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

	public void setSprintId(Integer sprintId) {
		this.sprintId = sprintId;
	}
	
	

	
}
