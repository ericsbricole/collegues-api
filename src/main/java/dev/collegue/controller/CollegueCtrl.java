package dev.collegue.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.collegue.model.Collegue;
import dev.collegue.model.CollegueAModifierEmail;
import dev.collegue.model.CollegueAModifierPhotoUrl;
import dev.collegue.service.CollegueService;
import exception.CollegueInvalideException;
import exception.CollegueNonTrouveException;

@RestController
@CrossOrigin
@RequestMapping("/collegues")
public class CollegueCtrl {

	@Autowired
	private CollegueService service;

	@GetMapping
	public List<String> rechercherParNom(@RequestParam String nom) {
		if (StringUtils.isEmpty(nom))
			return new ArrayList<>();
		else {
			return service.rechercherParNom(nom).stream().filter(c -> c.getNom().equals(nom))
					.map(Collegue::getMatricule).collect(Collectors.toList());
		}
	}

	@GetMapping(value = "/{matriculeRecherche}")
	public ResponseEntity<Collegue> rechercherParMatricule(@PathVariable String matriculeRecherche)
			throws CollegueNonTrouveException {
		Collegue collegueFound = service.rechercherParMatricule(matriculeRecherche);
		return ResponseEntity.status(HttpStatus.OK).body(collegueFound);
	}

	@ExceptionHandler(value = { CollegueNonTrouveException.class })
	public ResponseEntity<String> handleCollegueNotFound() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collegue non trouvé");
	}

	@PostMapping
	public ResponseEntity<Collegue> ajouterUnCollegue(@RequestBody Collegue collegueAAjouter) throws CollegueInvalideException {
		Collegue collegueAjoute = service.ajouterUnCollegue(collegueAAjouter);
		return ResponseEntity.status(HttpStatus.OK).body(collegueAjoute);
	}

	@PatchMapping(value = "/modifyEmail")
	public ResponseEntity<Collegue> modifierEmail(@RequestBody CollegueAModifierEmail collegueAModif)
			throws CollegueNonTrouveException, CollegueInvalideException {
		String newEmail = collegueAModif.getEmail();
		Collegue modifiedCollegue = service.modifierEmail(collegueAModif.getMatricule(), newEmail);
		return ResponseEntity.status(HttpStatus.OK).body(modifiedCollegue);
	}

	@PatchMapping(value = "/modifyPhotoUrl")
	public ResponseEntity<Collegue> modifierPhotoUrl(@RequestBody CollegueAModifierPhotoUrl collegueAModif)
			throws CollegueNonTrouveException, CollegueInvalideException {
		String newPhotoUrl = collegueAModif.getPhotoUrl();
		Collegue modifiedCollegue = service.modifierPhotoUrl(collegueAModif.getMatricule(), newPhotoUrl);
		return ResponseEntity.status(HttpStatus.OK).body(modifiedCollegue);
	}

	@ExceptionHandler(value = { CollegueInvalideException.class })
	public ResponseEntity<String> handleInvalidCollegue() {
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Le collègue à ajouter n'est pas valide.");
	}
}
