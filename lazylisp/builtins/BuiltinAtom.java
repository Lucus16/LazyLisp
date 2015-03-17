package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Atom;
import lazylisp.types.LLObject;

public class BuiltinAtom extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 1);
		return Atom.fromBool(arg.get(0).eval(outEnv) instanceof Atom);
	}
}
