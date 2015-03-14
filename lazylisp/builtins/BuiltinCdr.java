package lazylisp.builtins;

import lazylisp.Evaluator;
import lazylisp.LLException;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class BuiltinCdr extends AbstractFunction {
	private Evaluator eval;

	public BuiltinCdr(Evaluator eval) {
		this.eval = eval;
	}

	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		LLObject o = eval.eval(Cons.get(arg, 0));
		Cons cons = castIfPossible(o, Cons.class);
		return new Todo(cons.getCdr());
	}
}
