package com.ProjetoEscola.models.service.exception;

public class EmailAlreadyExistsException extends BussinessException {

	private static final long serialVersionUID = -350468774335783591L;

	public EmailAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
