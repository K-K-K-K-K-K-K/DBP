import java.util.*;

public class Record {
	private List<String> fields = new ArrayList<>();

	public Record(List<String> fields) {
		this.fields = fields;
	}

	public Record addField(String str) {
		fields.add(str);
		return this;
	}
}

