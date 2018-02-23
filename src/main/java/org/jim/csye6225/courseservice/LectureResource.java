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

@Path("courses/{courseId}/lectures")
public class LectureResource {
	// key: lectureId, value: Lecture
	private static Map<String, Lecture> lectureMap = new HashMap<>();
	
	public static Lecture getLectureInstance(String lectureId) {
		return lectureMap.get(lectureId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Lecture> getLectures() {
		return lectureMap.values();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture createLecture(Lecture lecture
			, @PathParam("courseId") String courseId) {
		if(!lectureMap.containsKey(lecture.lectureId)) {
			Course course = CourseResource.getCourseInstance(courseId);
			course.addLecture(lecture);
			lectureMap.put(lecture.lectureId, lecture);
		}
		return lectureMap.get(lecture.lectureId);
	} 
	
	@GET
	@Path("{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture getLecture(@PathParam("lectureId") String lectureId) {
		return lectureMap.get(lectureId);
	} 
	
	@PUT
	@Path("{lectureId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture updateLecture(Lecture lecture
			, @PathParam("lectureId") String lectureId
			, @PathParam("courseId") String courseId) {
		Lecture oldLecture = lectureMap.get(lectureId);
		if(oldLecture != null)
			oldLecture.update(lecture);
		else {
			Course course = CourseResource.getCourseInstance(courseId);
			course.addLecture(lecture);
			lectureMap.put(lecture.lectureId, lecture);
		}
		return lectureMap.get(lectureId);
	} 
	
	@DELETE
	@Path("{lectureId}")
	public void deleteLecture(@PathParam("courseId") String courseId
			, @PathParam("lectureId") String lectureId) {
		if(!lectureMap.containsKey(lectureId))
			return;
		Course course = CourseResource.getCourseInstance(courseId);
		Lecture lecture = lectureMap.get(lectureId);
		course.removeLecture(lecture);
		lectureMap.remove(lectureId);
	} 
}
