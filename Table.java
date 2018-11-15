import java.util.*;

public class Table {
	private String name;
	private int size;
	private List<String> columns = new ArrayList<>();
	private List<Record> records = new ArrayList<>();

	public Table(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	// カラム追加
	public Table addColumn(String column) throws DatabaseException {
		if (columns.size() + 1 > size) 
			throw new DatabaseException("カラム数が上限を超越 (" + (columns.size() + 1) + " > " + size + ")");

		columns.add(column);
		return this;
	}
	
	// 全カラム取得
	public List<String> getColumns() {
		return columns;
	}

	// カラム位置取得
	private int indexOfColumn(String column) throws DatabaseException {
		int index = columns.indexOf(column);
		
		if (index == -1)
			throw new DatabaseException("存在しないカラムが指定されました");
		
		return index;
	}

	// カラム変更
	public void changeColumn(String oldColumn, String newColumn) throws DatabaseException {
		columns.set(indexOfColumn(oldColumn), newColumn);
	}

	// レーコードクラスのインスタンス取得
	public Record getRecordInstance() {
		return new Record(size);
	}

	// レコード追加
	public Table addRecord(Record rec) throws DatabaseException {
		if (rec.getFields().size() != size)
			throw new DatabaseException("レコードのサイズが不正です");

		records.add(rec);
		return this;
	}

	// レコード位置取得
	private int indexOfRecord(Record record) throws DatabaseException {
		int index = records.indexOf(record);
		
		if (index == -1)
			throw new DatabaseException("存在しないレコードが指定されました");
		
		return index;
	}

	// レコード変更
	public void changeRecord(Record oldRecord, Record newRecord) throws DatabaseException {
		records.set(indexOfRecord(oldRecord), newRecord);
	}

	// レコード削除
	public void removeRecord(int index) throws DatabaseException {
		if (index >= records.size())
			throw new DatabaseException("存在しないレコードが指定されました");

		records.remove(index);
	}

	// 全レコード取得
	public List<Record> getRecords() {
		return records;
	}
}

