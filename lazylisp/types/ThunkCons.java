package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

/**
 * Takes a list and returns a list with every element thunked.
 * @author lars
 *
 */
public class ThunkCons extends LLObject {
	private Environment env;
	private LLObject body;
	private NonThunk evaluated;

	public ThunkCons(Environment env, LLObject car, LLObject cdr) {
		this.env = env;
		this.body = new Cons(car, cdr);
		evaluated = null;
	}

	public ThunkCons(Environment env, LLObject body) {
		this.env = env;
		this.body = body;
		evaluated = null;
	}

	public ThunkCons(Environment env) {
		this.env = env;
		this.body = Cons.nil;
		evaluated = Cons.nil;
	}

	@Override
	public NonThunk dethunk() throws LLException {
		if ((evaluated == null) && body instanceof Cons) {
			Cons cons = (Cons)body;
			evaluated = new Cons(cons.getCar().thunk(env),
							new ThunkCons(env, cons.getCdr()));
		}
		return evaluated;
	}

	@Override
	public String toString() {
		return "ThunkCons(" + body + ")";
	}

	@Override
	public LLObject get(int index) throws LLException {
		return dethunk().get(index);
	}
}
