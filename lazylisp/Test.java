package lazylisp;

import lazylisp.types.LLObject;

public class Test {
	public static void main(String[] args) throws LLException {
		String program = "nil";
		LLObject parsed = Parser.parse(program);
		Evaluator eval = new Evaluator();
		String result = Printer.print(eval, parsed);
		System.out.println(result);
	}
}
