package dev.collegue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.collegue.model.CollegueNote;

@Repository
public interface CollegueNoteRepository extends JpaRepository<CollegueNote, Integer> {

	@Query("select n from CollegueNote n where n.collegue.matricule = :matricule")
	List<CollegueNote> findByCollegueMatricule(@Param("matricule") String matricule);

}
