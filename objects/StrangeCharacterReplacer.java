package objects;

public class StrangeCharacterReplacer
{
	public static String removeStrangeCharacters(String input)
	{
		input = input.replace("�", "ae");
		input = input.replace("�", "oe");
		input = input.replace("�", "ue");
		input = input.replace("�", "Ae");
		input = input.replace("�", "Ue");
		input = input.replace("�", "Oe");
		input = input.replace("�", "ss");
		return input;
	}
}
