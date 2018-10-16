import java.util.*;

public class Database {
	private List<Table> tables = new ArrayList<>();

	public void addTable(Table table) {
		tables.add(table);
	}

	public void removeTable(Table table) {
	}

	public List<Table> getTables() {
		return tables;
	}
}

