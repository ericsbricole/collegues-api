package dev.collegue.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COLLEGUE")
public class Collegue {

	@Id
	@Column(name = "MATRICULE")
	private String matricule;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRENOMS")
	private String prenoms;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "DATEDENAISSANCE")
	private LocalDate dateDeNaissance;
	@Column(name = "PHOTOURL")
	private String photoUrl;
	@Column(name = "PASSWORD")
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "ROLES")
	private List<String> roles;

	public Collegue() {
	}

	public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance,
			String photoUrl) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}

	public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance,
			String photoUrl, String password, List<String> roles) {
		this(matricule, nom, prenoms, email, dateDeNaissance, photoUrl);
		this.password = password;
		this.roles = roles;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
