package lazylisp.builtins;

import lazylisp.LLException;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class BuiltinQuote extends AbstractFunction {
	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		arg = Cons.get(arg, 0);
		if (arg instanceof Todo) {
			return ((Todo)arg).getTodo();
		}
		return arg;
	}
}
