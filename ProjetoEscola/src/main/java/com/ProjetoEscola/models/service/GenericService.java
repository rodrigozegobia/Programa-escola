package com.ProjetoEscola.models.service;

import java.util.List;

import org.springframework.data.domain.Page;

/*
 * O  - Output class DTO based
 * I  - Input class DTO based 
 */

public interface GenericService<O,I,ID> {
	
	O save(I entity);
	O update(ID id, I entity);
	void delete(ID id);
	O read(ID id);
	List<O> list();
	List<O> list(String key);
	Page<O> listPaged(Integer actualPage, Integer pageSize, String order, String props);
	Page<O> listPagedByKey(String key, Integer actualPage, Integer pageSize, String order, String props);
}