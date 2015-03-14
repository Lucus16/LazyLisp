package lazylisp;

import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class Printer {
	public static String print(Evaluator eval, LLObject arg) throws LLException {
		return printX(eval, new Todo(arg));
	}

	public static String printX(Evaluator eval, LLObject arg) throws LLException {
		if (arg instanceof Todo) {
			arg = eval.eval(arg);
		}
		StringBuilder sb = new StringBuilder();
		if (arg instanceof Atom) {
			sb.append(((Atom)arg).getName());
		} else if (arg instanceof Cons) {
			sb.append('(');
			boolean first = true;
			while (arg instanceof Cons) {
				if (!first) { sb.append(' '); }
				first = false;
				Cons cons = (Cons)arg;
				sb.append(printX(eval, cons.getCar()));
				arg = cons.getCdr();
				if (arg instanceof Todo) {
					arg = eval.eval(arg);
				}
			}
			sb.append(')');
		} else if (arg instanceof AbstractFunction) {
			sb.append(arg); // TODO
		} else {
			throw new LLException("Unprintable type.");
		}
		return sb.toString();
	}
}
