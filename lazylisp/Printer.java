package lazylisp;

import lazylisp.types.AbstractFunction;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class Printer {
	public static String print(LLObject arg) throws LLException {
		arg = arg.dethunk();
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
				sb.append(print(cons.getCar().dethunk()));
				arg = cons.getCdr().dethunk();
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
