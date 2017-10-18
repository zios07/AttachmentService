package ma.co.omnidata.framework.attachment.controllers.handlers;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ma.co.omnidata.framework.attachment.services.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseError handleNotFoundException(NotFoundException ex) {
		return new ResponseError(ex.getCode(), ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleAllException(Exception ex) {
		return new ResponseError("An exception occured" , ex.getMessage());
	}
	
	@ExceptionHandler(DataAccessResourceFailureException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseError handleMongoSocketOpenException(DataAccessResourceFailureException ex) {
		return new ResponseError("ERROR.DATABASE.MONGO" , "Cannot connect to MongoDB");
	}

	
	static class ResponseError {
		private String code;
		private String message;
		
		public ResponseError(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		public String getCode() {
			return this.code;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
}
