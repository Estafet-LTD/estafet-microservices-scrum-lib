package com.estafet.microservices.scrum.lib.data.task;

import org.springframework.web.client.RestTemplate;

public class TaskBuilder {

	private String title = "my title";

	private String description = "my description";

	private Integer initialHours = 13;

	private Integer storyId;

	public Task build() {
		try {
			return new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/story/{id}/task",
					new Task().setTitle(title).setDescription(description).setInitialHours(initialHours), Task.class,
					storyId);
		} finally {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public TaskBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public TaskBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public TaskBuilder setInitialHours(Integer initialHours) {
		this.initialHours = initialHours;
		return this;
	}

	public TaskBuilder setStoryId(Integer storyId) {
		this.storyId = storyId;
		return this;
	}

}
