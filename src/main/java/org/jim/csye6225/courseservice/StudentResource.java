package org.jim.csye6225.courseservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

@Path("programs/{programId}/students")
public class StudentResource {
	// key: studentId, value: Student
	private static Map<String, Student> studentMap = new HashMap<>();
	// key: programId, value: list of Student
	private static Map<String, List<Student>> programStudent = new HashMap<>();
	
	public static Student getStudentInstance(String studentId) {
		return studentMap.get(studentId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Student> getStudentsInProgram(@PathParam("programId") String programId) {
		return programStudent.get(programId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student createStudent(@PathParam("programId") String programId, Student student) {
		Program program = ProgramResource.getProgramInstance(programId);
		if(program == null)
			return null;
		
		if(!studentMap.containsKey(student.studentId)) {
			String programName = program.programName;
			student.setProgramName(programName);
			studentMap.put(student.studentId, student);
			List<Student> students = programStudent.getOrDefault(programId
					, new ArrayList<Student>());
			students.add(student);
			programStudent.put(programId, students);
		}
		return studentMap.get(student.studentId);
	}
	
	@GET
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") String studentId) {
		return studentMap.get(studentId);
	}
	
	@PUT
	@Path("{studentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student, @PathParam("studentId") String studentId
			, @PathParam("programId") String programId) {
		Student oldStudent = studentMap.get(studentId);
		if(oldStudent == null)
			return null;
		
		String programName = ProgramResource.getProgramInstance(programId).programName;
		oldStudent.update(student);
		student.setProgramName(programName);
		return studentMap.get(studentId);
	}
	
	@DELETE
	@Path("{studentId}")
	public void deleteStudent(@PathParam("studentId") String studentId) {
		Student student = studentMap.get(studentId);
		if(student == null)
			return;
		
		studentMap.remove(studentId);
		List<Student> list = programStudent.get(studentId);
		if(list != null && list.contains(student)) {
			list.remove(student);
		}
	}
}
