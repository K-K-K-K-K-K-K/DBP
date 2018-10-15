import java.util.*;

public class Table {
	private List<String> clms = new ArrayList<>();
	private List<Record> recs = new ArrayList<>();

	public Table addColumn(String clm) {
		clms.add(clm);
		return this;
	}
	
	public List<String> getColumns() {
		return clms;
	}

	public int getColumnsLength() {
		return clms.size();
	}

	public void addRecord(Record rec) {
		recs.add(rec);
	}

	public List<Record> getRecords() {
		return recs;
	}
}

