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
//		LLObject argname = argnames;
//		Environment inEnv = new Environment(defEnv);
//		while ((arg instanceof Cons) && (argname instanceof Cons)) {
//			Cons argCons = (Cons)arg;
//			Thunk argTodo = new Thunk(outEnv, argCons.getCar());
//			Cons nameCons = (Cons)argname;
//			Atom nameAtom = cast(nameCons.getCar(), Atom.class);
//			inEnv.put(nameAtom, argTodo);
//			arg = argCons.getCdr();
//			argname = nameCons.getCdr();
//		}
//		if (arg instanceof Cons) {
//			throw new LLException("Too many arguments.");
//		} else if (argname instanceof Cons) {
//			throw new LLException("Too few arguments.");
//		}
//		return new Thunk(inEnv, body);
		return null; // TODO
	}
}
