package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public abstract class AbstractFunction extends LLObject {
	public abstract LLObject call(Environment env, LLObject arg) throws LLException;

	public static void checkArgCount(LLObject arg, int count)
			throws LLException {
		if (Cons.length(arg) != count) {
			throw new LLException("Wrong number of arguments.");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends LLObject> T castIfPossible(LLObject arg, Class<T> type)
			throws LLException {
		if (!type.isInstance(arg)) {
			throw new LLException("Wrong argument type.");
		}
		return (T)arg;
	}
}
