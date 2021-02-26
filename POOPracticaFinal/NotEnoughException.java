package POOPracticaFinal;

public class NotEnoughException extends Exception{
	public NotEnoughException() {
		super();
	}
	public NotEnoughException(String errorMessage) {
		super(errorMessage);
	}
}