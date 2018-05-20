package com.estafet.microservices.scrum.lib.data.story;

import org.springframework.web.client.RestTemplate;

public class StoryBuilder {

	private String title;

	private String description;

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
		return new RestTemplate().postForObject(System.getenv("STORY_API_SERVICE_URI") + "/project/{id}/story",
				new Story().setDescription(description).setSprintId(sprintId).setTitle(title)
						.setStorypoints(storypoints),
				Story.class, projectId);
	}

}
