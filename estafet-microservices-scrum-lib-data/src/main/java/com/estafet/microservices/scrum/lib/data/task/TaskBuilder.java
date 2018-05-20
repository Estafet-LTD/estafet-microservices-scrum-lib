package com.estafet.microservices.scrum.lib.data.task;

import org.springframework.web.client.RestTemplate;

public class TaskBuilder {

	private String title;

	private String description;

	private Integer initialHours;

	private Integer storyId;

	public Task build() {
		return new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/story/{id}/task",
				new Task().setTitle(title).setDescription(description).setInitialHours(initialHours), Task.class,
				storyId);
	}

	TaskBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	TaskBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	TaskBuilder setInitialHours(Integer initialHours) {
		this.initialHours = initialHours;
		return this;
	}

	TaskBuilder setStoryId(Integer storyId) {
		this.storyId = storyId;
		return this;
	}

}
