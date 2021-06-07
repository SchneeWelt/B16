package objects.core.tools;

public class Reader
{
	/**
	 * Gibt den Buchstaben auf der Stelle position zurück.
	 * 
	 * @param position
	 * @param stringBuilder
	 * @return
	 */

	public final static Character readLetter(int position, StringBuilder stringBuilder)
	{
		return stringBuilder.substring(position, position + 1).toCharArray()[0];
	}
}
