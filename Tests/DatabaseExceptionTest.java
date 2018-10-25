public class DatabaseExceptionTest {
	public static void main(String[] args) {
		String msg = "試験文章";
		try {
			throw new DatabaseException(msg);
		} catch (DatabaseException de) {
			if (de.getMessage().equals(msg))
				System.out.println("成功");
			else
				System.out.println("失敗");
		}
	}
}

