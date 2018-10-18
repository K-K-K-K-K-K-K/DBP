import java.util.*;

public class DatabaseTest {
	public static void main(String[] args) {
		Table table = new Table("名簿", 3);

		/* カラム登録 --------------------------------------------------------- */
		try {
			table.addColumn("名前").addColumn("住所").addColumn("年齢");
		} catch (DatabaseException de) {
			de.printStackTrace();
			System.out.println();
		}
		/* -------------------------------------------------------------------- */

		/* レコード追加 ------------------------------------------------------- */
		try {
			Record rec1 = table.getRecordInstance();
			rec1.addField("田中太郎");
			rec1.addField("天安門広場");
			rec1.addField("123");
			table.addRecord(rec1);

			Record rec2 = table.getRecordInstance();
			rec2.addField("佐藤花子").addField("エルサレム").addField("456");
			table.addRecord(rec2);
		} catch (DatabaseException de) {
			System.out.println(de.getMessage());
		}
		/* -------------------------------------------------------------------- */

		/* データベース作成 --------------------------------------------------- */
		Database base = new Database("或るデータベース");

		System.out.println(base.getName());

		base.addTable(table).addTable(table);

		base.getTables().stream().forEach(t -> {
			System.out.println(t.getName());
			t.getColumns().stream().forEach(column -> System.out.print(column + " "));
			System.out.println();

			t.getRecords().stream().forEach(record -> {
				record.getFields().stream().forEach(field -> System.out.print(field + " "));
				System.out.println();
			});
			System.out.println();
		});

		System.out.println();
		System.out.println();

		// 削除試験
		try {
			base.removeTable(table);
			base.getTables().stream().forEach(t -> {
				System.out.println(t.getName());
				t.getColumns().stream().forEach(column -> System.out.print(column + " "));
				System.out.println();

				t.getRecords().stream().forEach(record -> {
					record.getFields().stream().forEach(field -> System.out.print(field + " "));
					System.out.println();
				});
				System.out.println();
			});
		} catch(DatabaseException de) {
			de.printStackTrace();
		}
		/* -------------------------------------------------------------------- */
	}
}

