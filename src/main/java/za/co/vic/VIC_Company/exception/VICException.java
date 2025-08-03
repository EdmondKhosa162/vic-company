package za.co.vic.VIC_Company.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class VICException {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	
	public VICException(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}
}
