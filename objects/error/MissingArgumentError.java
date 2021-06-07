package objects.error;

public class MissingArgumentError extends Error
{
	public MissingArgumentError()
	{
		super("[Error] missing Argument!");
		
		System.out.println("Seems like you have a missing argument error");
		System.out.println("Therfore, I had to close this session");
		System.out.println("Why did this happen?");
		System.out.println("Well most times a missing argument error means");
		System.out.println("that you forgot to set the closing bracket of your input line");
		System.out.println("Make sure to set the last bracket next time and all should be fine :)");
		System.out.println("Yust ignore this big mumbo jumbo starting from here this is only for developers or bug solvers"
				+ "\n	Have a nice day");
	}
}
