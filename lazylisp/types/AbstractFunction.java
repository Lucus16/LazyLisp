package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public abstract class AbstractFunction extends LLObject {
	public abstract LLObject call(Environment env, LLObject arg) throws LLException;

	/**
	 * Accepts any argument state. Should only be used if all arguments are
	 * sure to be needed.
	 * @param arg
	 * @param count
	 * @throws LLException
	 */
	public static void checkArgCount(LLObject arg, int count)
			throws LLException {
		while (count > 0) {
			arg = arg.dethunk();
			if (!(arg instanceof Cons)) {
				throw new LLException("Too few arguments, missing: " + count);
			}
			arg = ((Cons)arg).getCdr();
			count -= 1;
		}
		if (arg instanceof Cons) {
			throw new LLException("Too many arguments.");
		}
	}

	@Override
	public AbstractFunction asFunc() throws LLException {
		return this;
	}
}
