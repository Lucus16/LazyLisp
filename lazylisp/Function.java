package lazylisp;

import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class Function extends AbstractFunction {
	public final Environment defEnv;
	public final LLObject argnames;
	public final LLObject body;

	public Function(Environment defEnv, LLObject argnames, LLObject body) {
		this.argnames = argnames;
		this.defEnv = defEnv;
		this.body = body;
	}

	@Override
	public LLObject call(Environment outEnv, LLObject arg) throws LLException {
		argnames = argnames.dethunk();
		Environment inEnv;
		HashMap<Atom, LLObject> map = new HashMap<Atom, LLObject>();
		if (argnames instanceof Atom) {
			map.put(argnames, arg.thunk(outEnv));
			inEnv = new Environment(defEnv, map);
			return body.thunk(inEnv);
		}
		LLObject argname = argnames.dethunk();
		while (argname instanceof Cons) {
			Cons nameCons = (Cons)argname;
			if (!(arg instanceof Cons) {
				throw new LLException("Too few arguments.");
			}
			Cons argCons = (Cons)arg;
			map.put(nameCons.getCar().dethunk().asAtom(), arg.thunk(outEnv));
			argname = nameCons.getCdr();
			arg = argCons.getCdr();
		}
		if (arg instanceof Cons) {
			throw new LLException("Too many arguments.");
		}
		inEnv = new Environment(defEnv, map);
		return body.thunk(inEnv);
	}
}
