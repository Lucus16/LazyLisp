package lazylisp.types;

import lazylisp.LLException;

public abstract class NonThunk extends LLObject {
	@Override
	public NonThunk dethunk() throws LLException {
		return this;
	}
}
