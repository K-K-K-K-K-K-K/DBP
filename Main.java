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
		
		DatabaseController ctrlr = new DatabaseController();

		for(;;) {
			System.out.print("> ");
			in = sc.nextLine().split(" ");
			switch (in[0]) {
				case "help":
					printHelp();
					System.out.println("");
					break;
				case "newdb":
					if (in.length != 2)
						System.out.println("[エラー] 不正な書式");
					else
						try {
							ctrlr.newDatabase(in[1]);
						} catch (DatabaseException dbe) {
							System.out.println("[エラー] " + dbe.getMessage());
						}
					System.out.println("");
					break;
				case "seldb":
					System.out.println("");
					break;
				case "shwdb":
					System.out.println("");
					break;
				case "exit":
					System.out.println("");
					System.exit(0);
				case "":
					break;
				default:
					System.out.println("[エラー] 存在しないコマンド");
					System.out.println("    コマンド: " + in[0]);
					System.out.println("");
			}
		}
	}

	private static void printHelp() {
		System.out.println("プログラム: ヘルプ(help) | 終了(exit)");
		System.out.println("データベース: 新規作成(newdb) | 開く(seldb) | 一覧表示(shwdb)");
		System.out.println("テーブル: 新規作成(newtbl) | 開く(seldbl) | 一覧表示(shwtbl)");
		System.out.println("レコード: 新規作成(newrec) | 開く(selrec) |一覧表示((shwrec)");
	}

	private static void newDatabase() {
	}

	private static void selDatabase() {
	}

	private static void showDatabase() {
	}
}

