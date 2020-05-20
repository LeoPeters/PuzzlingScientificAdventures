package cryptography;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

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

        return;
    }

    public static void main(String[] args) {
        PlainTextAttack pta = new PlainTextAttack();
        System.out.println(pta.checkSentence("xnm ceuob lrtzv ita hegfd tsmr xnm ypwq ktj"));
    }
}