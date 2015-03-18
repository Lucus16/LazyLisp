package lazylisp;

import java.util.HashMap;

import lazylisp.builtins.*;
import lazylisp.types.AbstractFunction;
import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;

public class Environment {
	private final Environment parent;
	private final HashMap<Atom, LLObject> env;

	public Environment() {
		String letDefinition
				= "(macro (vars body) ("
				+ "  (macro"
				+ "   (getvars getvals)"
				+ "   (eval (cons"
				+ "    (list (quote lambda) ((eval getvars) vars) body)"
				+ "    ((eval getvals) vars))))"
				+ "  (lambda (x) (cond"
				+ "    ((atom x) nil)"
				+ "    (true (cons (car (car x)) ((eval getvars) (cdr x))))))"
				+ "  (lambda (x) (cond"
				+ "    ((atom x) nil)"
				+ "    (true (cons (car (cdr (car x))) ((eval getvals) (cdr x))))))))";
		parent = null;
		env = new HashMap<Atom, LLObject>();
		put(new Atom("cons"), new BuiltinCons());
		put(new Atom("car"), new BuiltinCar());
		put(new Atom("cdr"), new BuiltinCdr());
		put(new Atom("atom"), new BuiltinAtom());
		put(new Atom("cond"), new BuiltinCond());
		put(new Atom("eq"), new BuiltinEq());
		put(new Atom("quote"), new BuiltinQuote());
		put(new Atom("lambda"), new BuiltinLambda());
		put(new Atom("macro"), new BuiltinMacro());
		put(new Atom("eval"), new BuiltinEval());
		try {
			put(new Atom("list"), this.eval(Parser.parse("(lambda l l)")));
			put(new Atom("let"), this.eval(Parser.parse(letDefinition)));
		} catch (LLException e) {
			e.printStackTrace();
		}
		put(Cons.nil, Cons.nil);
		put(Atom.t, Atom.t);
		put(Atom.f, Atom.f);
	}

	/**
	 * No other references to env must be kept.
	 * @param parent
	 * @param env
	 */
	public Environment(Environment parent, HashMap<Atom, LLObject> env) {
		this.parent = parent;
		this.env = env;
	}

	public Environment getParent() { return parent; }

	private LLObject put(Atom key, LLObject value) {
		assert value != null;
		return env.put(key, value);
	}

	/**
	 * Accepts any argument state. Returns any argument state.
	 * @param key
	 * @return
	 * @throws LLException
	 */
	public LLObject get(LLObject key) throws LLException {
		key = key.dethunk();
		LLObject value = env.get(key);
		if ((value == null) && (parent != null)) {
			value = parent.get(key);
		}
		if (value == null) {
			throw new LLException("undefined: " + key);
		}
		return value;
	}

	/**
	 * Adds one delayed evaluation layer. Accepts any argument state. Returns
	 * thunked state.
	 * @param arg
	 * @return
	 */
	public LLObject thunk(LLObject arg) {
		return arg.thunk(this);
	}

	/**
	 * Adds one evaluation layer. Accepts any argument state. Returns dethunked
	 * state.
	 * @param arg
	 * @return
	 * @throws LLException
	 */
	public LLObject eval(LLObject arg) throws LLException {
		arg = arg.dethunk();
		if (arg instanceof Atom) {
			return get(arg).dethunk();
		} else if (arg instanceof Cons) {
			Cons cons = (Cons)arg;
			LLObject fnArg = cons.getCdr();
			LLObject fnObj = eval(cons.getCar());
			if (!(fnObj instanceof AbstractFunction)) {
				throw new LLException("Not callable: " + fnObj);
			}
			AbstractFunction fn = (AbstractFunction)fnObj;
			return fn.call(this, fnArg).dethunk();
		} else {
			throw new LLException("Unexpected type: " + arg.getClass());
		}
	}
}
