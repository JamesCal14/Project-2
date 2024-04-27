import java.util.ArrayList;
import java.util.Random;

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
			double result = 0;
			double count = 0;
			if (i-windowValue<=0)
			{
				for (int j = 0;j<=i+windowValue && j<saltedValues.size();j++)
				{
					result = result + saltedValues.get(i);
					count++;
				}
			}
			else
			{
				for (int j = i-windowValue;j<=i+windowValue && j<saltedValues.size();j++)
				{
					result = result + saltedValues.get(i);
					count++;
				}
			}
			result = result / count;
			smoothedOutput.add(result);
		}
		return smoothedOutput;
	}
}

