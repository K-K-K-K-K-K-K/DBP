public class DatabaseControllerTest {
	public static void main(String[] args) {
		try {
			DatabaseController dc = new DatabaseController("TestDataPool");

			/* データベース作成試験 --------------------------------------------------- */
			dc.newDatabase("TestDB");
			dc.newDatabase("TestDB2");
			/* ------------------------------------------------------------------------ */

			/* 全データベース名取得試験 ----------------------------------------------- */
			dc.getDatabaseNames().stream().forEach(name -> System.out.println(name));
			/* ------------------------------------------------------------------------ */

			System.out.println();
			System.out.println();

			/* データベースの重複作成による例外発生の確認 ----------------------------- */
			try {
				dc.newDatabase("TestDB");
			} catch (DatabaseException de) {
				System.out.println(de.getMessage());
			}
			/* ------------------------------------------------------------------------ */

			System.out.println();
			System.out.println();

			/* データベース読み込み試験 ----------------------------------------------- */
			Database db1 = dc.openDatabase("TestDB");
			/* ------------------------------------------------------------------------ */

			/* 存在しないデータベースの読み込みによる例外発生の確認 ------------------- */
			try {
				Database db2 = dc.openDatabase("DB");
			} catch (DatabaseException de) {
				System.out.println(de.getMessage());
			}
			/* ------------------------------------------------------------------------ */

			System.out.println();
			System.out.println();

			/* データベース保存試験 --------------------------------------------------- */
			Table tbl =
				new Table("名簿", 2)
					.addColumn("氏名")
					.addColumn("国籍");

			db1.addTable(
				tbl
					.addRecord(
						tbl.getRecordInstance()
							.addField("田中")
							.addField("日本国")
					)
					.addRecord(
						tbl.getRecordInstance()
							.addField("朴")
							.addField("朝鮮民主主義人民共和国")
					)
					.addRecord(
						tbl.getRecordInstance()
							.addField("阮")
							.addField("共和社会主義越南")
					)
			);

			dc.saveDatabase(db1);

			dc.openDatabase(db1.getName()).getTables().stream().forEach(t -> {
				System.out.println(t.getName());
			
				t.getColumns().stream().forEach(c -> {
					System.out.print(c + " ");
				});
				System.out.println();

				t.getRecords().stream().forEach(rec -> {
					rec.getFields().stream().forEach(f -> {
						System.out.print(f + " ");
					});
					System.out.println();
				});
			});
			/* ------------------------------------------------------------------------ */

			System.out.println();
			System.out.println();

			/* データベース削除試験 --------------------------------------------------- */
			dc.removeDatabase("TestDB2");
			dc.getDatabaseNames().stream().forEach(name -> System.out.println(name));
			/* ------------------------------------------------------------------------ */
		} catch (DatabaseException de) {
			System.out.println(de.getMessage());
		}
	}
}

