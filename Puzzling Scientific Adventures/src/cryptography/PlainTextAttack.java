package cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.annotation.processing.FilerException;


public class PlainTextAttack {
	public static final String ENCRYPTLINE = "the quick brown fox jumps over the lazy dog";
	public static final String REGEXENCRYPTLINE = "\\w{3}\\s(\\w{5}\\s){2}\\w{3}\\s\\w{5}\\s\\w{4}\\s\\w{3}\\s\\w{4}\\s\\w{3}";
	
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
	
	private void findEncryption() throws EndOfFileException {
		while(!foundEncryption) {
			String line = readInput();
			if(line.matches(REGEXENCRYPTLINE)) {
				if(checkSentence(line)) {
					createMap(line);
					foundEncryption = true;
				}
			}
		}
	}
	
	private String readInput() throws EndOfFileException {
		String tempLine = "";
		while(scanner.hasNextLine()) {
			tempLine = scanner.nextLine();
			if(!tempLine.isEmpty()) {
				return tempLine;
			}
		}
		throw new EndOfFileException();
	}

	
    private boolean checkSentence(String line) {
        //Character 0 and Character 31 should match
        //Character 1 and Character 32 should match
        //Character 2 Character 28 Character 33 should match
        //Character 12 Character 17 Character 26 and Character 41 should match
        char[] word = line.toCharArray();
        if (word[0] == word[31] && word[1] == word[32] && word[2] == word[28] && word[28] == word[33] && word[12] == word[17] && word[17] == word[26] && word[26] == word[41]) {
            //if the matches are correct and removing the duplicate characters we count 26 letter then it is the correct sentence
            return line.replaceAll("[^a-z]", "").replaceAll("(.)(?=.*\\1)", "").length() == 26;
        }
        return false;
    }
	
	private void createMap(String line) {
		line = line.replaceAll("\\s", "");
		for(int i = 0; i < line.length(); i++) {
			decrypt.put(line.charAt(i), ENCRYPTLINE.charAt(i));
		}
	}
	
	private void transLateDecrypted() throws EndOfFileException {
		while(true) {
			for(char c : readInput().toCharArray()) {
				if(c != ' ') {
					c = decrypt.get(c);
				}
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		PlainTextAttack pta = new PlainTextAttack();
		try {
			pta.readInput();
		} catch (EndOfFileException e) {
			System.out.println("No encryption found in document");
		}
		try {
			pta.transLateDecrypted();
		} catch (EndOfFileException e) {
			System.out.println("All lines translated");
		}
	}
}
