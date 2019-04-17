package dev.collegue.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.model.Collegue;
import dev.collegue.service.CollegueService;

@RestController
@RequestMapping("/collegues")
public class CollegueCtrl {

	private CollegueService service = new CollegueService();
	
	@GetMapping
	public List<String> rechercherParNom(@RequestParam String nom) {
		if (StringUtils.isEmpty(nom))
			return null;
		else {
			List<String> matricules = new ArrayList<>();
			List<Collegue> collegues =  service.rechercherParNom(nom);
			for (Collegue c : collegues){
				matricules.add(c.getMatricule());
			}
			return matricules;
		}
	}

}
