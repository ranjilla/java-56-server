package project2.exceptions;

public class MissingModelException extends Exception {
	private int errorno;
	private String errormsg;
	public MissingModelException (String message) {
		
		super(message);
		this.errormsg = message;
	}
	
	public MissingModelException(int errorno) {
		super();
		this.errorno = errorno;
		
	}
	public MissingModelException(int errorno, String errormsg) {
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