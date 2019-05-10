package dev.collegue.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.collegue.model.Collegue;
import dev.collegue.model.CollegueNote;
import dev.collegue.repository.CollegueNoteRepository;
import dev.collegue.repository.CollegueRepository;
import exception.CollegueNonTrouveException;

@Service
public class CollegueNoteService {

	@Autowired
	private CollegueNoteRepository collegueNoteRepo;
	@Autowired
	private CollegueRepository collegueRepo;

	public CollegueNoteService(CollegueNoteRepository collegueNoteRepo, CollegueRepository collegueRepo) {
		this.collegueNoteRepo = collegueNoteRepo;
		this.collegueRepo = collegueRepo;
	}

	public CollegueNote saveNote(String matricule, String note) throws CollegueNonTrouveException {

		Collegue collegue = collegueRepo.findById(matricule).orElseThrow(() -> new CollegueNonTrouveException(
				"Aucun collegue ne correspond au matricule, impossible de sauvegarder une note"));
		CollegueNote collegueNote = new CollegueNote(collegue, note, LocalDateTime.now());
		collegueNoteRepo.save(collegueNote);
		return collegueNote;
	}

	public List<CollegueNote> rechercherNotes(String matricule) {
		List<CollegueNote> collegueNotes = collegueNoteRepo.findByCollegueMatricule(matricule);
		return collegueNotes;
	}

	public Collegue rechercherCollegueParEmail(String email) {
		return collegueRepo.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("Aucun collègue n'a été trouvé avec le mail " + email));
	}

}
