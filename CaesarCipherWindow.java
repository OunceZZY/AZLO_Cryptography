
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
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CaesarCipherWindow extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainWindow myWindow;

	// panel 1
	private JPanel panelInput;
	private JButton Receive;
	private JButton browse;
	private JTextField fileLocation;
	private JButton confirm;
	private JLabel responFileLocation;
	private JLabel waitCon;
	private JLabel receiveError;
	private JTextArea inputString;
	// panel 2
	private JPanel panelKeyWord;
	private JTextField inputKeyWord;
	private JButton Encrypt;
	private JButton Decrypt;
	// panel3
	private JPanel panelOutput;
	private JTextArea outputString;
	private JButton Save;
	private JButton Send;
	private JLabel sendError;
	// panel4
	private JPanel panelReturn;
	private JButton returnToMainPage;

	public CaesarCipherWindow(MainWindow m)
	{
		myWindow = m;
		// assign MainWindow to track the control to the MainWindow

		setLayout(new GridLayout(4, 1));
		// set 4 gridlayout

		// panel1
		panelInput = new JPanel();
		Receive = new JButton("Wait for receive");
		Receive.addActionListener(this);
		browse = new JButton("Browse the computer");
		browse.addActionListener(this);
		fileLocation = new JTextField(20);
		fileLocation.setVisible(false);
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setVisible(false);
		responFileLocation = new JLabel("Cannot find the file");
		waitCon = new JLabel("Receving message complete");
		waitCon.setVisible(false);
		receiveError = new JLabel("Error occur when receiving");
		receiveError.setVisible(false);
		responFileLocation.setVisible(false);
		inputString = new JTextArea("Type in here to encrypt/decrypt", 5, 30);
		panelInput.add(waitCon);
		panelInput.add(receiveError);
		panelInput.add(Receive);
		panelInput.add(fileLocation);
		panelInput.add(confirm);
		panelInput.add(responFileLocation);
		panelInput.add(browse);

		panelInput.add(new JScrollPane(inputString));
		add(panelInput);

		// panel2
		panelKeyWord = new JPanel();
		inputKeyWord = new JTextField("Enter keyword here", 15);
		Encrypt = new JButton("Encrypt");
		Decrypt = new JButton("Decrypt");
		panelKeyWord.add(inputKeyWord);
		panelKeyWord.add(Encrypt);
		panelKeyWord.add(Decrypt);
		add(panelKeyWord);
		Encrypt.addActionListener(this);
		Decrypt.addActionListener(this);

		// panel3
		panelOutput = new JPanel();
		outputString = new JTextArea(5, 35);
		Save = new JButton("Save");
		Send = new JButton("Send");
		sendError = new JLabel("No receiver is found");
		sendError.setVisible(false);
		panelOutput.add(new JScrollPane(outputString));
		panelOutput.add(Save);
		panelOutput.add(Send);
		panelOutput.add(sendError);
		Save.addActionListener(this);
		Send.addActionListener(this);
		panelOutput.setVisible(false);
		add(panelOutput);

		// panel for returning to main page
		panelReturn = new JPanel();
		returnToMainPage = new JButton("Return to main page");
		returnToMainPage.addActionListener(this);
		panelReturn.add(returnToMainPage);
		add(panelReturn);

		setTitle("Caesar Cipher");
		setSize(500, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == returnToMainPage)
		{// return to main page
			this.setVisible(false);
			myWindow.setVisible(true);
		}
		if (e.getSource() == Encrypt)
		{// if Encrypt put the string and keyword to CaesarDecipher and put it
			// on output field
			String input = "", keyWord = "";
			input = inputString.getText() + "";
			keyWord = inputKeyWord.getText() + "";
			CaesarCipher cipher = new CaesarCipher(input, keyWord);
			outputString.setText(cipher.getCryption());
			panelOutput.setVisible(true);
		}
		if (e.getSource() == Decrypt)
		{// if Decrypt put the string and keyword to CaesarDecipher and put it
			// on output field
			String input = "", keyWord = "";
			input = inputString.getText() + "";
			keyWord = inputKeyWord.getText() + "";
			CaesarDecipher cipher = new CaesarDecipher(input, keyWord);
			outputString.setText(cipher.getCryption());
			panelOutput.setVisible(true);
		}
		if (e.getSource() == Save)
		{// if save then save to desktop
			String saveIt;
			saveIt = outputString.getText();
			try
			{
				String userHomeFolder = System.getProperty("user.home");
				File file = new File(userHomeFolder + "/Desktop", "Save_text.txt");
				if (!file.exists())
				{// if file does not exists, then create it
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(saveIt);
				bw.close();
			} catch (IOException exp)
			{
				exp.printStackTrace();
			}
		}
		if (e.getSource() == Receive)
		{
			receiveError.setVisible(false);
			waitCon.setVisible(true);
			try
			{
				String clientSentence;
				// creating a new socket to welcome clients
				ServerSocket welcomeSocket = new ServerSocket(6789);

				System.out.println("waiting for client to connect, and then passing off to new port #");
				Socket connectionSocket = welcomeSocket.accept();

				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));

				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				// reading from the client
				clientSentence = inFromClient.readLine();

				outToClient.close();
				inFromClient.close();
				connectionSocket.close();
				welcomeSocket.close();

				System.out.println(clientSentence);
				inputString.setText(clientSentence);
			} catch (Exception e9)
			{
				receiveError.setVisible(true);
			}
		}
		if (e.getSource() == Send)
		{
			sendError.setVisible(false);
			try
			{
				String saveIt;
				saveIt = outputString.getText();

				String sentence;

				InputStream stream = new ByteArrayInputStream(saveIt.getBytes(StandardCharsets.UTF_8));
				System.out.println(stream + " Here is the stream");
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(stream));
				Socket clientSocket = new Socket("127.0.0.1", 6789);// send to
																	// myself

				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				sentence = inFromUser.readLine();

				outToServer.writeBytes(sentence + '\n');

				clientSocket.close();
			} catch (Exception e8)
			{
				sendError.setVisible(true);
			}
		}
		if (e.getSource() == browse)
		{// if browse it will present JTextField to input, and button to confirm
			fileLocation.setVisible(true);
			confirm.setVisible(true);
		}
		if (e.getSource() == confirm)
		{// confirm will read the file that is inputed
			responFileLocation.setVisible(false);
			String location = fileLocation.getText();
			String fileReadString = "";
			try
			{
				FileReader file = new FileReader(location);
				BufferedReader br = new BufferedReader(file);
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null)
				{
					System.out.println(line);
					fileReadString += line;
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				file.close();
				inputString.setText(fileReadString);
			} catch (FileNotFoundException e1)
			{
				responFileLocation.setVisible(true);
			} catch (IOException e2)
			{
				responFileLocation.setVisible(true);
			}
		}
	}
}
