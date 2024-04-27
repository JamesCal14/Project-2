import java.math.BigInteger;

/*
 * Second part of stats library with the few non calculus functions left.
 * Included is Poisson, Tchebysheff's and Uniform distribution.
 */
public class Library2 {

	public double getPoissonDis(int y, double ave)
	{
		BigInteger denom = factorial(y);
		return (Math.pow(Math.E, -ave) * Math.pow(ave, y)) / denom.doubleValue();
	}
	
	public double getExpectedPD(double ave)
	{
		return ave;
	}
	
	public double getVariancePD(double ave)
	{
		return ave;
	}
	
	public double getStanDevPD(double ave)
	{
		return Math.sqrt(ave);
	}
	
	public double chevyshefys(double mean, double stanDev, double upper, double lower)
	{
		double within = 0;
		within = upper - mean;
		double k = within/stanDev;
		return (1-(1/Math.pow(k, 2)));
	}
	
	public double uniformDistribution(double a, double b)
	{
		return (1/(b-a));
	}
	
	public double getExpectedUD(double a, double b)
	{
		return (a+b)/2;
	}
	
	public double getVarianceUD(double a, double b)
	{
		return Math.pow((b-a), 2) / 12;
	}
	
	public double getStanDevUD(double a, double b)
	{
		return Math.sqrt(getVarianceUD(a,b));
	}
	
	//Helper
	public BigInteger factorial(int n)
	{
		int factor = 1;
		BigInteger bigFactor = BigInteger.valueOf(factor);
		for (int i = 1;i<=n;i++)
		{
			BigInteger bigI = BigInteger.valueOf(i);
			bigFactor = bigFactor.multiply(bigI);
		}
		return bigFactor;
	}
}
