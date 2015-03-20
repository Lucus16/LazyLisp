package lazylisp.types;

import lazylisp.Environment;
import lazylisp.LLException;

public abstract class LLObject {
	public abstract NonThunk dethunk() throws LLException;

	public Thunk thunk(Environment env) {
		return new Thunk(env, this);
	}

	public LLObject eval(Environment env) throws LLException {
		return env.eval(this);
	}

	public LLObject get(int index) throws LLException {
		throw new IndexOutOfBoundsException();
	}

	public boolean toBool() throws LLException {
//		return !Atom.f.equals(this);
		if (Atom.t.equals(this)) { return true; }
		if (Atom.f.equals(this)) { return false; }
		throw new LLException("Not a boolean: " + this);
	}

	public Atom asAtom() throws LLException {
		throw new LLException("Not an atom: " + this);
	}

	public Cons asCons() throws LLException {
		throw new LLException("Not a list: " + this);
	}

	public AbstractFunction asFunc() throws LLException {
		throw new LLException("Not a function: " + this);
	}
}
