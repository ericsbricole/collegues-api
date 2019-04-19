package dev.collegue.model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class CollegueAModifierPhotoUrl {

	private String matricule;
	private String photoUrl;
	
	public CollegueAModifierPhotoUrl() {
	}

	public CollegueAModifierPhotoUrl(String matricule, String photoUrl) {
		this.matricule = matricule;
		this.photoUrl = photoUrl;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	

}
