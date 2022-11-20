package classes;

import interfaces.Container;
import interfaces.Iterator;

public class PicturesRepository implements Container {
	
	String[] names = new String[14];
	
	public PicturesRepository() {
		for (int i = 0; i < 14; i++) {
			names[i] = "pack" + (i + 1);
		}
	}
	
	public Iterator getIterator() {
		return new PicturesIterator();
	}
	
	private class PicturesIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			return index < names.length;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return names[index++];
			}

			return null;
		}
	}
}
