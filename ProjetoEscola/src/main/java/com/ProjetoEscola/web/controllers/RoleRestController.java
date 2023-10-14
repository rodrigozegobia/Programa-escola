package com.ProjetoEscola.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.ProjetoEscola.models.model.Role;
import com.ProjetoEscola.models.service.RoleService;
import com.ProjetoEscola.web.response.SystemMessage;
import com.ProjetoEscola.web.swagger.RoleRestControllerApi;

@RestController
@RequestMapping(value="/rest/role")
public class RoleRestController implements RoleRestControllerApi {

	@Autowired
	private RoleService roleService;
	
	@Override
	@GetMapping("/list")
	public List<Role> list() {
		return roleService.list();
	}

	@Override
	@GetMapping(value="/page")
	public Page<Role> listPaged(@RequestParam(value="offset", defaultValue="0", required=false) Integer actualPage, 
			@RequestParam(value="limit", defaultValue="10", required=false) Integer pageSize,
			@RequestParam(value="order", defaultValue="ASC", required=false) String order,
			@RequestParam(value="props", defaultValue="id", required=false) String props) {
		// TODO Auto-generated method stub
		return roleService.listPaged(actualPage, pageSize, order, props);
	}

	@Override
	@GetMapping(value="/pageByKey")
	public Page<Role> listPagedByKey(
			@RequestParam(value="key", defaultValue="", required=false) String key,
			@RequestParam(value="offset", defaultValue="0", required=false) Integer actualPage, 
			@RequestParam(value="limit", defaultValue="10", required=false) Integer pageSize,
			@RequestParam(value="order", defaultValue="ASC", required=false) String order,
			@RequestParam(value="props", defaultValue="id", required=false) String props) {
		
		return roleService.listPagedByKey(key, actualPage, pageSize, order, props);
	}

	@Override
	@GetMapping(value="/get/{id}")
	public ResponseEntity<?> searchRoleById(@PathVariable("id") Long id) {
		Role role = roleService.read(id);
		SystemMessage<Role> roleMessage = new SystemMessage<Role>(HttpStatus.OK.value(), "Cargo lido com sucesso.", role);
		
		return ResponseEntity.ok().body(roleMessage);
	}

	@Override
	@PostMapping(value="/save")
	public ResponseEntity<?> saveRole(@RequestBody Role role) {
		roleService.save(role);
		SystemMessage<Role> roleMessage = new SystemMessage<Role>(HttpStatus.OK.value(), "Cargo salvo com sucesso.", role);
		
		return ResponseEntity.ok().body(roleMessage);
	}

	@Override
	@PutMapping(value="/update/{id}")
	public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
		Role updatedRole = roleService.update(id, role);
		SystemMessage<Role> roleMessage = new SystemMessage<Role>(HttpStatus.OK.value(), "Cargo alterado com sucesso.", updatedRole);
		
		return ResponseEntity.ok().body(roleMessage);
	}

	@Override
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
		roleService.delete(id);
		SystemMessage<Role> roleMessage = new SystemMessage<Role>(HttpStatus.OK.value(), "Registro " + id + " excluído com sucesso.", null);
		
		return ResponseEntity.ok().body(roleMessage);
	}	
}
