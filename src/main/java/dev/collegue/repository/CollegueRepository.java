package dev.collegue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.collegue.model.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
	
}
