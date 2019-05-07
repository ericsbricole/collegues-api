package dev.collegue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.collegue.model.CollegueNote;

public interface CollegueNoteRepository extends JpaRepository<CollegueNote, Integer> {

	@Query("select n from CollegueNote n where n.collegue.matricule = :matricule")
	List<CollegueNote> findByCollegueMatricule(@Param("matricule") String matricule);

}
