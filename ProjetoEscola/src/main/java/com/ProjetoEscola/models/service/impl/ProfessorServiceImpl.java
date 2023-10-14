package com.ProjetoEscola.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ProjetoEscola.models.data.UserRequest;
import com.ProjetoEscola.models.data.UserResponse;
import com.ProjetoEscola.models.model.Professor;
import com.ProjetoEscola.models.repository.ProfessorRepository;
import com.ProjetoEscola.models.service.ProfessorService;
import com.ProjetoEscola.models.service.exception.EmailAlreadyExistsException;
import com.ProjetoEscola.models.service.exception.EntityNotFoundException;
import com.ProjetoEscola.models.service.exception.PasswordConfirmationException;
import com.ProjetoEscola.models.service.mapper.EntityConversor;
import com.ProjetoEscola.models.service.pagination.PageRequestConfig;

@Service
@Transactional //Escrever em HD -> unidirecional
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired //O spring instancia automaticamente
	private ProfessorRepository ProfessorRepository; //Não tem como instanciar normalmente pois é uma interface
	
	@Autowired
	private EntityConversor entityConversor;
	
	@Override
	public UserResponse save(UserRequest entity) {
		Professor professor = entityConversor.parseObject(entity, Professor.class);
		
		Optional<Professor> userSaved = ProfessorRepository.findByEmail(professor.getEmail());
		if(userSaved.isPresent() && userSaved.get().equals(professor)) {
			throw new EmailAlreadyExistsException("O email informado já está cadastrado");
		}
		if(!entity.getPassword().equals(entity.getRePassword())) {
			throw new PasswordConfirmationException("A senha de confirmação e a senha não conferem");
		}
		
		professor = ProfessorRepository.save(professor);
		UserResponse userResponse = entityConversor.parseObject(professor, UserResponse.class);
		
		return userResponse;
	}
	
	@Override
	public UserResponse update(Long id, UserRequest entity) {
		Professor professor = ProfessorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		Professor userConv = entityConversor.parseObject(entity, Professor.class);
		
		if(!entity.getPassword().equals(entity.getRePassword())) {
			throw new PasswordConfirmationException("A senha de confirmação e a senha não conferem");
		}
		
		professor.setRePassword(entity.getRePassword());
		professor.setEmail(entity.getEmail());
		professor.setPassword(entity.getPassword());
		professor.setUserName(entity.getUserName());
		
		professor = ProfessorRepository.saveAndFlush(professor); //Força o salvamento sem ficar em memória intermediária
		UserResponse userResponse = entityConversor.parseObject(userConv, UserResponse.class);
		
		return userResponse;
	}

	@Override
	public void delete(Long id) {
		ProfessorRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) //Desativa a escrita, não são necessárias transações
	public UserResponse read(Long id) {	
		Professor professor = ProfessorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		
		return entityConversor.parseObject(professor, UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> list() {
		return entityConversor.parseListObjects(ProfessorRepository.findAll(), UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> list(String key) {
		return entityConversor.parseListObjects(ProfessorRepository.findByUserName(key), UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserResponse> listPaged(Integer actualPage, Integer pageSize, String order, String props) {
		
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Professor> userList = ProfessorRepository.findAllPagination(page);
		List<UserResponse> userListResponse = entityConversor.parseListObjects(userList.getContent(), UserResponse.class);
		Page<UserResponse> pageUserResponse = new PageImpl<>(userListResponse, userList.getPageable(), userList.getTotalElements());
		
		return pageUserResponse;
	}

	@Override
	public Page<UserResponse> listPagedByKey(String key, Integer actualPage, Integer pageSize, String order,
			String props) {
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Professor> userList = ProfessorRepository.findAllPaginationWithKey(key, page);
		List<UserResponse> userListResponse = entityConversor.parseListObjects(userList.getContent(), UserResponse.class);
		Page<UserResponse> pageUserResponse = new PageImpl<>(userListResponse, userList.getPageable(), userList.getTotalElements());
		
		return pageUserResponse;
	}
	
}
