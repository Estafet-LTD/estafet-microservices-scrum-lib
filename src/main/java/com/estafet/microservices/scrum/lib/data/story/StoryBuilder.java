package com.estafet.microservices.scrum.lib.data.story;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class StoryBuilder {

	private String title= "my title";

	private String description = "my description";

	private Integer storypoints;

	private Integer sprintId;

	private Integer projectId;

	public StoryBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public StoryBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public StoryBuilder setStorypoints(Integer storypoints) {
		this.storypoints = storypoints;
		return this;
	}

	public StoryBuilder setSprintId(Integer sprintId) {
		this.sprintId = sprintId;
		return this;
	}

	public StoryBuilder setProjectId(Integer projectId) {
		this.projectId = projectId;
		return this;
	}

	public Story build() {
		try {
			return new RestTemplate().postForObject(System.getenv("STORY_API_SERVICE_URI") + "/project/{id}/story",
					new Story().setDescription(description).setTitle(title).setStorypoints(storypoints),
					Story.class, projectId);
		} finally {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
