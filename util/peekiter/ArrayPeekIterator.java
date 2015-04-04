package util.peekiter;

import java.util.NoSuchElementException;

public class ArrayPeekIterator<T> implements IPeekIterator<T> {
	private final T[] a;
	private int i;

	public ArrayPeekIterator(T[] a) {
		this.a = a.clone();
		i = -1;
	}

	@Override
	public boolean hasNext() {
		return i < a.length - 1;
	}

	@Override
	public T next() {
		i += 1;
		if (i >= a.length) {
			throw new NoSuchElementException();
		}
		return a[i];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public T peek() {
		return a[i + 1];
	}
}
