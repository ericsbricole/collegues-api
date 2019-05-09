package dev.collegue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.collegue.model.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
	
	Optional<List<Collegue>> findByNom(String nom);
	Optional<Collegue> findByEmail(String email);
	
}
