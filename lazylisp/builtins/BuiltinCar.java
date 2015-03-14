package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class BuiltinCar extends AbstractFunction {
	private Environment inEnv;

	public BuiltinCar(Environment inEnv) {
		this.inEnv = inEnv;
	}

	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		LLObject o = outEnv.eval(Cons.get(arg, 0));
		Cons cons = castIfPossible(o, Cons.class);
		return new Todo(inEnv, cons.getCar());
	}
}
