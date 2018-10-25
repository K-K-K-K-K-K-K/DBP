import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

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
		Database db = new Database(name);
		try {
			Files.newBufferedReader(Paths.get(".", pool, name), Charset.forName("UTF-8"))
				.lines()
				.map(line -> line.split("::"))
				.forEach(System.out::println);
		} catch (IOException ioe) {
			throw new DatabaseException("DBP用ファイルの読込に失敗");
		}

		return db;
	}

	public List<String> getDatabaseNames() throws DatabaseException {
		List<String> dbNames = new ArrayList<>();

		try {
			Files.newDirectoryStream(Paths.get(".", pool)).forEach(path -> {
				dbNames.add(path.getFileName().toString());
			});
		} catch (IOException ioe) {
			throw new DatabaseException("DBP用ファイルの一覧取得に失敗");
		}

		return dbNames;
	}
}

