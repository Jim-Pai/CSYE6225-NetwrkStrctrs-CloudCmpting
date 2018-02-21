package org.jim.csye6225.courseservice;


import java.util.ArrayList;
import java.util.List;

public class Program {
	
	public String programId;
	public String programName;
	public List<Course> courses;
	
	public Program() {}
	
	public Program(String programId, String name) {
		this.programId = programId;
		this.programName = name;
		this.courses = new ArrayList<>();
	}
	
	public Program(Program program) {
		this.programId = program.programId;
		this.programName = program.programName;
		this.courses = new ArrayList<>(program.courses);
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
}
