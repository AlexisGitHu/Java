package POOPracticaFinal;

public class ErrorActionException extends Exception
{ //excepci�n que se lanza cuando la acci�n de un personaje es irrealizable
	public ErrorActionException() {
		super();
	}
	public ErrorActionException(String errorMessage) {
		super(errorMessage);
	}
}