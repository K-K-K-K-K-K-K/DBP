public class DatabaseException extends Exception {
	private String message;

	public DatabaseException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

