import java.util.*;

public class Record {
	private int size;
	private List<String> list = new ArrayList<>();

	public Record(int size) {
		this.size = size;
	}

	public Record add(String str) {
		list.add(str);
		return this;
	}
}

