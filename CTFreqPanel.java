
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
 * CTFreqPanel is a class that create a chart panel that fill
 * using passed in data
 * 
 */
import java.awt.*;
import javax.swing.*;

public class CTFreqPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private char[] characters;
	private double[] persentageCollection;

	// constructor
	public CTFreqPanel()
	{
	}

	public CTFreqPanel(double[] pC)
	{
		title = "% in code";
		characters = new char[26];
		for (int i = 0; i < 26; i++)
			characters[i] = (char) (65 + i);
		persentageCollection = pC;

	}

	public CTFreqPanel(double[] pC, char[] chang)
	{
		title = "% in code";
		characters = chang;
		persentageCollection = pC;
	}

	// get set methods
	public char[] getChar()
	{
		return characters;
	}

	public double[] getData()
	{
		return persentageCollection;
	}

	@Override
	public void paintComponent(Graphics chart)
	{
		super.paintComponent(chart);
		if (persentageCollection == null || persentageCollection.length == 0)
			return;
		double minimumVal = 0, maximumVal = 0;
		for (int i = 0; i < persentageCollection.length; i++)
		{// find the minimum and maximum to scale
			if (minimumVal > persentageCollection[i])
				minimumVal = persentageCollection[i];
			if (maximumVal < persentageCollection[i])
				maximumVal = persentageCollection[i];
		}
		if (maximumVal == minimumVal)
			return;
		Dimension dem = getSize();// the component size in Frame
		int theWidth = dem.width, theHeight = dem.height;
		int singleBarW = theWidth / persentageCollection.length;

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

		for (int i = 0; i < persentageCollection.length; i++)
		{
			int lowLeft = i * singleBarW;
			int lowRight = leftB;
			int height = (int) (persentageCollection[i] * singleBarH);
			if (persentageCollection[i] >= 0)
				lowRight += (int) ((maximumVal - persentageCollection[i]) * singleBarH);
			else
			{
				lowRight += (int) (maximumVal * singleBarH);
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
