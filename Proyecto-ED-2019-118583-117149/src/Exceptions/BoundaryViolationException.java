package Exceptions;

/**
 * Clase BoundaryViolationException.
 * @author Juan Igancio Rapino y Matias Ilz..
 */
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception{
	
	/**
	 * 
	 * @param msg Mensaje de error.
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}

}