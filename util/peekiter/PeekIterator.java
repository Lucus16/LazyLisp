package util.peekiter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekIterator<T> implements IPeekIterator<T> {
	private final Iterator<T> iter;
	private T next;

	public PeekIterator(Iterator<T> iter) {
		this.iter = iter;
		if (!iter.hasNext()) {
			next = null;
		} else {
			next = iter.next();
		}
	}

	public T peek() {
		return next;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public T next() {
		if (next == null) {
			throw new NoSuchElementException();
		}
		T tmp = next;
		if (!iter.hasNext()) {
			next = null;
		} else {
			next = iter.next();
		}
		return tmp;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
