package com.group3.courseenrollment.domain;

public class Offering {
	private long id;
	private String code;
	private Course course;
	private Block block;

	public Offering(String code, Course course, Block block) {
		super();
		this.code = code;
		this.course = course;
		this.block = block;
	}

	public Offering() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

}
