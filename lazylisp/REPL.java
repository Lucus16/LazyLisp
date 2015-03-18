package lazylisp;

import java.util.Scanner;

public class REPL {
	private Scanner scanner;

	public REPL() {
		scanner = new Scanner(System.in);
	}

	public void repl() {
		Environment env = new Environment();
		while (true) {
			String input = getFullInput();
			if (input == null) { break; }
			try {
				System.out.println(Printer.print(env.eval(Parser.parse(input))));
			} catch (LLException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		scanner.close();
	}

	public String getFullInput() {
		System.out.print("ll> ");
		if (!scanner.hasNext()) {
			return null;
		}
		String s = scanner.nextLine();
		while (!parensMatch(s)) {
			System.out.print("... ");
			if (!scanner.hasNext()) {
				return null;
			}
			s += " " + scanner.nextLine();
		}
		return s;
	}

	public static boolean parensMatch(String input) {
		int level = 0;
		for (char c : input.toCharArray()) {
			if (c == '(') {
				level += 1;
			} else if (c == ')') {
				level -= 1;
				if (level < 0) {
					return false;
				}
			}
		}
		return level == 0;
	}

	public static void main(String[] args) {
		new REPL().repl();
	}
}
