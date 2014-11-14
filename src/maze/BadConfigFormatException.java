package maze;

public class BadConfigFormatException extends Exception
{
	private String message;
	
	public BadConfigFormatException()
	{
	}
	
	public BadConfigFormatException(String error)
	{
		super(error);
		message = error;
	}
	
	public String toString()
	{
		return message;
	}
}