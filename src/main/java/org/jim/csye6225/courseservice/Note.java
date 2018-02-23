package org.jim.csye6225.courseservice;

public class Note {
	public String noteId;
	public String content;
	
	public Note() {}
	
	public void update(Note note) {
		this.content = note.content;
	}
}
