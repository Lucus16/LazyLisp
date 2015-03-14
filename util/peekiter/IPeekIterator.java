package util.peekiter;

import java.util.Iterator;

public interface IPeekIterator<T> extends Iterator<T> {
	public T peek();
}
