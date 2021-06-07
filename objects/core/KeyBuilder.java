package objects.core;

import java.util.ArrayList;

import objects.core.database.ABC;
import objects.core.tools.NumericalIdentifier;
import objects.core.tools.Searcher;

/**
 * Der KeyBuilder fertig aus einem Wort (String) einen Schlüssel an. Der
 * Schlüssel ist eine eindimensionale liste des Datentyps Integer.
 * 
 * Damit ein Schlüssel erzeugt werden kann, muss die Mindestlänge des
 * SchlüsselWortes 1 betragen. Also mindestens 1 Character im KeyWord enthalten
 * sein.
 * 
 * Der Key darf groß und kleinbuchstaben, sowie sonderzeichen, leerzeichen und
 * zahlen enthalten.
 * 
 * Ist der spin positiv, so wird das keyWord von links nach rechts ausgelesen
 * Ist der spin negativ, so wird das keyWord von rechts nach links augelesen
 * 
 * Der Buchstabe 'A' bedeutet verschiebe um 0 -> keyValue auf dieser Position =
 * 0 Der Buchstabe 'B' bedeutet verschiebe um 1 -> keyValue auf dieser Position
 * = 1 und so weiter.
 */

public class KeyBuilder
{
	private int spin = 0;
	
	private ArrayList<Integer> key = new ArrayList<Integer>();

	public KeyBuilder(int spin, String keyWord)
	{
		updateKey(spin, keyWord);
	}

	/**
	 * Aktualisiert den schlüssel, indem basierend auf dem keyWord und dem Spin ein
	 * neuer Schlüssel angefertig wird.
	 * 
	 * @param spin
	 * @param keyWord
	 */

	public final void updateKey(int spin, String keyWord)
	{
		key.clear();
		this.spin = spin;
		createKey(keyWord.toCharArray());
	}

	/**
	 * Erzeugt den key. Weißt dem key objekt also eine ArrayList mit Werten zu.
	 */

	private final void createKey(char[] letters)
	{
		if (isPositiveSpin())
		{
			/* gehe durch jeden character in letters[] */
			for (int j = 0; j < letters.length; j++)
			{
				buildLetter(j, letters);
			}
		} else if (isNegativeSpin())
		{
			/* gehe durch jeden character in letters[] */
			for (int j = letters.length - 1; j >= 0; j--)
			{
				buildLetter(j, letters);
			}
		} else if (isZeroSpin())
		{
			System.err.println("Spin has to be unequal to 0");
		}
	}

	private final void buildLetter(int j, char[] letters)
	{
		Character tempLetter = letters[j];

		if (NumericalIdentifier.isNumericalABCContent(tempLetter))
		{
			assign(tempLetter, new ABC().getABC());
		} else
		{
			System.err.println("Error: Unable to find Character: " + tempLetter + "in ABC DataBase");
		}
	}

	/**
	 * Setzt den Key zusammen.
	 * 
	 * @param target
	 * @param abc
	 */

	private final void assign(Character target, ArrayList<Character> abc)
	{
		int result = Searcher.searchCharacterIndex(0, target, abc);

		if (result >= 0)
			key.add(result);
		else
		{
			System.out.println("Character not found");
		}
	}

	/**
	 * Gibt einen Wert n auf stelle index im Key zurück. Ist der Index kleiner als
	 * 0, wird ein Index von 0 zurückgegeben. Ist der Index größer als die Liste,
	 * die den Key beeinhaltet, so wird der Index im Uhrzeigersinn angepasst.
	 * 
	 * Ist die Liste, des keys beispielsweise 2 Blöcke groß - also Index 0 und Index
	 * 1 - verfügbar, der Index jedoch von Größe 2, dann wird der Index auf 0
	 * gesetzt. Es gillt Index = Index % key.size()
	 * 
	 * Die Aufgabe dieser Methode ist es egal für welchen input index wert einen
	 * existenten Wert in der key Liste zurückzugeben, so dass dieser Wert dann
	 * benutzt werden kann, um einen weiteren int zum Verschlüsseln zu erhalten.
	 * 
	 * @param index
	 * @return
	 */

	public final int translate(int index)
	{
		if (index < 0)
			index = 0;

		index %= key.size();

		return index;
	}

	private final boolean isPositiveSpin()
	{
		return spin > 0;
	}

	private final boolean isNegativeSpin()
	{
		return spin < 0;
	}

	private final boolean isZeroSpin()
	{
		return spin == 0;
	}

	public final ArrayList<Integer> getKey()
	{
		return key;
	}
}
