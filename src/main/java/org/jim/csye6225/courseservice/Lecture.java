package org.jim.csye6225.courseservice;

import java.util.List;

public class Lecture {
	public String lectureId;
	public String topic;
	public List<Note> notes;
	
	public void addNote(Note note) {
		notes.add(note);
	}
}
