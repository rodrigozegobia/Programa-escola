package com.ProjetoEscola.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoEscola.models.model.Role;
import com.ProjetoEscola.models.repository.RoleRepository;
import com.ProjetoEscola.models.service.RoleService;
import com.ProjetoEscola.models.service.pagination.PageRequestConfig;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role save(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public Role update(Long id, Role entity) {
		Role role = read(id);
		role.setName(entity.getName());
		
		return role;
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Role read(Long id) {
		return roleRepository.findById(id).get();	
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> list() {
		return roleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> list(String key) {
		return roleRepository.findAllByName(key);
	}

	@Override
	public Page<Role> listPaged(Integer actualPage, Integer pageSize, String order, String props) {
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Role> roleList = roleRepository.findAllPagination(page);
		
		return roleList;
	}

	@Override
	public Page<Role> listPagedByKey(String key, Integer actualPage, Integer pageSize, String order, String props) {
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Role> roleList = roleRepository.findAllPaginationWithKey(key, page);
		
		return roleList;
	}

}
