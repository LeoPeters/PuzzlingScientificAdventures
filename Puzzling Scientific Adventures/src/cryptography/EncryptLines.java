/**
 * 27.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - plain text attack
 * @author Cecilia Casarella & Leo Peters
 */

package cryptography;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author Leo
 *
 */
public class EncryptLines {
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<String> sentences = new ArrayList<String>();
	private ArrayList<String> decryptedSentences = new ArrayList<String>();
	private ArrayList<Character> letters = new ArrayList<Character>();
	private HashMap<Character, Character> encrypt = new HashMap<Character, Character>();
	PlainTextAttack pta;

	public EncryptLines(PlainTextAttack pta) {
		this.pta = pta;
		for (int i = 97; i < 123; i++) {
			letters.add((char) i);
		}
		encrypt.put(Character.valueOf((char) 32), Character.valueOf((char) 32));
	}

	/**
	 * Read lines from std input and put into the ArrayList
	 */
	public void readLines() {
		System.out.println("Please type the line you want to decrypt with and hit enter! (Type x for default input)");
		String input = scanner.nextLine();
		
		while (!input.contentEquals("x")) {
			sentences.add(input.toLowerCase().trim());
			System.out.println("Do you want to add another line? Type 'x' if not");
			input = scanner.nextLine();
		}
	}

	public void randomizeLetters() {
		int random;
		for (int i = 97; i < 123; i++) {
			random = (int) (Math.random() * letters.size());
			encrypt.put((char) i, letters.get(random));
			letters.remove(random);
		}
	}

	public void encryptLines() {
		FileWriter writer;
		try {
			File file = new File("input.txt");
			writer = new FileWriter(file);
			file.createNewFile();
			String temp;
			for(String s : sentences) {
				temp = "";
				for(int i = 0; i < s.length(); i++) {
					temp += encrypt.get(s.charAt(i));
				}
				decryptedSentences.add(temp);
			}
			Collections.shuffle(decryptedSentences);
			for (String s : decryptedSentences) {
				writer.write(s + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public String buildRegex() {
		ArrayList<String> regex = new ArrayList<String>();
		String sentence = sentences.get(0);
		char letter;

		for (int i = 0; i < sentence.length(); i++) {
			letter = sentence.charAt(i);
			if (letter != (int) 32) { // If not a whitespace
				if (sentence.substring(0, i).contains(String.valueOf(letter))) {
					regex.add("(\\" + String.valueOf(sentence.indexOf(letter) + 1) + ")");
				} else {
					regex.add("(\\w)");
				}
			} else {
				regex.add("(\\s)");
			}
		}
		return String.join("", regex);
	}
	
	public void setupDecrypt() {
		readLines();
		randomizeLetters();
		encryptLines();
		pta.setEncryptLine(sentences.get(0));
		pta.setRegexEncryptLine(buildRegex());
	}
}
