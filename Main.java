import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 起動通知文表示
		System.out.println("DBP ~ DataBaseProgram~");
		System.out.println();

		// シェル起動
		runShell();
	}

	private static void runShell() {
		Scanner sc = new Scanner(System.in);
		String in[];

		DatabaseController dbc = new DatabaseController();

		Database db = null;
		Table tbl = null;

		for(;;) {
			System.out.print("> ");
			in = sc.nextLine().split(" ");
			switch (in[0]) {
				case "help":
					printHelp();
					System.out.println();
					break;

				case "newdb":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else
						try {
							db = dbc.newDatabase(in[1]);
						} catch (DatabaseException dbe) {
							System.out.println("[エラー] " + dbe.getMessage());
						}

					System.out.println();
					break;

				case "seldb":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else
						try {
							db = dbc.openDatabase(in[1]);
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}

					System.out.println();
					break;

				case "shwdb":
					try {
						dbc.getDatabaseNames().stream().forEach(name -> {
							System.out.println(name);
						});
					} catch (DatabaseException de) {
						System.out.println("[エラー] + " + de.getMessage());
					}

					System.out.println();
					break;

				case "deldb":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else
						try {
							if (db.getName().equals(in[1]))
								db = null;
							dbc.removeDatabase(in[1]);
						} catch (DatabaseException de) {
							System.out.println("[エラー] + " + de.getMessage());
						}

					System.out.println();
					break;

				case "newtbl":
					if (in.length != 3)
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else {
						tbl = new Table(in[1], Integer.parseInt(in[2]));
						db.addTable(tbl);
					}

					System.out.println();
					break;

				case "seltbl":
					if (in.length != 2) 
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else
						try {
							tbl = db.getTables().get(db.indexOfTable(in[1]));
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}

					System.out.println();
					break;
				
				case "shwtbl":
					if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else
						db.getTables().stream().forEach(table -> {
							System.out.println(table.getName());
						});

					System.out.println();
					break;
	
				case "deltbl":
					if (in.length != 2) 
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else
						try {
							if (tbl.getName().equals(in[1]))
								tbl = null;
							db.removeTable(db.getTables().get(db.indexOfTable(in[1])));
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}

					System.out.println();
					break;
	
				// 未試験
				case "addclm":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else if (tbl == null)
						System.out.println("[エラー] テーブルが選択されていません");
					else
						try {
							tbl.addColumn(in[1]);
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}
							

					System.out.println();
					break;

				// 未試験
				case "chclm":
					if (in.length != 3)
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else if (tbl == null)
						System.out.println("[エラー] テーブルが選択されていません");
					else
						try {
							tbl.changeColumn(in[1], in[2]);
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}

					System.out.println();
					break;

				// 未試験
				case "newrec":
					if (in.length + 1 != tbl.getColumns().size())
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else if (tbl == null)
						System.out.println("[エラー] テーブルが選択されていません");
					else {
						Record rec = tbl.getRecordInstance();
						try {
							for (int i = 1; i < tbl.getColumns().size(); i++) {
								rec.addField(in[i]);
							}
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}
					}

					System.out.println();
					break;

				// 未試験
				case "delrec":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else if (db == null)
						System.out.println("[エラー] データベースが選択されていません");
					else if (tbl == null)
						System.out.println("[エラー] テーブルが選択されていません");
					else
						try {
							tbl.removeRecord(Integer.parseInt(in[1]));
						} catch (NumberFormatException nfe) {
							System.out.println("[エラー] 入力が数字ではありません");
						} catch (DatabaseException de) {
							System.out.println("[エラー] " + de.getMessage());
						}

					System.out.println();
					break;

				//
				case "edrec":
					break;

				case "shwrec":
					tbl.getColumns().stream().forEach(clm -> {
						System.out.print(clm + "    ");
					});
					System.out.println();

					System.out.println("----------------------------------------");

					tbl.getRecords().stream().forEach(rec -> {
						rec.getFields().stream().forEach(field -> {
							System.out.print(field + "    ");
						});
						System.out.println();
					});

					System.out.println();
					break;

				case "exit":
					System.out.println();
					System.exit(0);

				case "status":
					System.out.println("データベース: " + (db == null ? "" : db.getName()));
					System.out.println("テーブル: " + (tbl == null ? "" : tbl.getName()));
					
					System.out.println();
					break;

				case "":
					break;

				default:
					System.out.println("[エラー] 存在しないコマンド");
					System.out.println("    コマンド: " + in[0]);
					System.out.println();
			}
		}
	}

	private static void printHelp() {
		// コマンド一覧最新化の要有
		System.out.println("プログラム: 状態(status) | ヘルプ(help) | 終了(exit)");
		System.out.println("データベース: 新規作成(newdb) | 開く(seldb) | 削除 (deldb) | 一覧表示(shwdb)");
		System.out.println("テーブル: 新規作成(newtbl) | 開く(seltbl) | 一覧表示(shwtbl)");
		System.out.println("レコード: 新規作成(newrec) |一覧表示((shwrec)");
		// 削除, 編輯 -> Mainでの実装さえできればできる
	}
}

