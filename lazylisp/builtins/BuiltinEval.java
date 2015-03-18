package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class BuiltinEval extends AbstractFunction {
	@Override
	public LLObject call(Environment env, LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		return arg.get(0).eval(env).eval(env);
	}
}
