package com.estafet.microservices.scrum.lib.selenium.pages.project;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.estafet.microservices.scrum.lib.selenium.pages.Page;
import com.estafet.microservices.scrum.lib.selenium.pages.sprint.SprintBurndownPage;
import com.estafet.microservices.scrum.lib.selenium.pages.sprint.SprintPage;
import com.estafet.microservices.scrum.lib.selenium.pages.story.AddStoryPage;
import com.estafet.microservices.scrum.lib.selenium.pages.story.StoryPage;

public class ProjectPage extends Page {
	
	@FindBys({
	    @FindBy(partialLinkText = "Story #")
	})
	@CacheLookup
	List<WebElement> storyLinks;
	
	@FindBy(xpath = "//a[@id='project_burndown']")
	@CacheLookup
	WebElement projectBurndownLink;
	
	@FindBy(xpath = "//a[@id='sprint_burndown']")
	@CacheLookup
	WebElement sprintBurndownLink;
	
	@FindBy(xpath = "//a[@id='active_sprint']")
	@CacheLookup
	WebElement activeSprintLink;
	
	@FindBy(linkText = "Add Story")
	@CacheLookup
	WebElement addStoryLink;
	
	@FindBy(linkText = "Projects")
	@CacheLookup
	WebElement projectsBreadcrumbLink;
	
	@FindBy(linkText = "Project")
	@CacheLookup
	WebElement projectBreadcrumbLink;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[2]/h2[1]/small[1]")
	@CacheLookup
	WebElement projectTitle;
	
	public ProjectPage(String projectId) {
		super(projectId);
	}

	public ProjectPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String uri() {
		return "/project/{1}";
	}
	
	@Override
	public String title() {
		return "Simple Scrum Project Management";
	}
	
	public List<String> getStories() {
		return getTextList(storyLinks);
	}
	
	public StoryPage clickStoryLink(String story) {
		return click(story, storyLinks, StoryPage.class);
	}
	
	public SprintPage clickActiveSprintLink() {
		return click(activeSprintLink, SprintPage.class);
	}
	
	public String getActiveSprintText() {
		return activeSprintLink.getText();
	}

	public AddStoryPage clickAddStoryLink() {
		return click(addStoryLink, AddStoryPage.class);
	}
	
	public ProjectBurndownPage clickProjectBurndownLink() {
		return click(projectBurndownLink, ProjectBurndownPage.class);
	}
	
	public SprintBurndownPage clickSprintBurndownLink() {
		return click(sprintBurndownLink, SprintBurndownPage.class);
	}
	
	public ProjectListPage clickProjectsBreadCrumbLink() {
		return click(projectsBreadcrumbLink, ProjectListPage.class);
	}
	
	public ProjectPage clickProjectBreadCrumbLink() {
		return click(projectBreadcrumbLink, ProjectPage.class);
	}
	
	public String getProjectTitle() {
		return projectTitle.getText();
	}
	
	public Integer getProjectId() {
		return Integer.parseInt(getCurrentURI().replaceAll("\\/project\\/", ""));
	}
}
