package util.peekiter;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputPeekIterator implements IPeekIterator<Character> {
	private Scanner scanner;
	private String s;
	private int i;
	private boolean done;

	public InputPeekIterator() {
		scanner = new Scanner(System.in);
		s = scanner.nextLine();
		i = -1;
		done = false;
	}

	private boolean getMore() {
		if (!done) {
			if (!scanner.hasNext()) {
				done = true;
			} else {
				s += scanner.nextLine();
			}
		}
		return done;
	}

	@Override
	public boolean hasNext() {
		if (i < s.length() - 1) { return true; }
		getMore();
		return i < s.length() - 1;
	}

	@Override
	public Character next() {
		i += 1;
		if (i >= s.length()) {
			getMore();
		}
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
		if (i >= s.length()) {
			getMore();
		}
		return s.charAt(i + 1);
	}
}
