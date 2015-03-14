package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

/**
 * This is a meta-type, it will never occur in code. It signals that the
 * underlying object hasn't been evaluated yet.
 * @author lars
 *
 */
public class Todo extends LLObject {
	private Environment env;
	private LLObject todo;

	public Todo(Environment env, LLObject todo) {
		this.env = env;
		if (todo instanceof Todo) {
			this.todo = ((Todo)todo).todo;
//			System.err.println("Note: nested todo reduced.");
		} else {
			this.todo = todo;
		}
	}

	public LLObject eval() throws LLException {
		return env.eval(this);
	}

	public LLObject getTodo() {
		return todo;
	}

	public String toString() {
		return "Todo(" + todo + ")";
	}
}
