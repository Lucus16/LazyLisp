package lazylisp.types;

import java.util.List;

public class Cons extends LLObject {
	public static final Atom nil = new Atom("nil");
	private final LLObject car;
	private final LLObject cdr;

	public Cons(LLObject car, LLObject cdr) {
		assert (cdr instanceof Cons) || nil.equals(cdr);
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

	public static int length(LLObject list) {
		if (nil.equals(list)) {
			return 0;
		}
		assert (list instanceof Cons);
		Cons cons = (Cons)list;
		return 1 + length(cons.cdr);
	}

	public LLObject get(int index) {
		assert index >= 0;
		LLObject list = this;
		Cons cons = (Cons)list;
		while (index > 0) {
			index -= 1;
			list = cons.cdr;
			if (!(list instanceof Cons)) {
				throw new IndexOutOfBoundsException();
			}
			cons = (Cons)list;
		}
		return cons.car;
	}

	public static LLObject get(LLObject list, int index) {
		if (!(list instanceof Cons)) {
			throw new IndexOutOfBoundsException();
		}
		Cons cons = (Cons)list;
		return cons.get(index);
	}

	public static LLObject fromList(List<LLObject> list) {
		return fromList(list, 0);
	}

	private static LLObject fromList(List<LLObject> list, int start) {
		if (list.size() - start == 0) {
			return Cons.nil;
		}
		return new Cons(list.get(start), fromList(list, start + 1));
	}
}
