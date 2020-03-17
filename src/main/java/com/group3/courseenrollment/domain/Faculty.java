package com.group3.courseenrollment.domain;

public class Faculty {
	private long id;
	private String name;
	private String title;

	public Faculty(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}

	public Faculty() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
