package dev.collegue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.collegue.model.Collegue;
import exception.CollegueNonTrouveException;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		String matricule = UUID.randomUUID().toString();
		data.put(matricule, new Collegue(matricule, "Astier", "Alexandre", "astier@kaamelot.com", "10-01-2017", null));
		String matricule2 = UUID.randomUUID().toString();
		data.put(matricule2,
				new Collegue(matricule2, "Dark", "Vador", "vador@blackstar.com", "10-01-1997", "blackStar.jpeg"));
		String matricule3 = UUID.randomUUID().toString();
		data.put(matricule3, new Collegue(matricule3, "Rossi", "Oddet", "rossi@diginamic.com", "10-01-1987", null));
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

}
