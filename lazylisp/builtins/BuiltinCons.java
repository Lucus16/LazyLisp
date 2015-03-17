package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class BuiltinCons extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		return new Cons(arg.get(0).thunk(outEnv), arg.get(1).thunk(outEnv));
	}
}
