package com.ProjetoEscola.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ProjetoEscola.models.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	@Query("SELECT p FROM Professor p WHERE p.userName =:key")
	public List<Professor> findByUserName(@Param("key") String key);

	@Query("SELECT p FROM Professor p WHERE p.email =:email")
	public Optional<Professor> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT p FROM Professor p", countQuery = "SELECT COUNT(p) FROM Professor p")
	public Page<Professor> findAllPagination(Pageable page);
	
	@Query(value = "SELECT p FROM Professor p WHERE p.id LIKE(CONCAT('%',:key,'%')) OR p.userName LIKE(CONCAT('%',:key,'%'))", countQuery = "SELECT COUNT(p) FROM Professor p")
	public Page<Professor> findAllPaginationWithKey(@Param("key") String key, Pageable page);
	
}
