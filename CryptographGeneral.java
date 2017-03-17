
/*
 * CSCI 1101 - 	Final Project 
 * AZLO_Cryptographer
 * Name:					Student ID:  	CS ID:
 * Abdualrahman Aldosari	B00621912		Aldosari
 * Zehao Yan				B00721398		zyan
 * Tsz-Fung Luk				B00636383		luk
 * Zhiyuan Zhang (Owen)		B00716809		zhiyuanz
 * 
 * 		
 * Instructor: 	Prof. Bonnie MacKay
 * Date: 		Apr.18th
 * 
 * CryptographyGeneral is a super class for most of the core classes of 
 * the program. When the other classes varies, it arrange the input and
 * put easier for processing
 * 
 */
import java.util.ArrayList;

public abstract class CryptographGeneral
{
	private String inputText;// the users' plain text or encrypted text
	private String outputText = "";// the decryption or encryption output
	private String keyWord;// the key word to encrypt or decrypt the text
	private ArrayList<Character> textChange;// stores the chars of text
	private ArrayList<Integer> posSpace;// store the position of non-letter
										// chars
	private ArrayList<Character> posSpaceC;// store the characters of the
											// non-letter characters
											// (corresponding to its position in
											// posSpace)

	// Two constructors deal with different situation,
	public CryptographGeneral(String iT, String kW)
	{// the first one contains key word (encrypting/decrypting)
		inputText = iT;
		keyWord = kW;
		textChange = new ArrayList<Character>();
		posSpace = new ArrayList<Integer>();
		posSpaceC = new ArrayList<Character>();
		for (int i = 0; i < inputText.length(); i++)
			textChange.add(inputText.charAt(i));
	}

	public CryptographGeneral()
	{// the second does not contains key word (cracker)
		textChange = new ArrayList<Character>();
		posSpace = new ArrayList<Integer>();
		posSpaceC = new ArrayList<Character>();
	}

	// get and set methods
	public void setInputText(String iT)
	{
		inputText = iT;
		textChange.clear();
		for (int i = 0; i < inputText.length(); i++)
			textChange.add(inputText.charAt(i));
	}

	public String getkWCode() { return keyWord; }

	public ArrayList<Character> getTextChange() { return textChange; }

	// method that setKWCode (Caesar Cipher/Vigenere Cipher/Vigenere Cracker)
	public abstract void setKWCode();

	public void removeSpace()
	{// method remove the non-letter characters in the given text
		// tracking the arraylist from the last item to the first, store the
		// position and the characters then remove all the non-character
		int index = textChange.size();
		//System.out.println(textChange);
		while (index > 0)
		{
			index--;
			if (((int) (textChange.get(index)) < 65 || (int) (textChange.get(index)) > 122
					|| ((int) (textChange.get(index)) > 90 && (int) (textChange.get(index)) < 97)))
			{
				posSpace.add(index);
				posSpaceC.add(textChange.get(index));
				textChange.remove(index);
			}
		}
		//System.out.println(posSpace);
		//System.out.println(posSpaceC);
		//System.out.println(textChange);
	}

	// method will, in different ways, process the given text and edit it
	public abstract void Cryptogam();

	public String getCryption()
	{ // it would return the processed text in string
		Cryptogam();
		int index = 0;
		for (int i = posSpace.size() - 1; i >= 0; i--)
			textChange.add(posSpace.get(i), posSpaceC.get(i));
		while (index < textChange.size())
		{
			outputText += textChange.get(index);
			index++;
		}
		System.out.println(textChange+"\n"+outputText);
		return outputText.toUpperCase();
	}

}
