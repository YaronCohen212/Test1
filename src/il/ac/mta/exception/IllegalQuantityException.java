package il.ac.mta.exception;

public class IllegalQuantityException extends Exception{

	private static final long serialVersionUID = 1L;

	public IllegalQuantityException() {
		super("Quantity cannot be less then -1");
	}
}

