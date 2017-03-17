
/*
 * CSCI 1101 - 	Final Project 
 * AZLO_Cryptographer
 * Name:					Student ID:  	CS ID:
 * Abdualrahman Aldosari	B00621912		Aldosari
 * Zehao Yan				B00721398		zyan
 * Tsz-Fung Luk				B00636383		luk
 * Zhiyuan Zhang (Owen)		B00716809		zhiyuanz
 * 
 * Instructor: 	Prof. Bonnie MacKay
 * Date: 		Apr.18th
 * 
 * VigenereDecipher is the class, subclass of CryptographGeneral, decrypt the text 
 * in Vigenere cipher
 * 
 */
import java.util.ArrayList;

public class VigenereDecipher extends CryptographGeneral
{
	private ArrayList<Integer> kWCode;// ArrayList of integer of
	private ArrayList<Character> textChange; // ArrayList of the text

	// constructor
	public VigenereDecipher(String iT, String kW)
	{
		super(iT, kW);
		kWCode = new ArrayList<Integer>();
	}

	public void setKWCode()
	{// take the keyword string and convert the valid characters into the
		// ArrayList
		String keyWord = super.getkWCode();
		for (int i = 0; i < keyWord.length(); i++)
		{// the if else if statement presents only accept the letter in upper
			// and lower case letter
			if ((int) (keyWord.charAt(i)) <= 90 && (int) (keyWord.charAt(i)) >= 65)
				kWCode.add((int) (keyWord.charAt(i) - 65));
			else if ((int) (keyWord.charAt(i)) <= 122 && (int) (keyWord.charAt(i)) >= 97)
				kWCode.add((int) (keyWord.charAt(i) - 97));
		}
		if (kWCode.isEmpty()) // if nothing is added, then add 0 as the shifting
			kWCode.add(0);
	}

	public void Cryptogam()
	{// the method decrypt the String by keyword
		// declaring the variables
		super.removeSpace();
		setKWCode();
		textChange = super.getTextChange();
		int index = 0, codeAdd = 0, codeFAdd = 0;
		int codeC;
		while (index < textChange.size())
		{// for every character in the ArrayList
			codeAdd = kWCode.get(index % kWCode.size());// find the
			// corresponding integer
			// to shift
			codeC = (int) (textChange.get(index));
			;// and convert the character
			// to integer
			// the next if else if statement identify the character, weather
			// they are still letter but not non-letter characters
			if (codeC >= 65 && codeC <= 90 && codeC - codeAdd < 65)
				codeFAdd = codeC - codeAdd + 26;
			else if (codeC >= 97 && codeC <= 122 && codeC - codeAdd < 97)
				codeFAdd = codeC - codeAdd + 26;
			else
				codeFAdd = codeC - codeAdd;
			// add the character back to ArrayList
			textChange.set(index, (char) (codeFAdd));
			index++;// next character
		}
	}
}
