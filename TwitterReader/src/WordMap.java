import java.util.HashMap;

public class WordMap extends HashMap<String, Word> {

	private static final long serialVersionUID = 3978963924853466923L;

	public WordMap() {
		super();
	}

	/**
	 * 
	 * @param key
	 *            the key to increment a word if it exists or insert a new word
	 *            if not.
	 */
	public void putOrIncrement(String key) {
		Word word = this.get(key);
		if (word != null) {
			word.incrementCount();
		} else {
			super.put(key, new Word(key));
		}
	}
}
