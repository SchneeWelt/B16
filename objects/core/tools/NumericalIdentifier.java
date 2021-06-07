package objects.core.tools;

import java.util.ArrayList;

import objects.core.database.ABC;
import objects.core.database.ABC_LowerCase;
import objects.core.database.ABC_UpperCase;
import objects.core.database.Digits;
import objects.core.database.SpecialCharacters;

/**
 * Enthällt statische Methoden für die erkennung von groß oder kleinbuchstaben,
 * sowie sonderzeichen - leerzeichen inkludiert - und Zahlen.
 */

public class NumericalIdentifier
{
	/**
	 * Die Numerischenmethoden vergleichen den Character c mit allen anderen aus der
	 * entsprechenden Datenbank. Hier währe entsprechende Datenbank die
	 * ABC_LowerCase.java. Es wird so lange gesucht, bis ein gleicher character aus
	 * der Datenbank gefunden wird.
	 * 
	 * @param c
	 * @return true, wenn kleinbuchstabe
	 */

	public static final boolean isNumericalLowerCase(Character c)
	{
		ABC_LowerCase abc = new ABC_LowerCase();
		return compareByIteration(c, abc.getABC());
	}

	/**
	 * @param c
	 * @return True, wenn der Character c in der Datenbank ABC enthalten ist.
	 */

	public static final boolean isNumericalABCContent(Character c)
	{
		ABC abc = new ABC();
		return compareByIteration(c, abc.getABC());
	}

	/**
	 * @param c
	 * @return True, wenn großbuchstabe
	 */

	public static final boolean isNumericalUpperCase(Character c)
	{
		ABC_UpperCase abc = new ABC_UpperCase();
		return compareByIteration(c, abc.getABC());
	}

	public static final boolean isNumericalSpecialCharacter(Character c)
	{
		SpecialCharacters abc = new SpecialCharacters();
		return compareByIteration(c, abc.getSpecialCharacters());
	}

	/**
	 * Gibt zurück, ob der Character eine Zahl ist.
	 * 
	 * @param c
	 * @return
	 */

	public static final boolean isNumericalDigit(Character c)
	{
		Digits abc = new Digits();
		return compareByIteration(c, abc.getDigits());
	}

	/**
	 * 
	 * @param c
	 * @return true, wenn kleinbuchstabe. Anscheinend aber auch true, wenn special
	 *         character...
	 */

	public static final boolean isLowerCase(Character c)
	{
		return !Character.isUpperCase(c);
	}

	/**
	 * @param c
	 * @return True, wenn großbuchstabe
	 */

	public static final boolean isUpperCase(Character c)
	{
		return Character.isUpperCase(c);
	}

	/**
	 * Gibt zurück, ob der Character eine Zahl ist.
	 * 
	 * @param c
	 * @return
	 */

	public static final boolean isDigit(Character c)
	{
		return Character.isDigit(c);
	}

	/**
	 * @param letterToCompare
	 * @param abc
	 * @return
	 */

	private static final boolean compareByIteration(Character letterToCompare, ArrayList<Character> abc)
	{
		for (Character i : abc)
		{
			if (Character.compare(letterToCompare, i) == 0)
				return true;
		}

		return false;
	}
}
