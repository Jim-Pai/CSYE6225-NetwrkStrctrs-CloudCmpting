package org.jim.csye6225.courseservice;

import java.util.List;

public class Student {
	public String name;
	public String studentId;
	//image
	public List<Course> courses;
	public String programName;
	
	public Student() {}
	
	public void update(Student student) {
		this.name = student.name;
		this.studentId = student.studentId;
		this.courses = student.courses;
	}
	
	public void enrollCourse(Course course) {
		if(!courses.contains(course))
			courses.add(course);
	} 
	
	public void dropCourse(Course course) {
		if(courses == null)
			return;
		if(courses.contains(course))
			courses.remove(course);
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
