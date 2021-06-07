package objects.core.tools;

/**
 * Enhällt methoden um einen Character c mit einem anderen Character a zu
 * vergleichen.
 */

public class CharacterKomparator
{
	/**
	 * @param c
	 * @param a
	 * @return True, wenn c und a identisch
	 */ 
	
	public static final boolean matches(Character c, Character a)
	{
		return (Character.compare(c, a) == 0);
	}
}
