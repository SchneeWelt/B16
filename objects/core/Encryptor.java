package objects.core;

import java.util.ArrayList;

import objects.core.database.ABC;
import objects.core.tools.Reader;
import objects.core.tools.CharacterKomparator;
import objects.core.tools.NumericalIdentifier;

/**
 * Diese Klasse existiert zum Verschl�ssen von Daten
 * 
 * Der key ist die Abfolge an Buchstaben und Zahlen, nach der der eingebene Text
 * versch�sselt wird.
 * 
 * Der hop bestimmt, in welcher abfolge ein Buchstabe aus dem key �bersprungen
 * wird. Ein hop von 1 hie�e, dass, wenn f�r den key gillt: "Hallo", der
 * buchstabe "a" jedesmal �bersprungen wird. (Nicht implementiert)
 * 
 * Der Spin bezeichnet, ob der key von links (spin = -1), von rechts (spin = 1)
 * oder von der mitte aus anfangend (spin = 0) ausgelesen wird. (Nicht
 * implementier)
 */

public class Encryptor
{
	private KeyBuilder keyBuilder;

	public Encryptor(int spin, String keyWord)
	{
		keyBuilder = new KeyBuilder(spin, keyWord);
	}

	/**
	 * Verschl�sselt eine Nachricht und gibt diese zur�ck.
	 * 
	 * @param Input Text, der verschl�sselt werden soll
	 * @return Verschl�sselte Nachricht
	 */

	public String encrypt(String input)
	{
		String output = "";
		StringBuilder stringBuilder = new StringBuilder(input);

		/* gehe �ber jeden Charakter in input */
		for (int round = 0; round < input.length(); round++)
		{
			Character tempLetter = Reader.readLetter(round, stringBuilder);

			/* finde raus, ob tempLetter in der ABC datenbank existiert */
			if (NumericalIdentifier.isNumericalABCContent(tempLetter))
			{
				/* erzeuge eine Refernz */
				ABC abc = new ABC();

				/* entschl�ssel den Character */
				tempLetter = encryptLetter(round, tempLetter, abc.getABC());
			} else
			{
				System.out.println("Error: Unable to find Character: " + tempLetter + "in ABC Database");
			}

			output = output.concat("" + tempLetter);
		}

		return output;
	}

	/**
	 * Wird diese Methode aufgerufen, so wird ein neuer Key f�r die Verschl�sselung
	 * erzeugt, der Key besteht hier aus keyWord und spin.
	 * 
	 * @param spin
	 * @param keyWord
	 */

	public final void updateKey(int spin, String keyWord)
	{
		keyBuilder.updateKey(spin, keyWord);
	}

	/**
	 * verschl�sselt den character tempLetter auf basis des abc wertes. Der
	 * verschl�sselte character wird anschlie�end zur�ckgegeben.
	 * 
	 * @param outerIterator
	 * @param tempLetter
	 * @param abc
	 * @return
	 */

	private final Character encryptLetter(int outerIterator, char tempLetter, ArrayList<Character> abc)
	{
		for (int innerIterator = 0; innerIterator < abc.size(); innerIterator++)
		{
			/* hole jeden Character aus dem abc datensatz */
			Character c = abc.get(innerIterator);

			/*
			 * schaue f�r jeden Character, ob tempLetter und dieser Character �bereinstimmen
			 */

			if (CharacterKomparator.matches(c, tempLetter))
			{
				/*
				 * berechne die Anzahl an Stellen, um die dieser Character verschoben werden
				 * soll
				 */

				int keyShift = keyBuilder.getKey().get(keyBuilder.translate(outerIterator));

				/*
				 * erzeugen den neuen Character basierend auf dem keyShift und dem momentanen
				 * Character
				 */

				tempLetter = abc.get((keyShift + innerIterator) % abc.size());

				/* breche den loop ab um das Ergebnis zu �bergeben */
				break;
			}
		}

		return tempLetter;
	}
}
