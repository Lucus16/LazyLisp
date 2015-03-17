package lazylisp;

import lazylisp.types.LLObject;

public class Test {
	public static void main(String[] args) throws LLException {
		String program = "(cond (false blah) (true nil))";
		LLObject parsed = Parser.parse(program);
		Environment env = new Environment();
		System.out.println(Printer.print(parsed));
		LLObject evaluated = env.eval(parsed);
		System.out.println(Printer.print(evaluated));
	}
}
