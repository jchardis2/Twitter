public class Word {
	public String name;
	public int count;

	public Word() {
		super();
		count = 1;
	}

	public Word(String name) {
		this();
		this.name = name;
	}

	public void incrementCount() {
		count++;
	}
}
