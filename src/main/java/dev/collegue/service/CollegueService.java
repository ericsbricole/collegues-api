package dev.collegue.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import dev.collegue.model.Collegue;
import exception.CollegueInvalideException;
import exception.CollegueNonTrouveException;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		String matricule = UUID.randomUUID().toString();
		data.put(matricule, new Collegue(matricule, "Astier", "Alexandre", "astier@kaamelot.com", LocalDate.of(1980, 01, 01), "http:/kaamelot.com"));
		String matricule2 = UUID.randomUUID().toString();
		data.put(matricule2,
				new Collegue(matricule2, "Dark", "Vador", "vador@blackstar.com", LocalDate.of(1970, 02, 02), "http://blackStar.jpeg"));
		String matricule3 = UUID.randomUUID().toString();
		data.put(matricule3, new Collegue(matricule3, "Rossi", "Oddet", "rossi@diginamic.com", LocalDate.of(1990, 03, 03), "http://diginamic.com"));
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		return data.values().stream().filter( c -> c.getNom().equals(nomRecherche)).collect(Collectors.toList());
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		Collegue collegue = data.get(matriculeRecherche);
		if (collegue == null)
			throw new CollegueNonTrouveException("Pas de collegue trouvé avec le matricule " + matriculeRecherche);
		else
			return collegue;
	}
	
	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
		if (collegueAAjouter.getNom() == null || collegueAAjouter.getNom().length() < 2 || collegueAAjouter.getPrenoms() == null || collegueAAjouter.getPrenoms().length() < 2 )
			throw new CollegueInvalideException("Le nom et les prénoms doivent avoir au moins 2 caractères");
		if (collegueAAjouter.getEmail() == null || collegueAAjouter.getEmail().length() < 3 || StringUtils.containsNone(collegueAAjouter.getEmail(), "@"))
			throw new CollegueInvalideException("L'email doit avoir au moins 3 caractères et un \"@\"");
		if ( collegueAAjouter.getPhotoUrl() == null || ! StringUtils.startsWith(collegueAAjouter.getPhotoUrl(), "http") )
			throw new CollegueInvalideException("L'url de la photo doit commencer par http");
		if ( collegueAAjouter.getDateDeNaissance() == null || LocalDate.now().getYear() - collegueAAjouter.getDateDeNaissance().getYear() < 18 )
			throw new CollegueInvalideException("La personne doit avoir au moins 18 ans");
		
		String matriculeAAjouter = UUID.randomUUID().toString();
		collegueAAjouter.setMatricule(matriculeAAjouter);
		data.put(matriculeAAjouter, collegueAAjouter);
		return collegueAAjouter;
	}

}
