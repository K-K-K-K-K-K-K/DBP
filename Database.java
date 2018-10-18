import java.util.*;

public class Database {
	private String name;
	private List<Table> tables = new ArrayList<>();

	public Database(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Database addTable(Table table) {
		tables.add(table);
		return this;
	}

	public void removeTable(Table table) throws DatabaseException {
		if (!tables.contains(table))
			throw new DatabaseException("存在しないテーブルが指定されました");

		tables.remove(table);
	}

	public List<Table> getTables() {
		return tables;
	}
}

