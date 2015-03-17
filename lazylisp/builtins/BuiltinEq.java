package lazylisp.builtins;

import lazylisp.Environment;
import lazylisp.LLException;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Atom;
import lazylisp.types.LLObject;

public class BuiltinEq extends AbstractFunction {
	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		checkArgCount(arg, 2);
		Atom a1 = arg.get(0).eval(outEnv).asAtom();
		Atom a2 = arg.get(1).eval(outEnv).asAtom();
		return Atom.fromBool(a1.equals(a2));
	}
}
