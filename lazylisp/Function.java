package lazylisp;

import java.util.HashMap;

import lazylisp.types.AbstractFunction;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;
import lazylisp.types.ThunkCons;

public class Function extends AbstractFunction {
	public final Environment defEnv;
	public LLObject argnames;
	public final LLObject body;

	public Function(Environment defEnv, LLObject argnames, LLObject body) {
		this.argnames = argnames;
		this.defEnv = defEnv;
		this.body = body;
	}

	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		argnames = argnames.dethunk();
		HashMap<Atom, LLObject> map = new HashMap<Atom, LLObject>();
		if (argnames instanceof Atom) {
			map.put((Atom)argnames, new ThunkCons(outEnv, arg));
		} else {
			LLObject argname = argnames;
			while (argname instanceof Cons) {
				Cons nameCons = (Cons)argname;
				arg = arg.dethunk();
				if (!(arg instanceof Cons)) {
					throw new LLException("Too few arguments.");
				}
				Cons argCons = (Cons)arg;
				map.put(nameCons.getCar().dethunk().asAtom(),
						argCons.getCar().thunk(outEnv));
				argname = nameCons.getCdr().dethunk();
				arg = argCons.getCdr();
			}
			if (arg instanceof Cons) {
				throw new LLException("Too many arguments.");
			}
		}
		return body.thunk(new Environment(defEnv, map));
	}
}
