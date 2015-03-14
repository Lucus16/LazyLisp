package lazylisp.builtins;

import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class BuiltinLambda extends AbstractFunction {
	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		return null; // TODO
	}
}
