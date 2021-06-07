package objects.core.tools;

import java.util.ArrayList;

/**
 * Enth�llt Rekursieve teils Statische Methoden um Werte, wie chars aus einer
 * Liste zu suchen
 */

public class Searcher
{
	/**
	 * Diese Methode sucht rekursiv nach einer �bereinstimmung des TargetWertes mit
	 * einem Wert aus der Liste. Wird eine �bereinstimmung gefunden, wird der
	 * iterator, als index aus der Liste zur�ckgegeben. Wird der Ausgabe Wert in die
	 * Datenbank eingesetzt, m�sste der target Wert herauskommen. Findet die Methode
	 * keinen passenden Wert, dann wird -1 zur�ckgegeben.
	 * 
	 * Beispiel: Searcher.searchCharacterIndex(0, 'C', new ABC_UpperCase().getABC()).
	 * Dieser Aufruf wird 2 wiedergeben. Da 'A' = 0, 'B' = 1 und 'C' = 2
	 * 
	 * @param iterator Der Iterator der Methode. Wert wird mit jedem aufruf um eins
	 *                 erh�ht.
	 * @param database Die Datenbank. Alle Werte aus dieser Liste werden mit dem
	 *                 TargetWert verglichen.
	 * @param target   Der Wert nach dem Gesucht wird.
	 */

	public final static int searchCharacterIndex(int iterator, Character target, ArrayList<Character> database)
	{
		Character tempLetter = database.get(iterator);

		/* tempLetter und target sind gleich */
		if (CharacterKomparator.matches(tempLetter, target))
		{
			return iterator; // Ziel Wert gefunden
		} else if (iterator > database.size())
		{
			return -1; // Wert nicht gefunden
		}

		return searchCharacterIndex(iterator + 1, target, database); // erneut suchen.
	}
}
