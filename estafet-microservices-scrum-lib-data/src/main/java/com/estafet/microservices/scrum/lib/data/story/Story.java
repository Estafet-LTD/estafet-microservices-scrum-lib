package com.estafet.microservices.scrum.lib.data.story;

public class Story {

	private int id;

	private String title;

	private String description;

	private Integer storypoints;

	private Integer sprintId;

	private Integer projectId;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Integer getStorypoints() {
		return storypoints;
	}

	public Integer getSprintId() {
		return sprintId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	Story setTitle(String title) {
		this.title = title;
		return this;
	}

	Story setDescription(String description) {
		this.description = description;
		return this;
	}

	Story setStorypoints(Integer storypoints) {
		this.storypoints = storypoints;
		return this;
	}

	Story setSprintId(Integer sprintId) {
		this.sprintId = sprintId;
		return this;
	}

	Story setProjectId(Integer projectId) {
		this.projectId = projectId;
		return this;
	}
	
}
