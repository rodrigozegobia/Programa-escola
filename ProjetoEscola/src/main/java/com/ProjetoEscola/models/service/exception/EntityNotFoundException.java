package com.ProjetoEscola.models.service.exception;

public class EntityNotFoundException extends BussinessException {

	private static final long serialVersionUID = -1469455524594842935L;

	public EntityNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
