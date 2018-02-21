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

@Path ("programs")
public class ProgramResource {
	
	// key: programId, value:Program
	private static Map<String, Program> programMap = new HashMap<>(); 
	
	public static Program getProgramInstance(String programId) {
		return programMap.get(programId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Program> getProgramList() {
		return programMap.values();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Program createProgram(Program program) {
		if(!programMap.containsKey(program.programId)) {
			programMap.put(program.programId, program);
		}
		System.out.println("id: " + program.programId + ", name: " + program.programName);
		return programMap.get(program.programId);
	}
	
	@GET
	@Path("{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") String programId) {
		return programMap.get(programId);
	}
	
	@PUT
	@Path("{programId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Program updateProgram(Program program) {
		programMap.put(program.programId, program);
		return program;
	}
	
	@DELETE
	@Path("{programId}")
	public void deleteProgram(@PathParam("programId") String programId) {
		if(programMap.containsKey(programId)) {
			programMap.remove(programId);
			System.out.println("Delete Successfully.");
		}
		else {
			System.out.println("Program Doesn't Exist.");
		}
	}
}
