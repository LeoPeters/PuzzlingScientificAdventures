/**
 * 27.05.2020 - v1.0
 * Puzzling Scientific Adventures
 * Task 2 - plain text attack
 * @author Cecilia Casarella & Leo Peters
 */

package cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class prepares and executes a plain text attack. The sentence needed for
 * encryption is set at String "ENCRYPTLINE". The regex "REGEXENCRYPTLINE" has to
 * match the sentence when it is decrypted (also indicating every duplicate
 * letter).
 * 
 * @author Leo
 *
 */
public class PlainTextAttack {
	private String encryptLine = "the quick brown fox jumps over the lazy dog";
	private String regexEncryptLine = "(\\w)(\\w)(\\w)\\s\\w{5}\\s\\w{2}(\\w)\\w{2}\\s\\w\\4\\w\\s\\w{5}\\s\\4\\w\\3\\w\\s\\1\\2\\3\\s\\w{4}\\s\\w\\4\\w";

	private File file;
	private Scanner scanner;
	private HashMap<Character, Character> decrypt = new HashMap<Character, Character>();
	private boolean foundEncryption;

	public PlainTextAttack() {
		try {
			file = new File("input.txt");
			scanner = new Scanner(file);
			foundEncryption = false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Find the line indicated by ENCRYPTLINE
	 * 
	 * @throws EndOfFileException
	 */
	private void findEncryption() throws EndOfFileException {
		while (!foundEncryption) {
			String line = readInput();
			if (line.matches(regexEncryptLine)) {
//				if(checkSentence(line)) {
				createMap(line);
				foundEncryption = true;
//				}
			}
		}
	}

	/**
	 * Reads the next line of the "input.txt" file.
	 * @return
	 * @throws EndOfFileException
	 */
	private String readInput() throws EndOfFileException {
		String tempLine = "";
		while (scanner.hasNextLine()) {
			tempLine = scanner.nextLine();
			if (!tempLine.isEmpty()) {
				return tempLine;
			}
		}
		throw new EndOfFileException();
	}

	/**
	 * Creating a map for all characters in the alphabet. The keys are the letters
	 * found in the decrypted sentence. The values are the corresponding encrypted
	 * letters.
	 * 
	 * @param line
	 */
	private void createMap(String line) {
//		System.out.println(line);
		for (int i = 0; i < line.length(); i++) {
			decrypt.put(line.charAt(i), encryptLine.charAt(i));
		}
	}

	/**
	 * Translate all decrypted sentences with the help of the map.
	 * 
	 * @throws EndOfFileException
	 */
	private void transLateDecrypted() throws EndOfFileException {
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (true) {
			for (char c : readInput().toCharArray()) {
				if (c != ' ') {
					c = decrypt.get(c);
				}
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		PlainTextAttack pta = new PlainTextAttack();
		EncryptLines decrypt = new EncryptLines(pta);
		try {
			decrypt.setupDecrypt();
			pta.findEncryption();
		} catch (EndOfFileException e) {
			System.out.println("No encryption found in document");
		}
		try {
			pta.transLateDecrypted();
		} catch (EndOfFileException e) {
			System.out.println("All lines translated");
		}
	}

	public String getEncryptLine() {
		return encryptLine;
	}

	public void setEncryptLine(String encryptLine) {
		this.encryptLine = encryptLine;
	}

	public String getRegexEncryptLine() {
		return regexEncryptLine;
	}

	public void setRegexEncryptLine(String regexEncryptLine) {
		this.regexEncryptLine = regexEncryptLine;
	}
}
