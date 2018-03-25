package org.jim.csye6225.courseservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jim.csye6225.courseservice.database.DynamoDB;

@Path("professors")
public class ProfessorResource {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professor createProfessor(Professor professor) {
		DynamoDB dynamoDB = DynamoDB.getInstance();
		if(dynamoDB.contains("Professors", professor.id))
			return null;
		
		dynamoDB.addOrUpdateItem(professor);
		return professor;
	}
	
	@GET
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getInstance();
		return (Professor)dynamoDB.getItem("Professors", professorId);
	}
	
	@PUT
	@Path("{professorId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(Professor professor
			, @PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getInstance();
		dynamoDB.addOrUpdateItem(professor);
		return professor;
	}
	
	@DELETE
	@Path("{professorId}")
	public void deleteProfessor(@PathParam("professorId") String professorId) {
		DynamoDB dynamoDB = DynamoDB.getInstance();
		if(!dynamoDB.contains("Professors", professorId))
			return;
		dynamoDB.deleteItem("Professors", professorId);
	}
	
}
