package com.ProjetoEscola.models.service.exception;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = -6731019793876859379L;

	public BussinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
