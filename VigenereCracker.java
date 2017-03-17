
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
 * VigenereCracker is a class, subclass of CryptographGeneral, that will analysis
 * a given String and output the guess length of the keyword then analysis the 
 * frequency of the letters in the specific position.
 * 
 * 
 */

import java.util.ArrayList;

public class VigenereCracker extends CryptographGeneral
{
	private ArrayList<Integer> occuranceTime; // storing the integers so-called
												// coincidence
	private String presentString = ""; // String that will present the
										// information
	private String[] PresentStringArray; // Array of the String
	private int guessLength; // integer guess length
	private int[] guessLengthOption;// array of guess length storing
	private ArrayList<Character> encryptedText;// the text
	private double[][] persentageCollection;// data information that storing the
											// frequency

	public VigenereCracker()
	{
		super();
		occuranceTime = new ArrayList<Integer>();
		PresentStringArray = new String[10];
		guessLengthOption = new int[10];// limit the array to 10, only store 10
										// strings
	}

	// get and set method
	public String getPresentString()
	{
		return presentString;
	}

	public String[] getPresentStringArray() { return PresentStringArray; }

	public int[] getGuessLengthOption() { return guessLengthOption; }

	public void setGuessLength(int g) { guessLength = g; }

	public double[][] getPersentageCollection() { return persentageCollection; }

	@Override
	public void setKWCode()
	{// setKWCode method here is used as find the possible length of the
		// encrypted text
		encryptedText = super.getTextChange();
		super.removeSpace();
		occuranceTime.clear();// clear the ArrayList is for the reason in the
								// GUI, users will use this method for several
								// times, and for each time it is used, it needs
								// to be cleared in oder to be used again
		for (int shift = 1; shift < encryptedText.size(); shift++)
		{// the for loop will shift to find the coincidence in the text
			int count = 0;
			for (int track = 0; track < encryptedText.size() && track + shift < encryptedText.size(); track++)
				if (encryptedText.get(track) == encryptedText.get(track + shift))
					count++;
			occuranceTime.add(count);
		}
		for (int j = 0; j < 10; j++)
		{// this for loop will find the top 10 possible
			int index = 0, largest = occuranceTime.get(index);
			for (int i = 1; i < 50 && i < occuranceTime.size(); i++)
			{
				if (occuranceTime.get(i) > largest)
				{
					largest = occuranceTime.get(i);
					index = i;
				}
			}
			occuranceTime.set(index, 0);
			PresentStringArray[j] = "The number " + (j + 1) + " possible keyword length is " + (index + 1)
					+ "\tOccurance time is " + largest + "\n";
			guessLengthOption[j] = index + 1;
		}
	}

	@Override
	public void Cryptogam()
	{// analysis the text by the given guess number
		double totalChar = 0;
		persentageCollection = new double[guessLength][];
		for (int i = 0; i < guessLength; i++)
		{
			String modPos = "";
			for (int j = i; j < encryptedText.size(); j += guessLength)
			{// count total character and add strings to the modPos
				modPos += encryptedText.get(j);
				totalChar++;
			}
			double[] percentage = new double[26];
			for (int k = 0; k < 26; k++)
			{// calculate the frequency of the letter occurance
				char ch;
				int count = 0;
				ch = (char) (k + 65);
				for (int l = 0; l < modPos.length(); l++)
				{
					if (ch == modPos.charAt(l))
						count++;
				}
				percentage[k] = count / totalChar;// add to the array
			}
			// add the array to another array
			persentageCollection[i] = percentage;
		}
	}
}
