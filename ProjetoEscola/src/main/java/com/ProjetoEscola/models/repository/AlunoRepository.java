package com.ProjetoEscola.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ProjetoEscola.models.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query("SELECT a FROM Professor a WHERE a.userName =:key")
	public List<Aluno> findByUserName(@Param("key") String key);

	@Query("SELECT a FROM Professor a WHERE a.email =:email")
	public Optional<Aluno> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT a FROM Professor a", countQuery = "SELECT COUNT(a) FROM Professor a")
	public Page<Aluno> findAllPagination(Pageable page);
	
	@Query(value = "SELECT a FROM Professor a WHERE a.id LIKE(CONCAT('%',:key,'%')) OR a.userName LIKE(CONCAT('%',:key,'%'))", countQuery = "SELECT COUNT(a) FROM Professor a")
	public Page<Aluno> findAllPaginationWithKey(@Param("key") String key, Pageable page);
	
}