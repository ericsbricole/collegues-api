package dev.collegue.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.collegue.model.Collegue;
import dev.collegue.repository.CollegueRepository;
import exception.CollegueInvalideException;
import exception.CollegueNonTrouveException;

@Service
public class CollegueService {

	private static final byte NOM_MIN_LENGTH = 2;
	private static final byte PRENOM_MIN_LENGTH = 2;
	private static final byte EMAIL_MIN_LENGTH = 3;
	private static final byte AGE_MIN = 18;

	CollegueRepository collegueRepo;

	@Autowired
	public CollegueService(CollegueRepository collegueRepo) {
		this.collegueRepo = collegueRepo;
		List<Collegue> collegues = new ArrayList<>();
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Astier", "Alexandre", "astier@kaamelot.com",
				LocalDate.of(1980, 10, 11), "https://www.savemybrain.net/v2/wp-content/uploads/2012/04/arthur.jpg"));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Asterix", "Gaulois", "asterix@armorique.com",
				LocalDate.of(1960, 01, 01), "http://om1337.o.m.pic.centerblog.net/4w7h72p3.jpg"));
		collegues.add(new Collegue(UUID.randomUUID().toString(), "Dark", "Vador", "vador@blackStar.com",
				LocalDate.of(1960, 12, 05), "https://upload.wikimedia.org/wikipedia/en/thumb/7/76/Darth_Vader.jpg/220px-Darth_Vader.jpg"));
		collegueRepo.saveAll(collegues);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		return collegueRepo.findAll();
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		Optional<Collegue> optCollegue = collegueRepo.findById(matriculeRecherche);
		return optCollegue
				.orElseThrow(() -> new CollegueNonTrouveException("Aucun collègue n'a été trouvé avec ce matricule"));
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
		if (collegueAAjouter.getNom() == null || collegueAAjouter.getNom().length() < NOM_MIN_LENGTH
				|| collegueAAjouter.getPrenoms() == null || collegueAAjouter.getPrenoms().length() < PRENOM_MIN_LENGTH)
			throw new CollegueInvalideException("Le nom et les prénoms doivent avoir au moins 2 caractères");
		if (collegueAAjouter.getEmail() == null || collegueAAjouter.getEmail().length() < EMAIL_MIN_LENGTH
				|| StringUtils.containsNone(collegueAAjouter.getEmail(), "@"))
			throw new CollegueInvalideException("L'email doit avoir au moins 3 caractères et un \"@\"");
		if (collegueAAjouter.getPhotoUrl() == null || !StringUtils.startsWith(collegueAAjouter.getPhotoUrl(), "http"))
			throw new CollegueInvalideException("L'url de la photo doit commencer par http");
		if (collegueAAjouter.getDateDeNaissance() == null
				|| LocalDate.now().getYear() - collegueAAjouter.getDateDeNaissance().getYear() < AGE_MIN)
			throw new CollegueInvalideException("La personne doit avoir au moins " + AGE_MIN + " ans");
		String matriculeAAjouter = UUID.randomUUID().toString();
		collegueAAjouter.setMatricule(matriculeAAjouter);
		collegueRepo.save(collegueAAjouter);
		return collegueAAjouter;
	}

	@Transactional // même contexte transactionnel necessaire pour garder le
					// meme em, sinon collegueToModify est détaché, pas
					// persistant
	public Collegue modifierEmail(String matricule, String email)
			throws CollegueNonTrouveException, CollegueInvalideException {
		if (email == null || email.length() < EMAIL_MIN_LENGTH || StringUtils.containsNone(email, "@"))
			throw new CollegueInvalideException("L'email n'est pas conforme");
		Collegue collegueToModify = rechercherParMatricule(matricule);
		collegueToModify.setEmail(email);
		return collegueToModify;
	}

	@Transactional
	public Collegue modifierPhotoUrl(String matricule, String photoUrl)
			throws CollegueInvalideException, CollegueNonTrouveException {
		if (StringUtils.containsNone(photoUrl, "http"))
			throw new CollegueInvalideException("l'url de la photo doit commencer par http");
		Collegue collegueToModify = rechercherParMatricule(matricule);
		collegueToModify.setPhotoUrl(photoUrl);
		return collegueToModify;
	}

}
