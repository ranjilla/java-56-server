package project2.exceptions;

public class MissingPriceException extends Exception {
	private int errorno;
	private String errormsg;
	public MissingPriceException (String message) {
		
		super(message);
		this.errormsg = message;
	}
	
	public MissingPriceException(int errorno) {
		super();
		this.errorno = errorno;
		
	}
	public MissingPriceException(int errorno, String errormsg) {
		super();
		this.errorno = errorno;
		this.errormsg = errormsg;
		
	}
	public int getErrorno() {
		return errorno;
	}
	public void setErrorno(int errorno) {
		this.errorno = errorno;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
}