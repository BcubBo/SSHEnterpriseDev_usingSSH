package exception;

public class JboaException extends RuntimeException {


	private static final long serialVersionUID = 4202976819258540179L;

	//继承父类的RuntimeException运行时异常
	public JboaException() {
		super();
	}
	
	public JboaException(String msg) {
		super(msg);
	}
	
	public JboaException(Throwable e) {
		super(e);
	}
	
	public JboaException(String msg, Throwable e) {
		super(msg, e);
	}
}
