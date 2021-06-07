package objects;

public class StrangeCharacterReplacer
{
	public static String removeStrangeCharacters(String input)
	{
		input = input.replace("ä", "ae");
		input = input.replace("ö", "oe");
		input = input.replace("ü", "ue");
		input = input.replace("Ä", "Ae");
		input = input.replace("Ü", "Ue");
		input = input.replace("Ö", "Oe");
		input = input.replace("ß", "ss");
		return input;
	}
}
