package lazylisp.builtins;

import lazylisp.LLException;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class BuiltinCons extends AbstractFunction {
	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		return new Cons(new Todo(Cons.get(arg, 0)),
				new Todo(Cons.get(arg, 1)));
	}
}
