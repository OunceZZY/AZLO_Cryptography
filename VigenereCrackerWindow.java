
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
 * VigenereCrackerWindow will creates window that input and output data
 * through VigenereCraker
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VigenereCrackerWindow extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow myWindow;
	private JPanel panelInput;
	private JButton browse;
	private JTextField fileLocation;
	private JButton confirm;
	private JLabel responFileLocation;
	private JTextArea inputString;
	private VigenereCracker crack;

	// panel2
	private JButton Crack;
	// panel3
	private JPanel panelOutput;
	private JButton[] options;

	private JButton returnToMainPage;

	public VigenereCrackerWindow(MainWindow m)
	{
		myWindow = m;
		crack = new VigenereCracker();

		setLayout(new BorderLayout());

		// panel North
		panelInput = new JPanel();
		browse = new JButton("Browse the computer");
		browse.addActionListener(this);
		fileLocation = new JTextField(20);
		fileLocation.setVisible(false);
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setVisible(false);
		responFileLocation = new JLabel("Cannot find the file");
		responFileLocation.setVisible(false);
		inputString = new JTextArea("Input the encrypted text here", 5, 35);
		panelInput.add(fileLocation);
		panelInput.add(confirm);
		panelInput.add(responFileLocation);
		panelInput.add(browse);
		panelInput.add(new JScrollPane(inputString));
		Crack = new JButton("Crack It");
		Crack.addActionListener(this);
		panelInput.add(Crack);
		panelInput.setPreferredSize((new Dimension(500, 200)));
		add(panelInput, BorderLayout.NORTH);
		panelInput.setSize(WIDTH, HEIGHT * 2);

		// panel Center
		options = new JButton[10];
		panelOutput = new JPanel();
		panelOutput.setVisible(false);
		add(panelOutput, BorderLayout.CENTER);

		// panel South
		returnToMainPage = new JButton("Return to main page");
		returnToMainPage.addActionListener(this);
		add(returnToMainPage, BorderLayout.SOUTH);

		setTitle("Vigenere Cipher Cracker");
		setSize(500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == returnToMainPage)
		{// return to mainpage
			this.setVisible(false);
			myWindow.setVisible(true);
		}
		if (e.getSource() == Crack)
		{// crack then clear everything originally on the panel add new labels
			// on it
			panelOutput.removeAll();
			panelOutput.revalidate();
			panelOutput.repaint();

			String str = inputString.getText();
			crack.setInputText(str);
			crack.setKWCode();
			String[] getOptions = crack.getPresentStringArray();
			String Opts = "";
			int[] selection = crack.getGuessLengthOption();
			int OptsInt = 0;
			// create labels
			for (int i = 0; i < 10 && i < getOptions.length; i++)
			{
				Opts = getOptions[i];
				JLabel add = new JLabel(Opts);
				panelOutput.add(add);
			}
			panelOutput.add(new JLabel("\tSelect the posisble length of the keyword\t"));
			// create button
			for (int i = 0; i < selection.length; i++)
			{
				OptsInt = selection[i];
				JButton add = new JButton("" + OptsInt);
				add.addActionListener(this);
				options[i] = add;
				panelOutput.add(add);
			}
			panelOutput.setVisible(true);
		}

		for (int i = 0; i < options.length; i++)
		{
			if (e.getSource() == options[i])
			{// if button is pressed then analysis the text and pass in
				// correspondent date to the object for analyzing
				int gL = Integer.parseInt(options[i].getText());
				crack.setGuessLength(gL);
				crack.Cryptogam();
				double[][] data = crack.getPersentageCollection();
				FrequencyAnalysis fa = new FrequencyAnalysis(gL, data);
				fa.setVisible(true);
			}
		}

		if (e.getSource() == browse)
		{ // chosing file from computer
			fileLocation.setVisible(true);
			confirm.setVisible(true);
		}
		if (e.getSource() == confirm)
		{// read and write the file to text field
			responFileLocation.setVisible(false);
			String location = fileLocation.getText();
			String fileReadString = "";
			try
			{
				File file = new File(location);
				if (!file.exists())
					responFileLocation.setVisible(true);
				else
				{
					Scanner inputFile;
					inputFile = new Scanner(file);
					while (inputFile.hasNext())
						fileReadString += inputFile.next();
					inputFile.close();
					inputString.setText(fileReadString);
				}
			} catch (FileNotFoundException e1)
			{
				responFileLocation.setVisible(true);
			}
		}
	}
}
