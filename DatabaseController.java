import java.util.*;
import java.io.*;
import java.nio.file.*;

public class DatabaseController {
	private final String pool = "DataPool";

	public Database newDatabase(String name) throws DatabaseException {
		if (Files.exists(Paths.get(".", pool, name)))
			throw new DatabaseException("既に存在するデータベースです");

		try {
			Files.createDirectories(Paths.get(".", pool, name));
		} catch (IOException ioe) {
			throw new DatabaseException("DBP用ファイル作成に失敗");
		}

		return new Database(name);
	}

	public Database openDatabase(String name) throws DatabaseException {
		// ファイル開く
		// 文字列処理でTable,Recordあばばばば
		// テーブルをDBに突っ込む
		Database db = new Database(name);
		return db;
	}

	public List<String> getDatabaseNames() {
		// 
		List<String> dbnames = new ArrayList<>();
		dbnames.add("aaa");
		dbnames.add("bbb");
		dbnames.add("ccc");
		return dbnames;
	}
}

