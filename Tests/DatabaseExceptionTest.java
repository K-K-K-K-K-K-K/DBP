public class DatabaseExceptionTest {
	public static void main(String[] args) {
		try {
			throw new DatabaseException("試験");
		} catch (DatabaseException de) {
			System.out.println(de.getMessage());
		}
	}
}

