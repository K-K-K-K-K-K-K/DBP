import java.util.*;

public class Database {
	private List<Table> tbls = new ArrayList<>();

	public void addTable(Table tbl) {
		tbls.add(tbl);
	}

	public List<Table> getTables() {
		return tbls;
	}
}

