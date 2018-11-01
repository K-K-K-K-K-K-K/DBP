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
		if (!Files.exists(Paths.get(".", pool, name)))
			throw new DatabaseException("存在しないデータベースです");

		Database db = new Database(name);
		try {
			// テーブル読込
			Files.newDirectoryStream(Paths.get(".", pool, name)).forEach(path -> {
				try {
					List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
				
					// カラム読込
					String[] clms = lines.get(0).split("::");
					Table tbl = new Table(path.getFileName().toString(), clms.length);
					Arrays.stream(clms).forEach(clm -> {
						try {
							tbl.addColumn(clm);
						} catch (DatabaseException de) {
							throw new RuntimeException();
						}
					});

					// レコード読込
					for (int i = 1; i < lines.size() - 1; i++) {
						Record rec = tbl.getRecordInstance();
						Arrays.stream(lines.get(i).split("::")).forEach(field -> {
							try {
								rec.addField(field);
							} catch (DatabaseException de) {
								throw new RuntimeException();
							}
						});
						tbl.addRecord(rec);
					}
	
					db.addTable(tbl);
				} catch (Exception e) {
						throw new RuntimeException();
				}
			});
		} catch (Exception e) {
			throw new DatabaseException("DBP用ファイルの読込に失敗");
		}

		return db;
	}

	public void saveDatabase(Database db) throws DatabaseException {
		try {
			db.getTables().stream().forEach(tbl -> {
				try {
					BufferedWriter bw = Files.newBufferedWriter(Paths.get(".", pool, db.getName(), tbl.getName()), StandardCharsets.UTF_8);
					for (int i = 0; i < tbl.getSize(); i++) {
						String clm = tbl.getColumns().get(i);
						bw.write(clm, 0, clm.length());
	
						if (i != tbl.getSize() - 1)
							bw.write("::", 0, 2);
					}
					bw.newLine();

					for (int i = 0; i < tbl.getRecords().size(); i++) {
						tbl.getRecords().get(i).getFields().stream().forEach(
						System.out::println);
					}

/*					for (int i = 0; i < tbl.getRecords().size(); i++) {
						Record rec = tbl.getRecords().get(i);
						for (int j = 0; i < rec.getSize() + 1; i++) {// + 1
							String field = rec.getFields().get(i);
							bw.write(field, 0, field.length());
				
							if (j != rec.getSize() - 1)
								bw.write("::", 0, 2);
						}
						bw.newLine();
					}
	*/
					bw.close();
				} catch (Exception e) {
					throw new RuntimeException();
				}	
			});
		} catch (Exception e) {
			throw new DatabaseException("DBP用ファイルへの書出に失敗");
		}
	}

	public void removeDatabase(String dbName) throws DatabaseException {
		Path root = Paths.get(".", pool, dbName);
		try {
			Files.walk(root, FileVisitOption.FOLLOW_LINKS)
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.forEach(File::delete);

			root.toFile().delete();
		} catch (IOException ioe) {
			throw new DatabaseException("DBP用ファイル及ディレクトリの削除に失敗");
		}
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

