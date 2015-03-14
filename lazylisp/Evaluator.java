package lazylisp;

import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.AbstractFunction;
import lazylisp.types.LLObject;
import lazylisp.types.Todo;

public class Evaluator {
	private Environment env;

	public Evaluator() {
		env = new Environment(this);
	}

	public LLObject eval(LLObject arg) throws LLException {
		return evalTodo(new Todo(arg));
	}

	public LLObject evalTodo(LLObject arg) throws LLException {
		while (arg instanceof Todo) {
			arg = ((Todo)arg).getTodo();
			if (arg instanceof Atom) {
				LLObject old = arg;
				arg = env.get(arg);
				if (arg == null) {
					throw new LLException("Unknown atom: " + old.toString());
				}
			} else if (arg instanceof Cons) {
				Cons cons = (Cons)arg;
				LLObject fnName = cons.getCar();
				LLObject fnArg = cons.getCdr();
				LLObject fnObj = env.get(fnName);
				if (fnObj == null) {
					throw new LLException("Unknown function.");
				}
				if (!(fnObj instanceof AbstractFunction)) {
					throw new LLException("Not a function: " + fnObj.toString());
				}
				AbstractFunction fn = (AbstractFunction)fnObj;
				arg = fn.call(fnArg);
			} else {
				throw new LLException("Unexpected type: " + arg.getClass());
			}
		}
		return arg;
	}
}
