package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Function;
import lazylisp.types.LLObject;

public class BuiltinMacro extends AbstractFunction {
	@Override
	public LLObject call(Environment env, LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		return new Function(env, arg.get(0), arg.get(1), false);
	}
}
