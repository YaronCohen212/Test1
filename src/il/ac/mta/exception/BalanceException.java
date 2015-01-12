package il.ac.mta.exception;

public class BalanceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public BalanceException() {
		super("Not enough balance to complete purchase!");
	}
}
