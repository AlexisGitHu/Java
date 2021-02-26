package POOPracticaFinal;

public class ArgMissingException extends Exception{
	public ArgMissingException() {
		super();
	}
	public ArgMissingException(String errorMessage) {
		super(errorMessage);
	}
}