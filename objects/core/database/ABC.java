package objects.core.database;

import java.util.ArrayList;

/**
 * Diese Datenbank enth‰llt alle Sonderzeichen, Zahlen und groﬂ, sowie
 * kleinbuchstaben aus den zugrunde liegenden sub Datenbanken.
 */

public class ABC extends Database
{
	@Override
	protected void fillList()
	{
		Digits digits = new Digits();
		ABC_LowerCase lowerCase = new ABC_LowerCase();
		ABC_UpperCase upperCase = new ABC_UpperCase();
		SpecialCharacters specialCharacters = new SpecialCharacters();
		
		for (Character c : digits.getDigits())
		{
			list.add(c);
		}
		
		for (Character c : lowerCase.getABC())
		{
			list.add(c);
		}
		
		for (Character c : upperCase.getABC())
		{
			list.add(c);
		}
		
		for (Character c : specialCharacters.getSpecialCharacters())
		{
			list.add(c);
		}
	}
	
	public final ArrayList<Character> getABC()
	{
		return getList();
	}
}
