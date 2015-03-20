package lazylisp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String code;
		if (args.length != 0) {
			if (args[0] == "-i") { new REPL().repl(); return; }
			try {
				code = new Scanner(new File(args[0])).useDelimiter("\\Z")
						.next();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				return;
			}
		} else {
			code = new Scanner(System.in).useDelimiter("\\Z").next();
		}
		try {
			System.out.println(Printer.print(new Environment().eval(
					Parser.parse(code))));
		} catch (LLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
