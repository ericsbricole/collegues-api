package dev.collegue.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.collegue.model.Collegue;

public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();
	
	public CollegueService() {
		String matricule = UUID.randomUUID().toString();
		data.put(matricule, new Collegue(matricule, "Astier", "Alexandre", "astier@kaamelot.com", "10-01-2017", null));
		String matricule2 = UUID.randomUUID().toString();
		data.put(matricule2, new Collegue(matricule2, "Dark", "Vador", "vador@blackstar.com", "10-01-1997", "blackStar.jpeg"));
		String matricule3 = UUID.randomUUID().toString();
		data.put(matricule2, new Collegue(matricule2, "Rossi", "Oddet", "rossi@diginamic.com", "10-01-1987", null));
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> colleguesFound = new ArrayList<>();
		for (Collegue c : data.values()) {
			if (c.getNom().equals(nomRecherche))
				colleguesFound.add(c);
		}
		return colleguesFound;
	}
	
}
