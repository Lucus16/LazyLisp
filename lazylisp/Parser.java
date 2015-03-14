package lazylisp;

import java.util.ArrayList;
import java.util.List;

import lazylisp.types.Atom;
import lazylisp.types.Cons;
import lazylisp.types.LLObject;
import util.peekiter.IPeekIterator;
import util.peekiter.StringPeekIterator;

public class Parser {
	private static LLObject getList(IPeekIterator<Character> iter) {
//		System.err.println("getList called with peek: " + iter.peek());
		List<LLObject> elems = new ArrayList<LLObject>();
		while (iter.hasNext()) {
			LLObject next = getElem(iter);
			if (next == null) {
				break;
			}
			elems.add(next);
		}
		return Cons.fromList(elems);
	}

	private static LLObject getElem(IPeekIterator<Character> iter) {
//		System.err.println("getElem called with peek: " + iter.peek());
		while (iter.hasNext() && (iter.peek() == ' ' || iter.peek() == '\t' ||
				iter.peek() == '\n' || iter.peek() == '\r')) {
			iter.next();
		}
		if (!iter.hasNext()) {
			return null;
		}
		if (iter.peek() == ')') {
			iter.next();
			return null;
		}
		if (iter.peek() == '(') {
			iter.next();
			return getList(iter);
		}
		return getAtom(iter);
	}

	private static Atom getAtom(IPeekIterator<Character> iter) {
//		System.err.println("getAtom called with peek: " + iter.peek());
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext()) {
			char next = iter.peek();
			if (next == ' ' || next == '\n' || next == '\r' || next == '\t' ||
					next == '(' || next == ')') {
				break;
			}
			sb.append(iter.next());
		}
		return new Atom(sb.toString());
	}

	public static LLObject parse(String s) {
		return getElem(new StringPeekIterator(s));
	}
}
