package com.ProjetoEscola.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ProjetoEscola.models.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r =:key")
	List<Role> findAllByName(@Param("key") String key);
	
	@Query(value = "SELECT r FROM Role r", countQuery = "SELECT COUNT(r) FROM Role r")
	public Page<Role> findAllPagination(Pageable page);
	
	@Query(value = "SELECT r FROM Role r WHERE r.id LIKE(CONCAT('%',:key,'%')) OR r.name LIKE(CONCAT('%',:key,'%')) ", countQuery = "SELECT COUNT(r) FROM Role r")
	public Page<Role> findAllPaginationWithKey(@Param("key") String key, Pageable page);
}
