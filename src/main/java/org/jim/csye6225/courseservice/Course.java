package org.jim.csye6225.courseservice;

import java.util.List;

public class Course {
	public String courseId;
	public String courseName;
	//public List<Student> students;
	public List<Lecture> lectures;
	//board, roster
	
	public Course() {}
	
	public void update(Course course) {
		this.courseId = course.courseId;
		this.courseName = course.courseName;
		//this.students = course.students;
		this.lectures = course.lectures;
	}
	
//	public void addStudent(Student student) {
//		if(!students.contains(student))
//			this.students.add(student);
//	}
//	
//	public void removeStudent(Student student) {
//		if(students == null)
//			return;
//		if(students.contains(student))
//			this.students.remove(student);
//	}
	
	public void addLecture(Lecture lecture) {
		if(!lectures.contains(lecture))
			lectures.add(lecture);
	}
	
	public void removeLecture(Lecture lecture) {
		if(lectures.contains(lecture))
			lectures.remove(lecture);
	} 
}
