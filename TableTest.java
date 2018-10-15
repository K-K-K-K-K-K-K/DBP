import java.util.*;

public class TableTest {
	public static void main(String[] args) {
		Table table = new Table();

		/* カラム登録のテスト ------------------------------------------------- */
		table.addColumn("名前").addColumn("住所").addColumn("年齢");

		// カラム登録の反映確認
		table.getColumns().stream().forEach(clm -> System.out.println(clm));
		System.out.println();
		/* -------------------------------------------------------------------- */

		/* レコード追加のテスト ----------------------------------------------- */
		try {
			Record rec1 = new Record(table.getColumnsLength());
			rec1.addField("田中太郎");
			rec1.addField("天安門広場");
			rec1.addField("123");

			table.addRecord(rec1);
		
			Record rec2 = new Record(table.getColumnsLength());
			rec2.addField("佐藤花子");
			rec2.addField("エルサレム");
			rec2.addField("456");

			table.addRecord(rec2);
		} catch (DatabaseException de) {
			System.out.println(de.getMessage());
		}

		table.getRecords().stream().forEach(rec -> {
			rec.getFields().stream().forEach(field -> System.out.println(field));
			System.out.println();
		});
		/* -------------------------------------------------------------------- */
	}
}

