package org.jim.csye6225.courseservice;

import java.util.List;

public class Lecture {
	public String lectureId;
	public String topic;
	public List<Note> notes;
	
	public void update(Lecture lecture) {
		this.topic = lecture.topic;
		this.notes = lecture.notes;
	}
	
	public void addNote(Note note) {
		if(!notes.contains(note))
			notes.add(note);
	}
	
	public void removeNote(Note note) {
		if(notes.contains(note))
			notes.remove(note);
	} 
}
