package errorHandling;

public class MovieNotFoundException extends Exception
{
	@Override
	public String getMessage() 
	{
		return "Movie was not found in MovieBase";
	}
}
