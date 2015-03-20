package lazylisp.types;

import java.util.List;

import lazylisp.LLException;

public class Cons extends NonThunk {
	public static final Atom nil = new Atom("nil");
	private final LLObject car;
	private final LLObject cdr;

	public Cons(LLObject car, LLObject cdr) {
		this.car = car;
		this.cdr = cdr;
	}

	public LLObject getCar() {
		return car;
	}

	public LLObject getCdr() {
		return cdr;
	}

	public String toString() {
		return "Cons(" + car + ", " + cdr + ")";
	}

	/**
	 * Returns any argument state.
	 * @param index
	 * @return
	 * @throws LLException
	 */
	public LLObject get(int index) throws LLException {
		assert index >= 0;
		Cons cons = this;
		while (index > 0) {
			index -= 1;
			LLObject list = cons.cdr.dethunk();
			if (!(list instanceof Cons)) {
				System.out.println(list.getClass());
				throw new IndexOutOfBoundsException();
			}
			cons = (Cons)list;
		}
		return cons.car;
	}

	public static LLObject fromList(List<LLObject> list) {
		LLObject prev = nil;
		for (int i = list.size() - 1; i >= 0; i--) {
			prev = new Cons(list.get(i), prev);
		}
		return prev;
	}

	@Override
	public Cons asCons() throws LLException {
		return this;
	}
}
