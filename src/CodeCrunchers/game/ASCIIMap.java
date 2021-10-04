package CodeCrunchers.game;

import java.util.*;

// This class creates a HashMap object of the ASCII value of each keyboard character,
// with the keyboard character functioning as the Key.
// Only ASCII character codes 32-127 are used, as these represent the keys on a
// standard keyboard.
// ASCII codes taken from https://www.ascii-code.com/.
public class ASCIIMap {
	
	private HashMap<Character, Integer> keyMap;
	private char keyInput;
	private int asciiValue;
	
	public ASCIIMap() {
		this.keyMap = new HashMap<Character, Integer>();
	
		// Loops through all standard keyboard characters, converts the character to it's
		// corresponding ASCII value as an integer, and adds both objects to the HashMap.
		char tempKey = ' ';
		int tempValue;
		for (int i = 32; i < 128; i++) {
			tempValue = (int) tempKey;
			this.keyMap.put(tempKey, tempValue);
			tempKey++;
		}
		
	}
	
	public int returnASCIIValue(char keyInput) {
		return this.keyMap.get(keyInput);
	}
	
	public void testPrint() {
		for (char i : this.keyMap.keySet()) {
			System.out.println("Key: " + i + " - ASCII Value: " + this.keyMap.get(i));
		}
	}

	public static void main(String[] args) {
		ASCIIMap asciiMap = new ASCIIMap();
		asciiMap.testPrint();
	}

}
