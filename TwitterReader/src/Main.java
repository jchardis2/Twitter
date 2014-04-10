import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static File files[];
	public static String removeArray[] = { "", " ", "\t" };

	public static void main(String[] args) {
		if (args.length > 0) {
			files = new File[args.length];
			for (int i = 0; i < args.length; i++) {
				File file = new File(args[i]);
				files[i] = file;
			}
		} else {
			files = new File[1];
			files[0] = new File("tweets.txt");
		}
		WordMap wm = parseFiles(files);
		List<Word> wordList = sortMap(wm);
		for (Word word : wordList) {
			System.out.println("Word: " + word.name + "\tCount: " + word.count);
		}
	}

	public static WordMap parseFiles(File files[]) {
		WordMap wordMap = new WordMap();
		for (File file : files) {
			InputStream fis;
			BufferedReader br;
			String line;
			try {
				fis = new FileInputStream(file);

				br = new BufferedReader(new InputStreamReader(fis));
				while ((line = br.readLine()) != null) {
					String wordArray[] = line.split(" ");
					for (String string : wordArray) {
						string = string.trim();
						if (!shouldRemove(string))
							wordMap.putOrIncrement(string);
					}
				}
				br.close();
				br = null;
				fis = null;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return wordMap;
	}

	public static List<Word> sortMap(WordMap wm) {
		List<Word> wordList = new ArrayList<Word>(wm.values());
		return sort(0, wordList.size() - 1, wordList);
	}

	private static List<Word> sort(int cur, int next, List<Word> wordList) {
		List<Word> sortedList = new ArrayList<>();
		int count = 0;
		while (wordList.size() != 0) {
			for (int i = 0; i < wordList.size(); i++) {
				Word word = wordList.get(i);
				if (word.count == count) {
					sortedList.add(word);
					wordList.remove(i);
					i--;
				}
			}
			count++;
			// System.out.println("Count: " + count);
			// System.out.println("Wordlist size: " + wordList.size());
		}
		return sortedList;
	}

	public static boolean shouldRemove(String string) {
		for (String rm : removeArray) {
			if (string.equals(rm)) {
				return true;
			}
		}
		return false;
	}

}
