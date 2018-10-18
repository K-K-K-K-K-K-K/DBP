import java.util.*;

public class Database {
	private List<Table> tables = new ArrayList<>();

	public void addTable(Table table) {
		tables.add(table);
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

