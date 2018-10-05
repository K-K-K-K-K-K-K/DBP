import java.util.*;

public class Table {
	private List<String> clms = new ArrayList<>();
	private List<Record> recs = new ArrayList<>();

	public void addColumn(String clm) {
		clms.add(clm);
	}

	public void addRecord(Record rec) {
		recs.add(rec);
	}
}

