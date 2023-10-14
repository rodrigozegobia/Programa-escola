package com.ProjetoEscola.web.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ProjetoEscola.models.service.exception.EmailAlreadyExistsException;
import com.ProjetoEscola.models.service.exception.EntityNotFoundException;
import com.ProjetoEscola.models.service.exception.PasswordConfirmationException;
import com.ProjetoEscola.web.response.Fields;
import com.ProjetoEscola.web.response.SystemErrorMessage;

@RestControllerAdvice //Faz o monitoramento do que está sendo executado no sistema e pega erros
public class SystemExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(DataIntegrityViolationException.class) // Pega uma excessão desse tipo quando o sistema a lançar
	public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException except, WebRequest request){
		
		SystemErrorMessage sysError = new SystemErrorMessage
			.Builder()
			.addStatus(HttpStatus.BAD_REQUEST.value())
			.addMessage(except.getMessage())
			.addDescription("Violação de integridade, existem campos obrigatórios que não foram preenchidos.")
			.addError(true)
			.addDate(new Date())
			.build();
		
		return ResponseEntity.ok().body(sysError);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> entityNotFoundException(EntityNotFoundException except, WebRequest request){
		SystemErrorMessage sysError = new SystemErrorMessage();
		sysError.setStatus(HttpStatus.BAD_REQUEST.value());
		sysError.setMessage(except.getMessage());
		sysError.setDate(new Date());
		sysError.setError(true);
		sysError.setDescription("Não foi possível encontrar o ID especificado.");
		
		return ResponseEntity.ok().body(sysError);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException except, WebRequest request){
		SystemErrorMessage sysError = new SystemErrorMessage();
		sysError.setStatus(HttpStatus.BAD_REQUEST.value());
		sysError.setMessage(except.getMessage());
		sysError.setDate(new Date());
		sysError.setError(true);
		sysError.setDescription("O email informado já está cadastrado no sistema.");
		
		return ResponseEntity.ok().body(sysError);
	}
	
	@ExceptionHandler(PasswordConfirmationException.class)
	public ResponseEntity<?> passwordConfirmationException(PasswordConfirmationException except, WebRequest request){
		SystemErrorMessage sysError = new SystemErrorMessage();
		sysError.setStatus(HttpStatus.BAD_REQUEST.value());
		sysError.setMessage(except.getMessage());
		sysError.setDate(new Date());
		sysError.setError(true);
		sysError.setDescription("A senha de confirmação deve ser igual a senha informada.");
		
		return ResponseEntity.ok().body(sysError);
	}
	
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException except, WebRequest request){
		SystemErrorMessage sysError = new SystemErrorMessage();
		sysError.setStatus(HttpStatus.BAD_REQUEST.value());
		sysError.setMessage(except.getMessage());
		sysError.setDate(new Date());
		sysError.setError(true);
		sysError.setDescription("{Media Type} o tipo de dado enviado não é suportado");
		
		return ResponseEntity.ok().body(sysError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException except, WebRequest request){
		
		BindingResult bindingResult = except.getBindingResult(); //Lista com todos os erros;
		List<Fields> fields = new ArrayList<>();
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			
			Fields field = new Fields();
			field.setName(fieldError.getField());
			field.setClientMessage(message);
			fields.add(field);
		}
		
		SystemErrorMessage sysError = SystemErrorMessage
			.builder()
			.addStatus(HttpStatus.BAD_REQUEST.value())
			.addMessage(except.getMessage())
			.addDescription("Informe todos os dados solicitados no cadastro.")
			.addError(true)
			.addDate(new Date())
			.addFields(fields)
			.build();
		
		return ResponseEntity.ok().body(sysError);
	}

}
