import java.util.ArrayList;

public class RSI {

	public double calculateRSI(ArrayList<Double> values, int input)
	{
		double U = 0;
		double D = 0;
		for (int i = values.size()-1; i>0; i--)
		{
			double change = values.get(i) - values.get(i-1);
			if (change > 0)
			{
				U += change;
			}
			else
			{
				D += Math.abs(change);
			}
		}
		double AvgU = U / input;
		double AvgD = D / input;
		double RS = AvgU / AvgD;
		double RSI = 100 - 100 / (1 + RS);
		return RSI;
	}
	
	public ArrayList<Double> calculateMA(ArrayList<Double> values, int windowValue)
	{
		ArrayList<Double> MA = new ArrayList<Double>();
		for (int i =0;i<values.size();i++)
		{
			double result = 0;
			double count = 0;
			if (i-windowValue<=0)
			{
				for (int j = 0;j<=i+windowValue && j<values.size();j++)
				{
					result = result + values.get(j);
					count++;
				}
			}
			else
			{
				for (int j = i-windowValue;j<=i+windowValue && j<values.size();j++)
				{
					result = result + values.get(j);
					count++;
				}
			}
			result = result / count;
			MA.add(result);
		}
		return MA;
	}
}
