package ma.co.omnidata.framework.attachment.services.exceptions;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String code;

	public NotFoundException(String code, String errorMessage) {
		super(errorMessage);
		this.code = code;
	}

	public NotFoundException() {
		super();
	}

	public String getCode() {
		return code;
	}

}
