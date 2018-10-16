

public class DatabaseController {

	public Database newDatabase(String name) throws DatabaseException {
		return new Database();
	}

	public Database openDatabase(String name) throws DatabaseException {
		return new Database();
	}

	public String[] getDatabaseNames() {
		String[] names = {"a", "b", "c"};
		return names;
	}
}

