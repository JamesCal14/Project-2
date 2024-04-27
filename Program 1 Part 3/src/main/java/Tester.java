
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;  

public class Tester {
  public static void main(String[] args) {
	ArrayList<Double> x = new ArrayList<Double>();
	ArrayList<Double> y = new ArrayList<Double>();
	ArrayList<Double> salt = new ArrayList<Double>();
	Helper help = new Helper();
    Scanner in = new Scanner(System.in);
    System.out.println("Enter max:");
	double max = in.nextDouble();
	System.out.println("Enter min:");
	double min = in.nextDouble();
	System.out.println("Enter increment:");
	double inc = in.nextDouble();
	System.out.println("Enter salting range:");
	int saltVal = in.nextInt();
	System.out.println("Enter smoothing range:");
	int smoothVal = in.nextInt();
	
    for (double i =min; i<=max;i+=inc)
	{
		x.add(i);
		y.add(((i)*2)+5);
		
	}
    
    try {
		FileWriter output = new FileWriter("output.csv");
		for(int i = 0; i<x.size();i++)
		{
				output.write(x.get(i) +", ");
		}
		output.write("\n");
		for(int i = 0; i<x.size();i++)
		{
				output.write(y.get(i) +", ");
		}
		output.close();
		}
		catch (IOException e)
		{
			e.getMessage();
		}
    
   
    try {
		//read file input and create string builder
		StringBuilder sb = new StringBuilder();
		FileInputStream input = new FileInputStream("output.csv");
		int i = input.read();
		sb.append((char)i);
		while (i != -1)
		{
			i = input.read();
			sb.append((char)i);
		}
		System.out.println(sb);
		String split[] = sb.toString().split("\n");
		String close[] = split[1].split(", ");
		for (int j = 0; j < close.length-1; j++)
		{
			salt.add(Double.parseDouble(close[j]));
		}
		input.close();
		}
		catch (IOException e)
		{
			e.getMessage();
		}
    
    ArrayList<Double> saltedValues = new ArrayList<Double>();
    ArrayList<Double> smoothedValues = new ArrayList<Double>();
    saltedValues = help.salt(salt, saltVal);
    smoothedValues = help.smooth(saltedValues, smoothVal);
    System.out.println("Salted Y Values: "+saltedValues);
    System.out.println("Smoothed Y Values: "+smoothedValues);
    
    //JFreeChart
    //https://www.javatpoint.com/jfreechart-line-chart
    DefaultCategoryDataset data = new DefaultCategoryDataset();
    String series1 = "Salted";
    String series2 = "Smoothed";
    
    data.addValue(saltedValues.get(0), series1, "-10");
    data.addValue(saltedValues.get(5), series1, "-5");
    data.addValue(saltedValues.get(10), series1, "0");
    data.addValue(saltedValues.get(15), series1, "5");
    data.addValue(saltedValues.get(20), series1, "10");
    
    data.addValue(smoothedValues.get(0), series2, "-10");
    data.addValue(smoothedValues.get(5), series2, "-5");
    data.addValue(smoothedValues.get(10), series2, "0");
    data.addValue(smoothedValues.get(15), series2, "5");
    data.addValue(smoothedValues.get(20), series2, "10");
    // create a chart...
    JFreeChart chart = ChartFactory.createLineChart("Salted VS Smoothed Data", "X", "Y", data);
    // create and display a frame...
    ChartFrame frame = new ChartFrame("Test", chart);
    frame.pack();
    frame.setVisible(true);
  	}
    
  }