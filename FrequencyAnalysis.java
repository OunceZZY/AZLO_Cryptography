
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
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrequencyAnalysis extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PTFreqPanel wF;
	private int guessNumber;
	private double[][] data;
	private JButton[] thChar;
	private JPanel panel2;
	private JButton left, right;
	private JPanel panel4;
	private CTFreqPanel ctf;
	private int count = 0;

	public FrequencyAnalysis(int n, double[][] d)
	{
		guessNumber = n;
		data = d;
		ctf = new CTFreqPanel();
		thChar = new JButton[guessNumber];

		setLayout(new GridLayout(4, 1));
		// add plain text frequency char panel to the frame
		wF = new PTFreqPanel();
		add(wF);

		// two panels according to passed in data create buttons
		panel2 = new JPanel();
		for (int i = 0; i < guessNumber; i++)
		{
			JButton add = new JButton("L" + (i + 1));
			add.addActionListener(this);
			thChar[i] = add;
			panel2.add(add);
		}
		add(panel2);

		panel4 = new JPanel();
		left = new JButton("<");
		right = new JButton(">");
		panel4.add(left);
		panel4.add(right);
		left.addActionListener(this);
		right.addActionListener(this);

		panel4.setVisible(false);
		add(panel4);
		add(ctf);
		setTitle("Frequency Analysis");
		setSize(600, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < guessNumber; i++)
		{
			if (e.getSource() == thChar[i])
			{// creating a graph after clean the panel by the past in data
				count = i;
				thChar[i].setText("A");
				super.remove(ctf);

				ctf = new CTFreqPanel(data[i]);
				add(ctf);

				panel4.setVisible(true);
				super.revalidate();
			}
		}
		if (e.getSource() == left || e.getSource() == right)
		{// if left and right shift is chosen
			// remove everything on the panel
			super.remove(ctf);
			// rearrange the data
			char[] characters = new char[26];// = ctf.getChar();
			double[] change = new double[26];// ctf.getData();
			for (int i = 0; i < 26; i++)
			{
				characters[i] = ctf.getChar()[i];
				change[i] = ctf.getData()[i];
			}
			char tempChar;
			double tempDouble;
			if (e.getSource() == left)
			{// create a new graph with the rearranged data
				thChar[count].setText("" + (char) ((int) (thChar[count].getText().charAt(0) + 1)));
				if ((int) (thChar[count].getText().charAt(0)) > 90)
					thChar[count].setText("" + (char) ((int) (thChar[count].getText().charAt(0) - 26)));
				tempChar = characters[0];
				tempDouble = change[0];
				for (int i = 0; i < 25; i++)
				{
					change[i] = change[i + 1];
					characters[i] = characters[i + 1];
				}
				change[25] = tempDouble;
				characters[25] = tempChar;
			} else if (e.getSource() == right)
			{
				thChar[count].setText("" + (char) ((int) (thChar[count].getText().charAt(0) - 1)));
				if ((int) (thChar[count].getText().charAt(0)) < 65)
					thChar[count].setText("" + (char) ((int) (thChar[count].getText().charAt(0) + 26)));
				tempChar = characters[25];
				tempDouble = change[25];
				for (int i = 25; i > 0; i--)
				{
					change[i] = change[i - 1];
					characters[i] = characters[i - 1];
				}
				change[0] = tempDouble;
				characters[0] = tempChar;
			}
			ctf = new CTFreqPanel(change, characters);
			add(ctf);
			panel4.setVisible(true);
			super.revalidate();
		}
	}
}
