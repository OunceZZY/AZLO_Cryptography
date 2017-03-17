
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton Caesar;
	private JButton Vigenere;
	private JButton VigenereCracker;
	private JPanel panel;
	private JPanel center;
	private JLabel label;

	public MainWindow()
	{
		
		panel = new JPanel();
		center = new JPanel();
		Caesar = new JButton("\tEncrypt/Decrypt by Caesar Cipher\t");
		Caesar.addActionListener(this);
		Vigenere = new JButton("\tEncrypt/Decrypt by Vigenere Cipher\t");
		Vigenere.addActionListener(this);
		label = new JLabel("\nChoose the type of cipher\n");
		VigenereCracker = new JButton ("\tCrack text in Vigenere Cipher\t");
		VigenereCracker.addActionListener(this);
		panel.add(label);
		
		center.add(Caesar);
		center.add(Vigenere);
		center.add(VigenereCracker);
		setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);

		setTitle("AZLO_Cryptographer");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == Caesar)
		{
			CaesarCipherWindow cipher = new CaesarCipherWindow(this);
			setVisible(false);
			cipher.setVisible(true);
		}
		if (e.getSource() == Vigenere)
		{
			VigenereCipherWindow cipher = new VigenereCipherWindow (this);
			setVisible (false);
			cipher.setVisible(true);
		}
		if(e.getSource() == VigenereCracker)
		{
			VigenereCrackerWindow cipher = new VigenereCrackerWindow(this);
			setVisible(false);
			cipher.setVisible(true);
		}
	}
}
