package lazylisp.builtins;

import lazylisp.Evaluator;
import lazylisp.LLException;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class BuiltinEq extends AbstractFunction {
	private Evaluator eval;

	public BuiltinEq(Evaluator eval) {
		this.eval = eval;
	}

	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		LLObject o1 = eval.eval(Cons.get(arg, 0));
		LLObject o2 = eval.eval(Cons.get(arg, 1));
		Atom a1 = castIfPossible(o1, Atom.class);
		Atom a2 = castIfPossible(o2, Atom.class);
		return Atom.fromBool(a1.equals(a2));
	}
}
