package dev.collegue.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

}
