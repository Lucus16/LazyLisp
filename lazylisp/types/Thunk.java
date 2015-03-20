package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public class Thunk extends LLObject {
	private Environment env;
	private LLObject body;
	private NonThunk evaluated;

	public Thunk(Environment env, LLObject body) {
		this.env = env;
		this.body = body;
		evaluated = null;
	}

	public String toString() {
		// TODO add representation of environment
		return "Thunk(" + body + ")";
	}

	@Override
	public NonThunk dethunk() throws LLException {
		if (evaluated == null) {
			evaluated = env.eval(body);
		}
		return evaluated;
	}

	@Override
	public LLObject get(int index) throws LLException {
		return dethunk().get(index);
	}
}
