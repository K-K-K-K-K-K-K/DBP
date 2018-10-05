import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 起動メッセージ
		System.out.println("DBP ~ DataBaseProgram~");
		System.out.println();

		runShell();
	}

	private static void runShell() {
		Scanner sc = new Scanner(System.in);
		String cmd;

		for(;;) {
			System.out.print("> ");
			cmd = sc.nextLine();
			switch (cmd) {
				case "help":
					printHelp();
					System.out.println("");
					break;
				case "newdb":
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
					System.out.println("    コマンド: " + cmd);
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

