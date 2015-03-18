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
	private boolean evaluated;

	public ThunkCons(Environment env, LLObject car, LLObject cdr) {
		this.env = env;
		this.body = new Cons(car, cdr);
		evaluated = false;
	}

	public ThunkCons(Environment env, LLObject body) {
		this.env = env;
		this.body = body;
		evaluated = false;
	}

	public ThunkCons(Environment env) {
		this.env = env;
		this.body = Cons.nil;
		evaluated = true;
	}

	@Override
	public LLObject dethunk() throws LLException {
		if (!evaluated && body instanceof Cons) {
			Cons cons = (Cons)body;
			body = new Cons(cons.getCar().thunk(env),
							new ThunkCons(env, cons.getCdr()));
			evaluated = true;
		}
		return body;
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
