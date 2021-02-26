package POOPracticaFinal;

public class ErrorActionException extends Exception
{ //excepción que se lanza cuando la acción de un personaje es irrealizable
	public ErrorActionException() {
		super();
	}
	public ErrorActionException(String errorMessage) {
		super(errorMessage);
	}
}