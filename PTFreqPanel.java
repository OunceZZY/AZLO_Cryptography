
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
 * PTFreqPanel create chart panel by given data
 * 
 */
import java.awt.*;
import javax.swing.*;

public class PTFreqPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private char[] characters;
	private double[] pTFrequency;

	public PTFreqPanel()
	{
		title = "% in plaintext";
		characters = new char[26];
		for (int i = 0; i < 26; i++)
			characters[i] = (char) (65 + i);
		pTFrequency = new double[26];

		pTFrequency[0] = 0.08167;
		pTFrequency[1] = 0.01492;
		pTFrequency[2] = 0.02782;
		pTFrequency[3] = 0.04253;
		pTFrequency[4] = 0.12702;
		pTFrequency[5] = 0.02228;
		pTFrequency[6] = 0.02015;
		pTFrequency[7] = 0.06094;
		pTFrequency[8] = 0.06966;
		pTFrequency[9] = 0.00153;
		pTFrequency[10] = 0.00772;
		pTFrequency[11] = 0.04025;
		pTFrequency[12] = 0.02406;
		pTFrequency[13] = 0.06749;
		pTFrequency[14] = 0.07507;
		pTFrequency[15] = 0.01929;
		pTFrequency[16] = 0.00095;
		pTFrequency[17] = 0.05987;
		pTFrequency[18] = 0.06327;
		pTFrequency[19] = 0.09056;
		pTFrequency[20] = 0.02758;
		pTFrequency[21] = 0.00978;
		pTFrequency[22] = 0.02361;
		pTFrequency[23] = 0.00150;
		pTFrequency[24] = 0.01974;
		pTFrequency[25] = 0.00074;
	}

	public void paintComponent(Graphics chart)
	{
		super.paintComponent(chart);
		if (pTFrequency == null || pTFrequency.length == 0)
			return;
		double minimumVal = 0, maximumVal = 0;
		for (int i = 0; i < pTFrequency.length; i++)
		{// find the minimum and maximum to scale
			if (minimumVal > pTFrequency[i])
				minimumVal = pTFrequency[i];
			if (maximumVal < pTFrequency[i])
				maximumVal = pTFrequency[i];
		}
		if (maximumVal == minimumVal)
			return;
		Dimension dem = getSize();// the component size in Frame
		int theWidth = dem.width, theHeight = dem.height;
		int singleBarW = theWidth / pTFrequency.length;

		Font titleF = new Font("SansSerif", Font.BOLD, 20);
		Font labelF = new Font("SansSerif", Font.PLAIN, 10);
		FontMetrics titleFont = chart.getFontMetrics(titleF);
		FontMetrics labelFont = chart.getFontMetrics(labelF);

		int titleWidth = titleFont.stringWidth(title);
		int length = titleFont.getAscent();
		int width = (theWidth - titleWidth) / 2;
		chart.setFont(titleF); 
		chart.drawString(title, width, length);
		// the title is drawn
		int leftB = titleFont.getHeight(), rightB = labelFont.getHeight();
		double singleBarH = (theHeight - leftB - rightB) / (maximumVal - minimumVal);
		length = theHeight - labelFont.getDescent();
		chart.setFont(labelF);

		for (int i = 0; i < pTFrequency.length; i++)
		{
			int lowLeft = i * singleBarW;
			int lowRight = leftB;
			int height = (int) (pTFrequency[i] * singleBarH);
			if (pTFrequency[i] >= 0)
				lowRight += (int)((maximumVal - pTFrequency[i]) * singleBarH);
			else
			{
				lowRight +=  (int)(maximumVal * singleBarH);
				height = 0;
			}

			chart.setColor(Color.RED);
			chart.fillRect(lowLeft, lowRight, singleBarW - 2, height);
			chart.setColor(Color.BLACK);
			chart.drawRect(lowLeft, lowRight, singleBarW - 2, height);
			// make sure the label is at middle of the bottom
			int labelWidth = labelFont.stringWidth("" + characters[i]);
			width = i * singleBarW + (singleBarW - labelWidth) / 2;
			chart.drawString("" + characters[i], width, length);//bottom label part
		}
	}
}
