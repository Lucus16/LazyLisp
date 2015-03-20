package lazylisp.types;

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
		Environment inEnv = new Environment(defEnv);
		if (argnames instanceof Atom) {
			if (evalArgs) {
				LLObject value = new ThunkCons(outEnv, arg);
				inEnv.put((Atom)argnames, value);
			} else {
				inEnv.put((Atom)argnames, arg);
			}
		} else {
			LLObject argname = argnames;
			int argnum = 0;
			while (argname instanceof Cons) {
				argnum += 1;
				Cons nameCons = (Cons)argname;
				arg = arg.dethunk();
				if (!(arg instanceof Cons)) {
					throw new LLException("Too few arguments.");
				}
				Cons argCons = (Cons)arg;
				LLObject value = argCons.getCar();
				if (evalArgs) { value = value.thunk(outEnv); }
				inEnv.put(nameCons.getCar().dethunk().asAtom(), value);
				argname = nameCons.getCdr().dethunk();
				arg = argCons.getCdr();
			}
			if (arg instanceof Cons) {
				throw new LLException("Too many arguments, expected: " + argnum);
			}
		}
		return body.thunk(inEnv);
	}
}
