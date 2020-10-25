package com.restjwtauth.exceptions;

/**
 * Assim como é necessário criar uma nova exception, faz-se necessário também
 * criar o bean que será retornado pelo handler.
 * 
 * @author Ícaro
 *
 */
public class ExceptionResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionResponse(String message) {
		super();
		this.message = message;
	}
}
