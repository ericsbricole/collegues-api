package dev.collegue.model;

public class PhotoCollegue {

	private String matricule;
	private String photoUrl;	
	
	public PhotoCollegue() {
	}
	
	public PhotoCollegue(String matricule, String photoUrl) {
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
