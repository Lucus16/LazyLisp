package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public class Thunk extends LLObject {
	private Environment env;
	private LLObject body;
	private boolean evaluated;

	public Thunk(Environment env, LLObject body) {
		this.env = env;
		this.body = body;
		evaluated = false;
	}

	public String toString() {
		// TODO add representation of environment
		return "Thunk(" + body + ")";
	}

	@Override
	public LLObject dethunk() throws LLException {
		if (!evaluated) {
			body = env.eval(body);
			evaluated = true;
		}
		return body;
	}

	@Override
	public LLObject get(int index) throws LLException {
		return dethunk().get(index);
	}
}
