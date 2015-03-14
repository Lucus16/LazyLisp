package lazylisp.builtins;

import lazylisp.Evaluator;
import lazylisp.LLException;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class BuiltinAtom extends AbstractFunction {
	private Evaluator eval;

	public BuiltinAtom(Evaluator eval) {
		this.eval = eval;
	}

	@Override
	public LLObject call(LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		LLObject o = eval.eval(Cons.get(arg, 0));
		return Atom.fromBool(o instanceof Atom);
	}
}
