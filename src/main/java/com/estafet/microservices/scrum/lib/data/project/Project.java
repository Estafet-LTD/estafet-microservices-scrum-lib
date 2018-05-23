package com.estafet.microservices.scrum.lib.data.project;

public class Project {

	private Integer id;

	private String title;

	private Integer noSprints;

	private Integer sprintLengthDays;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getNoSprints() {
		return noSprints;
	}

	public Integer getSprintLengthDays() {
		return sprintLengthDays;
	}

	Project setId(Integer id) {
		this.id = id;
		return this;
	}

	Project setTitle(String title) {
		this.title = title;
		return this;
	}

	Project setNoSprints(Integer noSprints) {
		this.noSprints = noSprints;
		return this;
	}

	Project setSprintLengthDays(Integer sprintLengthDays) {
		this.sprintLengthDays = sprintLengthDays;
		return this;
	}

}
