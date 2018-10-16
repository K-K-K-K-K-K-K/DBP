import java.util.*;

public class Record {
	private int size;
	private List<String> fields = new ArrayList<>();

	public Record(int size) {
		this.size = size;
	}

	// フィールド追加
	public Record addField(String str) throws DatabaseException {
		if (fields.size() + 1 > size) 
			throw new DatabaseException("フィールド数がカラム数を超越 (" + (fields.size() + 1) + " > " + size + ")");

		fields.add(str);
		return this;
	}

	// フィールド位置取得
	private int indexOf(String field) throws DatabaseException {
		int index = fields.indexOf(field);

		if (index == -1)
			throw new DatabaseException("存在しないフィールドが指定されました");

		return index;
	}

	// フィールド変更
	public void changeField(String oldField, String newField) throws DatabaseException {
		fields.set(indexOf(oldField), newField);
	}

	// 全フィールド取得
	public List<String> getFields() {
		return fields;
	}
}

