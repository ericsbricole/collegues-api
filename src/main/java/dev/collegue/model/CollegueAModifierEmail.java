package dev.collegue.model;

public class CollegueAModifierEmail {

	private String matricule;
	private String email;
	
	public CollegueAModifierEmail() {
	}

	public CollegueAModifierEmail(String matricule, String email) {
		this.matricule = matricule;
		this.email = email;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
