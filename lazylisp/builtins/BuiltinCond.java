package lazylisp.builtins;

import lazylisp.Evaluator;
import lazylisp.LLException;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class BuiltinCond extends AbstractFunction {
	private Evaluator eval;

	public BuiltinCond(Evaluator eval) {
		this.eval = eval;
	}

	@Override
	public LLObject call(LLObject arg) throws LLException {
		while (!Cons.nil.equals(arg)) {
			Cons cons = castIfPossible(arg, Cons.class);
			LLObject pair = cons.getCar();
			checkArgCount(pair, 2);
			LLObject cond = eval.eval(Cons.get(pair, 0));
			if (!Atom.f.equals(cond)) {
				return new Todo(Cons.get(pair, 1));
			}
			arg = cons.getCdr();
		}
		return Cons.nil;
	}
}
