package utils;

/**
 * @author GuilhermeMatheus
 *
 */
public class Parameter {
	String key;
	double value;
	
	public Parameter(String key, double value)
	{
		this.key = key;
		this.value = value;
	}
	
	String getKey()
	{
		return key;
	}
	double getValue()
	{
		return value;
	}
}
