package lazylisp;

import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;

public class Function extends AbstractFunction {
	public final Environment env;
	public final LLObject argnames;

	public Function(Environment env, LLObject argnames) {
		this.argnames = argnames;
		this.env = env;
	}

	@Override
	public LLObject call(LLObject arg) throws LLException {
		/* Loop over arg and argnames and put(argname, a) for every a and
		 * corresponding argname in them on a new Environment with the current
		 * environment as parent.
		 * Does current mean environment of creation or environment of call
		 * here? Creation.
		 * Can function access underlying vars or only what it is passed?
		 * Both, but underlying vars will be constant for the existance of the
		 * function as they are in an environment below it.
		 *
		 * This call method returns a Todo object containing the expansion of
		 * this function. This Todo must specify the environment this function
		 * was created in.
		 *
		 * or
		 *
		 * This call method returns a Todo object of the function body with a
		 * new environment which has as parent the environment at the creation
		 * of this function and otherwise only contains the parameters with
		 * which this function was called. < more efficient I think
		 */
		return null; // TODO
	}
}
