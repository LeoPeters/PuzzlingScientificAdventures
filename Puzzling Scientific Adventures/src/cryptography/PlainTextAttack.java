package cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class PlainTextAttack {
	public static final String ENCRYPTLINE = "the quick brown fox jumps over the lazy dog";
	public static final String REGEXENCRYPTLINE = "\\w{3}\\s(\\w{5}\\s){2}\\w{3}\\s\\w{5}\\s\\w{4}\\s\\w{3}\\s\\w{4}\\s\\w{3}";
	
	private File file;
	private Scanner scanner;
	private HashMap<Character, Character> decrypt = new HashMap<Character, Character>();
	
	public PlainTextAttack() {
		try {
			file = new File("input.txt");
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readInput() {
		String tempLine;
		while(scanner.hasNextLine()) {
			tempLine = scanner.nextLine();
			System.out.println(tempLine);
			if(tempLine.matches(REGEXENCRYPTLINE)) {
				if(checkSentence(tempLine)) {
					createMap(tempLine);
				}
			}
		}
	}

	
	private boolean checkSentence(String line) {
		
		return false;
	}
	
	private void createMap(String line) {
		line = line.replaceAll("\\s", "");
		for(int i = 0; i < line.length(); i++) {
			decrypt.put(line.charAt(i), ENCRYPTLINE.charAt(i));
		}
	}
	
	private void transLateDecrypted() {
		
	}
	
	public static void main(String[] args) {
		PlainTextAttack pta = new PlainTextAttack();
		pta.readInput();
	}
}
