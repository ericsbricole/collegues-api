package dev.collegue.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COLLEGUENOTE")
public class CollegueNote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Collegue collegue;
	@Column(name="NOTE")
	private String note;
	@Column(name="CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;

	public CollegueNote() {
	}

	public CollegueNote(Collegue collegue, String note, LocalDateTime creationDateTime) {
		this.collegue = collegue;
		this.note = note;
		this.creationDateTime = creationDateTime;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	

	
	
}
