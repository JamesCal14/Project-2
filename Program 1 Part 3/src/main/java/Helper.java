import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Helper {

	Random rand = new Random();
	public ArrayList<Double> salt(ArrayList<Double> values, int saltVal)
	{
		ArrayList<Double> saltedOutput = new ArrayList<Double>();
		for(int i =0;i<values.size();i++)
		{
			int r = rand.nextInt(saltVal);
			double result = values.get(i) + r;
			saltedOutput.add(result);
		}
		return saltedOutput;
	}
	
	public ArrayList<Double> smooth(ArrayList<Double> saltedValues, int windowValue)
	{
		ArrayList<Double> smoothedOutput = new ArrayList<Double>();
		for (int i =0;i<saltedValues.size();i++)
		{
			DescriptiveStatistics mean = new DescriptiveStatistics();
			if (i-windowValue<=0)
			{
				for (int j = 0;j<=i+windowValue && j<saltedValues.size();j++)
				{
					mean.addValue(saltedValues.get(j));
				}
			}
			else
			{
				for (int j = i-windowValue;j<=i+windowValue && j<saltedValues.size();j++)
				{
					mean.addValue(saltedValues.get(j));
				}
			}
			smoothedOutput.add(mean.getMean());
		}
		return smoothedOutput;
	}
}
