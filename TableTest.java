import java.util.*;

public class TableTest {
	public static void main(String[] args) {
		Table table = new Table(3);

		/* カラム登録のテスト ------------------------------------------------- */
		try {
			table.addColumn("名前").addColumn("住所").addColumn("年齢");

			// カラム登録の反映確認
			table.getColumns().stream().forEach(clm -> System.out.println(clm));
			System.out.println();

			// カラム変更の反映確認
			table.changeColumn("名前", "氏名");

			table.getColumns().stream().forEach(clm -> System.out.println(clm));
			System.out.println();

			// 超過時の例外発生の確認
			table.addColumn("超過分");
		} catch (DatabaseException de) {
			de.printStackTrace();
			System.out.println();
		}
		/* -------------------------------------------------------------------- */

		/* レコード追加のテスト ----------------------------------------------- */
		try {
			Record rec1 = table.getRecordInstance();
			rec1.addField("田中太郎");
			rec1.addField("天安門広場");
			rec1.addField("123");

			table.addRecord(rec1);
		
			Record rec2 = table.getRecordInstance();
			rec2.addField("佐藤花子").addField("エルサレム").addField("456");

			table.addRecord(rec2);

			table.getRecords().stream().forEach(rec -> {
				rec.getFields().stream().forEach(field -> System.out.println(field));
				System.out.println();
			});
			System.out.println();


			// レコード変更テスト
			Record rec3 = table.getRecordInstance();
			rec3.addField("山田");
			rec3.addField("竹島");
			rec3.addField("243");

			table.changeRecord(rec1, rec3);

			table.getRecords().stream().forEach(rec -> {
				rec.getFields().stream().forEach(field -> System.out.println(field));
				System.out.println();
			});
			System.out.println();
			

			// レコード削除テスト
			table.removeRecord(rec2);
			table.getRecords().stream().forEach(rec -> {
				rec.getFields().stream().forEach(field -> System.out.println(field));
				System.out.println();
			});
			System.out.println();
		} catch (DatabaseException de) {
			System.out.println(de.getMessage());
		}
		/* -------------------------------------------------------------------- */
	}
}

