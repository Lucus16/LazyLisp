package lazylisp.types;

import lazylisp.LLException;

public class Atom extends LLObject {
	public static final Atom t = new Atom("true");
	public static final Atom f = new Atom("false");

	private final String name;

	public Atom(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Atom)) {
			return false;
		}
		Atom other = (Atom)o;
		return this.name.equals(other.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	public static LLObject fromBool(boolean b) {
		return b ? t : f;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getClass().getName() + "(" + name + ")";
	}

	@Override
	public Atom asAtom() throws LLException {
		return this;
	}
}
