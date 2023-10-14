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
import com.ProjetoEscola.models.model.Aluno;
import com.ProjetoEscola.models.repository.AlunoRepository;
import com.ProjetoEscola.models.service.AlunoService;
import com.ProjetoEscola.models.service.exception.EmailAlreadyExistsException;
import com.ProjetoEscola.models.service.exception.EntityNotFoundException;
import com.ProjetoEscola.models.service.exception.PasswordConfirmationException;
import com.ProjetoEscola.models.service.mapper.EntityConversor;
import com.ProjetoEscola.models.service.pagination.PageRequestConfig;

@Service
@Transactional //Escrever em HD -> unidirecional
public class AlunoServiceImpl implements AlunoService {

	@Autowired //O spring instancia automaticamente
	private AlunoRepository AlunoRepository; //Não tem como instanciar normalmente pois é uma interface
	
	@Autowired
	private EntityConversor entityConversor;
	
	@Override
	public UserResponse save(UserRequest entity) {
		Aluno aluno = entityConversor.parseObject(entity, Aluno.class);
		
		Optional<Aluno> userSaved = AlunoRepository.findByEmail(aluno.getEmail());
		if(userSaved.isPresent() && userSaved.get().equals(aluno)) {
			throw new EmailAlreadyExistsException("O email informado já está cadastrado");
		}
		if(!entity.getPassword().equals(entity.getRePassword())) {
			throw new PasswordConfirmationException("A senha de confirmação e a senha não conferem");
		}
		
		aluno = AlunoRepository.save(aluno);
		UserResponse userResponse = entityConversor.parseObject(aluno, UserResponse.class);
		
		return userResponse;
	}
	
	@Override
	public UserResponse update(Long id, UserRequest entity) {
		Aluno aluno = AlunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		Aluno userConv = entityConversor.parseObject(entity, Aluno.class);
		
		if(!entity.getPassword().equals(entity.getRePassword())) {
			throw new PasswordConfirmationException("A senha de confirmação e a senha não conferem");
		}
		
		aluno.setRePassword(entity.getRePassword());
		aluno.setEmail(entity.getEmail());
		aluno.setPassword(entity.getPassword());
		aluno.setUserName(entity.getUserName());
		
		aluno = AlunoRepository.saveAndFlush(aluno); //Força o salvamento sem ficar em memória intermediária
		UserResponse userResponse = entityConversor.parseObject(userConv, UserResponse.class);
		
		return userResponse;
	}

	@Override
	public void delete(Long id) {
		AlunoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true) //Desativa a escrita, não são necessárias transações
	public UserResponse read(Long id) {	
		Aluno aluno = AlunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		
		return entityConversor.parseObject(aluno, UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> list() {
		return entityConversor.parseListObjects(AlunoRepository.findAll(), UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> list(String key) {
		return entityConversor.parseListObjects(AlunoRepository.findByUserName(key), UserResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserResponse> listPaged(Integer actualPage, Integer pageSize, String order, String props) {
		
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Aluno> userList = AlunoRepository.findAllPagination(page);
		List<UserResponse> userListResponse = entityConversor.parseListObjects(userList.getContent(), UserResponse.class);
		Page<UserResponse> pageUserResponse = new PageImpl<>(userListResponse, userList.getPageable(), userList.getTotalElements());
		
		return pageUserResponse;
	}

	@Override
	public Page<UserResponse> listPagedByKey(String key, Integer actualPage, Integer pageSize, String order,
			String props) {
		Pageable page = PageRequestConfig.generatePage(actualPage, pageSize, order, props);
		
		Page<Aluno> userList = AlunoRepository.findAllPaginationWithKey(key, page);
		List<UserResponse> userListResponse = entityConversor.parseListObjects(userList.getContent(), UserResponse.class);
		Page<UserResponse> pageUserResponse = new PageImpl<>(userListResponse, userList.getPageable(), userList.getTotalElements());
		
		return pageUserResponse;
	}
	
}
