import java.util.*;

public class Record {
	private int size;
	private List<String> fields = new ArrayList<>();

	public Record(int size) {
		this.size = size;
	}
		
	public Record addField(String str) throws DatabaseException {
		fields.add(str);
		
		if (fields.size() > size) 
			throw new DatabaseException("フィールド数がカラム数を超越 (" + fields.size() + " > " + size + ")");

		return this;
	}

	public List<String> getFields() {
		return fields;
	}
}

