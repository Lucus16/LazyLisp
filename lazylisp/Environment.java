package lazylisp;

import java.util.HashMap;

import lazylisp.builtins.BuiltinAtom;
import lazylisp.builtins.BuiltinCar;
import lazylisp.builtins.BuiltinCdr;
import lazylisp.builtins.BuiltinCond;
import lazylisp.builtins.BuiltinCons;
import lazylisp.builtins.BuiltinEq;
import lazylisp.builtins.BuiltinQuote;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class Environment extends HashMap<Atom,LLObject> {
	private static final long serialVersionUID = -8947298904045651939L;

	private final Environment parent;
	private final Evaluator eval;

	public Environment(Evaluator eval) {
		parent = null;
		this.eval = eval;
		put(new Atom("cons"), new BuiltinCons());
		put(new Atom("car"), new BuiltinCar(eval));
		put(new Atom("cdr"), new BuiltinCdr(eval));
		put(new Atom("atom"), new BuiltinAtom(eval));
		put(new Atom("cond"), new BuiltinCond(eval));
		put(new Atom("eq"), new BuiltinEq(eval));
		put(new Atom("quote"), new BuiltinQuote());
		put(Cons.nil, Cons.nil);
		put(Atom.t, Atom.t);
		put(Atom.f, Atom.f);
	}

	public Environment(Evaluator eval, Environment parent) {
		this.parent = parent;
		this.eval = eval;
	}

	public LLObject eval(LLObject arg) throws LLException {
		return eval.eval(arg);
	}

	@Override
	public LLObject get(Object key) {
		LLObject value = super.get(key);
		if ((value == null) && (parent != null)) {
			value = parent.get(key);
		}
		return value;
	}
}
