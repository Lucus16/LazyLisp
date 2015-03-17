package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public class Thunk extends LLObject {
	private Environment env;
	private LLObject body;
	private boolean evaluated;

	public Thunk(Environment env, LLObject body) {
		assert !(body instanceof Thunk);
		this.env = env;
		this.body = body;
		this.evaluated = false;
	}

	public String toString() {
		// TODO add representation of environment
		return getClass().getName() + "(" + body + ")";
	}

	@Override
	public LLObject dethunk() throws LLException {
		if (!evaluated) {
			body = env.eval(body);
			evaluated = true;
		}
		return body;
	}
}
