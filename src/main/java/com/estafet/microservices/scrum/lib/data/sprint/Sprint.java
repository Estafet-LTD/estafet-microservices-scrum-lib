package com.estafet.microservices.scrum.lib.data.sprint;

import java.util.ArrayList;
import java.util.List;

import com.estafet.microservices.scrum.lib.data.project.Project;
import com.estafet.microservices.scrum.lib.data.story.Story;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprint {

	private Integer id;

	private String startDate;

	private String endDate;

	private Integer number;

	private String status;

	private Integer projectId;

	private Integer noDays;

	public Integer getId() {
		return id;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public Integer getNumber() {
		return number;
	}

	public String getStatus() {
		return status;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public Integer getNoDays() {
		return noDays;
	}
	
	@JsonIgnore
	public String getName() {
		return "Sprint#" + id;
	}
	
	public List<Story> getStories() {
		List<Story> stories = new ArrayList<Story>();
		for (Story story : Project.getProjectById(projectId).getStories()) {
			if (story.getSprintId() != null && story.getSprintId().equals(id)) {
				stories.add(story);
			}
		}
		return stories;
	}
	
	public void complete() {
		for (Story story : getStories()) {
			story.complete();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
