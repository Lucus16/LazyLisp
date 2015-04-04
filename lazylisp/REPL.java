package lazylisp;

import java.util.Scanner;

public class REPL {
	private Scanner scanner;

	public REPL() {
		scanner = new Scanner(System.in);
	}

	public void repl() {
		Environment env = new Environment();
		for (String input = getFullInput(); input != null; input = getFullInput()) {
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
		StringBuilder sb = new StringBuilder();
		sb.append(scanner.nextLine());
		while (!parensMatch(sb.toString())) {
			System.out.print("... ");
			if (!scanner.hasNext()) {
				return null;
			}
			sb.append(" ");
			sb.append(scanner.nextLine());
		}
		return sb.toString();
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
