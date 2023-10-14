package com.ProjetoEscola.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoEscola.models.data.UserRequest;
import com.ProjetoEscola.models.data.UserResponse;
import com.ProjetoEscola.models.model.Aluno;
import com.ProjetoEscola.models.service.AlunoService;
import com.ProjetoEscola.web.response.SystemMessage;
import com.ProjetoEscola.web.swagger.AlunoRestControllerApi;

import jakarta.validation.Valid;

@RestController                //Só se configura o consumes e o produces aqui se tiver certeza que a classe vai utilizar o JSON, XML e etc
@RequestMapping(value="/rest/aluno") //Para colocar nos métodos, se coloca os mesmos parâmetros no GetMapping, PostMapping e etc.

public class AlunoRestController implements AlunoRestControllerApi {
	
	@Autowired
	private AlunoService alunoService;
	
	@Override
	@GetMapping(value="/list", 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }) //Verbo GET
	public List<UserResponse> list(){
		return alunoService.list();
	}

	@Override
	@GetMapping(value="/pageByKey",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Page<UserResponse> listPagedByKey(
			@RequestParam(value="key", defaultValue="", required=false) String key, 
			@RequestParam(value="offset", defaultValue="0", required=false) Integer actualPage, 
			@RequestParam(value="limit", defaultValue="10", required=false) Integer pageSize,
			@RequestParam(value="order", defaultValue="ASC", required=false) String order,
			@RequestParam(value="props", defaultValue="id", required=false) String props) {
		return alunoService.listPagedByKey(key, actualPage, pageSize, order, props);
	}
	
	@Override
	@GetMapping(value="/page",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Page<UserResponse> listPaged(
			@RequestParam(value="offset", defaultValue="0", required=false) Integer actualPage, 
			@RequestParam(value="limit", defaultValue="10", required=false) Integer pageSize,
			@RequestParam(value="order", defaultValue="ASC", required=false) String order,
			@RequestParam(value="props", defaultValue="id", required=false) String props) {
		return alunoService.listPaged(actualPage, pageSize, order, props);
	}

	@Override
	@GetMapping(value="/get/{id}",
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> searchUserById(@PathVariable("id") Long id) {
		UserResponse userResponse = alunoService.read(id);
		SystemMessage<UserResponse> userMessage = new SystemMessage<UserResponse>(HttpStatus.OK.value(), "Usuário lido com sucesso.", userResponse);
		
		return ResponseEntity.ok().body(userMessage);
	}

	@Override
	@PostMapping(value="/save",
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest) { //O RequestBody é o comando que lê o que chegou do http
		UserResponse userResponse = alunoService.save(userRequest);
		SystemMessage<UserResponse> userMessage = new SystemMessage<UserResponse>(HttpStatus.OK.value(), "Usuário cadastrado com sucesso.", userResponse);
		
		return ResponseEntity.ok().body(userMessage);
	}

	@Override
	@PutMapping(value="/update/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
		UserResponse updatedUser = alunoService.update(id, userRequest);
		SystemMessage<UserResponse> userMessage = new SystemMessage<UserResponse>(HttpStatus.OK.value(), "Usuário alterado com sucesso.", updatedUser);
		
		return ResponseEntity.ok().body(userMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		alunoService.delete(id);
		SystemMessage<Aluno> userMessage = new SystemMessage<Aluno>(HttpStatus.OK.value(), "Registro " + id + " excluído com sucesso.", null);
		
		return ResponseEntity.ok().body(userMessage);
	}
	
}
