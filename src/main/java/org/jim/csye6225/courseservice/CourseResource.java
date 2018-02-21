package org.jim.csye6225.courseservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("programs/{programId}/courses")
public class CourseResource {
	// key: courseId, value: course
	private static Map<String, Course> courseMap = new HashMap<>();
	
	public static Course getCourseInstance(String courseId) {
		return courseMap.get(courseId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Course> getCourseList(@PathParam("programId") String programId) {
		Program program = ProgramResource.getProgramInstance(programId);
		return program.courses;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course createCourse(Course course, @PathParam("programId") String programId) {
		Program program = ProgramResource.getProgramInstance(programId);
		if(program == null)
			return null;
		
		if(!courseMap.containsKey(course.courseId)) {
			courseMap.put(course.courseId, course);
			program.addCourse(course);
		}
		return courseMap.get(course.courseId);
	}
	
	@GET
	@Path("{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {
		return courseMap.get(courseId);
	}
	
	@PUT
	@Path("{courseId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course updateCourse(Course course, @PathParam("courseId") String courseId) {
		Course oldCourse = courseMap.get(courseId);
		if(oldCourse == null)
			return null;
		oldCourse.update(course);
		return courseMap.get(courseId);
	}
	
	@DELETE
	@Path("{courseId}")
	public void removeCourse(@PathParam("programId") String programId, 
			@PathParam("courseId") String courseId) {
		Program program = ProgramResource.getProgramInstance(programId);
		Course course = courseMap.get(courseId);
		
		if(program == null || course == null)
			return;
		
		if(program.courses.contains(course)) {
			program.courses.remove(course);
		}
		if(courseMap.containsKey(courseId)) {
			courseMap.remove(courseId);
		}
	}
}
