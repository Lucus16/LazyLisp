package lazylisp.types;

import java.util.HashMap;

import lazylisp.Environment;
import lazylisp.LLException;

public class Function extends AbstractFunction {
	public final Environment defEnv;
	public LLObject argnames;
	public final LLObject body;
	public final boolean evalArgs;

	public Function(Environment defEnv, LLObject argnames, LLObject body,
			boolean evalArgs) {
		this.argnames = argnames;
		this.defEnv = defEnv;
		this.body = body;
		this.evalArgs = evalArgs;
	}

	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		argnames = argnames.dethunk();
		HashMap<Atom, LLObject> map = new HashMap<Atom, LLObject>();
		if (argnames instanceof Atom) {
			if (evalArgs) {
				map.put((Atom)argnames, new ThunkCons(outEnv, arg));
			} else {
				map.put((Atom)argnames, arg);
			}
		} else {
			LLObject argname = argnames;
			while (argname instanceof Cons) {
				Cons nameCons = (Cons)argname;
				arg = arg.dethunk();
				if (!(arg instanceof Cons)) {
					throw new LLException("Too few arguments.");
				}
				Cons argCons = (Cons)arg;
				LLObject value = argCons.getCar();
				if (evalArgs) { value = value.thunk(outEnv); }
				map.put(nameCons.getCar().dethunk().asAtom(), value);
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
