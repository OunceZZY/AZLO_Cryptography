
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
 * CaesarDecipher is the class, subclass of CryptographGeneral, decrypt the text 
 * in Caesar cipher
 * 
 */
import java.util.ArrayList;

public class CaesarDecipher extends CryptographGeneral
{
	private int shift; // number of shift
	private ArrayList<Character> textChange; // the text

	// constructor
	public CaesarDecipher(String iT, String kW)
	{
		super(iT, kW);
	}

	@Override
	public void setKWCode()
	{// method get a String and get the first valid character as the shift
		// if no valid shift, default 0
		int index = 0;
		String keyWord = super.getkWCode();
		if (!keyWord.equals("")) // first get the first character as the shift
			shift = (int) (keyWord.charAt(index));
		else// if the length is empty then shift is 0
			shift = 0;
		while (index < keyWord.length())
		{// check the shift, if the shift is 0 then pass

			if (shift >= 65 && shift <= 90)
			{// if the input is upper case, then in ascii table, minus 65
				shift -= 65;
				break;
			} else if (shift >= 97 && shift <= 122)
			{// if the input is lower case, then in ascii table, minus 97
				shift -= 97;
				break;
			} else
			{// else check the next character, see if the next character adapts
				// the requirement
				if (index != keyWord.length() - 1)
				{
					index++;
					shift = (int) (keyWord.charAt(index));
				} else
				{// if no, shift is 0
					shift = 0;
					break;
				}
			}
		}
	}

	public void Cryptogam()
	{// shift the texts
		setKWCode();
		super.removeSpace();
		textChange = super.getTextChange();
		int temp, tempPlus;
		for (int i = 0; i < textChange.size(); i++)
		{// for every characters shift
			temp = (int) (textChange.get(i));
			tempPlus = temp - shift;
			// if shift to a non-letter character, process it into letter
			if ((temp >= 65 && temp <= 90 && tempPlus < 65) || (temp >= 97 && temp <= 122 && tempPlus < 97))
				textChange.set(i, (char) (tempPlus + 26));
			else
				textChange.set(i, (char) (tempPlus));
		}
	}
}
