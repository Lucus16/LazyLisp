package util.peekiter;

import java.util.NoSuchElementException;

public class StringPeekIterator implements IPeekIterator<Character> {
	private final String s;
	private int i;

	public StringPeekIterator(String s) {
		this.s = s;
		i = -1;
	}

	@Override
	public boolean hasNext() {
		return i < s.length() - 1;
	}

	@Override
	public Character next() {
		i += 1;
		if (i >= s.length()) {
			throw new NoSuchElementException();
		}
		return s.charAt(i);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Character peek() {
		return s.charAt(i + 1);
	}
}
