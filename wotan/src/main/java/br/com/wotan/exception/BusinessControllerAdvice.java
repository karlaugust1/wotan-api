package br.com.wotan.exception;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.wotan.data.enun.ExceptionType;

@ControllerAdvice
public class BusinessControllerAdvice extends ResponseEntityExceptionHandler {

	private SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
	private String date = dateFormater.format(new Date());

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<BusinessResponse> handleAllExceptions(BusinessException ex, WebRequest request) {
		BusinessResponse detalhes = new BusinessResponse(ex.getExceptionType(), ex.getMessage(), ex.getDetails(), date);
		return new ResponseEntity<>(detalhes, HttpStatus.INTERNAL_SERVER_ERROR);
	}

/*	@ExceptionHandler(DatabaseException.class)
	public final ResponseEntity<BusinessResponse> handleAllExceptions(DatabaseException ex, WebRequest request) {
		BusinessResponse detalhes = new BusinessResponse(ex.getExceptionType(), ex.getMessage(), ex.getMsgException(),
				date);
		return new ResponseEntity<>(detalhes, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<BusinessResponse> handleConflict(Exception e, HttpServletResponse response)
			throws IOException {
		BusinessResponse detalhes = new BusinessResponse(ExceptionType.ERROR, e.getMessage(), "", date);
		return new ResponseEntity<>(detalhes, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BusinessResponse detalhes = new BusinessResponse(ExceptionType.ERROR, "Ocorreu interno no sistema! Por favor entre em contato", "", date);
		return new ResponseEntity<>(detalhes, HttpStatus.BAD_REQUEST);
	}

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fields = ex.getBindingResult().getFieldErrors();
		
		StringBuilder builderError = new StringBuilder();
		fields.forEach(field ->{
			builderError.append(field.getDefaultMessage()+"<br>");
		});
		
        BusinessResponse detalhes = new BusinessResponse(ExceptionType.ERROR, builderError.toString(), "", date);
		return new ResponseEntity<>(detalhes, HttpStatus.BAD_REQUEST);
    }
}
