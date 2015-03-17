package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class BuiltinCond extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		arg = arg.dethunk();
		while (arg instanceof Cons) {
			Cons cons = (Cons)arg;
			LLObject pair = cons.getCar();
			checkArgCount(pair, 2);
			if (pair.get(0).eval(outEnv).toBool()) {
				return pair.get(1).thunk(outEnv);
			}
			arg = cons.getCdr().dethunk();
		}
		throw new LLException("All conditions failed.");
	}
}
