package objects.error;

public class WrongArgumentError extends Error
{
	public WrongArgumentError()
	{
		super("[Error] wrong arguments!");
	}
}
