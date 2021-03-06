package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Function;
import lazylisp.types.LLObject;

public class BuiltinLambda extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		return new Function(outEnv, arg.get(0), arg.get(1), true);
	}
}
