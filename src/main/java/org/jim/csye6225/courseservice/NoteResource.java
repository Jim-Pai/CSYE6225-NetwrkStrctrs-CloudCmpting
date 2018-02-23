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


@Path("lectures/{lectureId}/notes")
public class NoteResource {
	// key : noteId, value: Note
	private static Map<String, Note> noteMap = new HashMap<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Note> getNotes(){
		return noteMap.values();
	} 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Note createNote(Note note, @PathParam("lectureId") String lectureId) {
		if(!noteMap.containsKey(note.noteId)) {
			Lecture lecture = LectureResource.getLectureInstance(lectureId);
			lecture.addNote(note);
			noteMap.put(note.noteId, note);
		}
		return noteMap.get(note.noteId);
	}
	
	@GET
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Note getNote(@PathParam("noteId") String noteId) {
		return noteMap.get(noteId);
	}
	
	@PUT
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Note updateNote(Note note
			, @PathParam("lectureId") String lectureId
			, @PathParam("noteId") String noteId) {
		Note oldNote = noteMap.get(noteId);
		if(oldNote != null)
			oldNote.update(note);
		else {
			Lecture lecture = LectureResource.getLectureInstance(lectureId);
			lecture.addNote(note);
			noteMap.put(note.noteId, note);
		}
		return noteMap.get(noteId);
	}
	
	@DELETE
	@Path("{noteId}")
	public void deleteNote(@PathParam("lectureId") String lectureId
			, @PathParam("noteId") String noteId) {
		if(!noteMap.containsKey(noteId))
			return;
		
		Lecture lecture = LectureResource.getLectureInstance(lectureId);
		Note note = noteMap.get(noteId);
		lecture.removeNote(note);
		noteMap.remove(noteId);
	}
}
