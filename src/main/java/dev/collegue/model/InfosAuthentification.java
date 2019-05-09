package dev.collegue.model;

public class InfosAuthentification {
	
	private String matricule;
	private String password;

	public InfosAuthentification() {
	}
	
	public InfosAuthentification(String matricule, String password) {
		this.matricule = matricule;
		this.password = password;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
