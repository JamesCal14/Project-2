import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
  public static void main(String[] args) {
	RSI rsi = new RSI();
	ArrayList<String> weeklyValues = new ArrayList<String>();
	ArrayList<Double> closeValues = new ArrayList<Double>();
	Scanner in = new Scanner(System.in);
	System.out.println("Enter RSI period: ");
	int period = in.nextInt();
	System.out.println("Enter MA smoothing value: ");
	int ma = in.nextInt();
    try {
		//read file input and create string builder
		StringBuilder sb = new StringBuilder();
		FileInputStream input = new FileInputStream("GME.csv");
		int i = input.read();
		sb.append((char)i);
		while (i != -1)
		{
			i = input.read();
			sb.append((char)i);
		}
		String splitLine[] = sb.toString().split("\n");
		for (int j = 0; j<splitLine.length;j++)
		{
			weeklyValues.add(splitLine[j]);
		}
		for (int j = 1; j<splitLine.length;j++)
		{
			String splitCommas[] = splitLine[j].split(",");
			closeValues.add(Double.parseDouble(splitCommas[4]));
		}
		input.close();
		}
		catch (IOException e)
		{
			e.getMessage();
		}
    System.out.println("RSI: "+rsi.calculateRSI(closeValues, period));
    System.out.println("MA: "+rsi.calculateMA(closeValues, ma));
  	} 
  }
