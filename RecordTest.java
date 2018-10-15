import java.util.*;

public class RecordTest {
	public static void main(String[] args) {
		/* レコード追加のテスト ----------------------------------------------- */
		try {
			int length = 3;

			Record rec1 = new Record(length);
			rec1.addField("田中太郎");
			rec1.addField("天安門広場");
			rec1.addField("123");
	
			rec1.getFields().stream().forEach(field -> System.out.println(field));
			System.out.println();
	

			Record rec2 = new Record(length);
			rec2.addField("佐藤花子");
			rec2.addField("エルサレム");
			rec2.addField("456");
		
			rec2.getFields().stream().forEach(field -> System.out.println(field));
			System.out.println();

			
			Record rec3 = new Record(length);
			rec3.addField("始皇帝");
			rec3.addField("北海道");
			rec3.addField("99999999");
			rec3.addField("皇帝");
		} catch (DatabaseException de) {
			de.printStackTrace();
		}
		/* -------------------------------------------------------------------- */
	}
}

