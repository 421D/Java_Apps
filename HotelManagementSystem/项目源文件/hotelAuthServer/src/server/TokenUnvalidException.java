package server;

public class TokenUnvalidException extends Exception{ //������֤�쳣��
	private static final long serialVersionUID = 1L;
	public String toString() {
		return "TokenUnvalid";
	}
}
