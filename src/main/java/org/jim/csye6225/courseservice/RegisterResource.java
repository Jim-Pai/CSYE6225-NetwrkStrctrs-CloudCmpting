package org.jim.csye6225.courseservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("courses/{courseId}/students")
public class RegisterResource {
	
	private static Map<String, List<Student>> courseStudents = new HashMap<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Student> getEnrolledStudents(@PathParam("courseId") String courseId){
		return courseStudents.get(courseId);
	} 
	
	@POST
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Course> enrollCourse(String text
			, @PathParam("courseId") String courseId
			, @PathParam("studentId") String studentId) {
		Student student = StudentResource.getStudentInstance(studentId);
		Course course = CourseResource.getCourseInstance(courseId);
		student.enrollCourse(course);
		List<Student> students = courseStudents.getOrDefault(courseId, new ArrayList<Student>());
		if(!students.contains(student))
			students.add(student);
		courseStudents.put(courseId, students);
		//course.addStudent(student);
		return student.courses;
	} 
	
	@DELETE
	@Path("{studentId}")
	public Collection<Course> dropCourse(@PathParam("courseId") String courseId
			, @PathParam("studentId") String studentId) {
		Student student = StudentResource.getStudentInstance(studentId);
		Course course = CourseResource.getCourseInstance(courseId);
		student.dropCourse(course);
		//course.removeStudent(student);
		return student.courses;
	} 
}
