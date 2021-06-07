package objects.core;

import java.util.ArrayList;

import objects.core.database.ABC;
import objects.core.tools.Reader;
import objects.core.tools.CharacterKomparator;
import objects.core.tools.NumericalIdentifier;

/**
 * Der Decryptor entschlüsselt, was der Encryptor zuvor verschlüsselt hat.
 */

public class Decryptor
{	
	private KeyBuilder keyBuilder;

	public Decryptor(int spin, String keyWord)
	{
		keyBuilder = new KeyBuilder(spin, keyWord);
	}

	/**
	 * Entschlüsselt eine Nachricht input
	 * 
	 * @param input
	 * @return
	 */

	public final String decrypt(String input)
	{
		String output = "";
		StringBuilder stringBuilder = new StringBuilder(input);

		/* gehe über jeden Charakter in input */
		for (int round = 0; round < input.length(); round++)
		{
			/* lese den Charakter letterOfInput aus dem input aus */
			Character tempLetter = Reader.readLetter(round, stringBuilder);

			/* finde raus, ob tempLetter in der ABC datenbank existiert */
			if (NumericalIdentifier.isNumericalABCContent(tempLetter))
			{
				/* erzeuge eine Refernz */
				ABC abc = new ABC();

				/* decrypt letter */
				tempLetter = decryptLetter(round, tempLetter, abc.getABC());
			} else
			{
				System.out.println("Error: Unable to find letter" + tempLetter);
			}

			output = output.concat("" + tempLetter);
		}

		return output;
	}

	/**
	 * Wird diese Methode aufgerufen, so wird ein neuer Key für die Verschlüsselung
	 * erzeugt, der Key besteht hier aus keyWord und spin. Diese methode sollte
	 * immer dann aufgerufen werden, wenn sich das keyWord, der Spin oder beides
	 * geändert hat. Dies ist nötig, um die Änderungen wirksam zu machen.
	 * 
	 * @param spin
	 * @param keyWord
	 */

	public final void updateKey(int spin, String keyWord)
	{
		keyBuilder.updateKey(spin, keyWord);
	}

	/**
	 * 
	 * Enschlüsselt den Charakter tempLetter basierend auf den abcReferenzwerten.
	 * 
	 * @param round
	 * @param tempLetter
	 * @param abc
	 * @return
	 */

	private final Character decryptLetter(int round, char tempLetter, ArrayList<Character> abc)
	{
		for (int innerIterator = 0; innerIterator < abc.size(); innerIterator++)
		{
			/* hole jeden Character aus dem abc datensatz */
			Character c = abc.get(innerIterator);

			/*
			 * schaue für jeden Character, ob tempLetter und dieser Character übereinstimmen
			 */

			if (CharacterKomparator.matches(c, tempLetter))
			{
				/*
				 * berechne die Anzahl an Stellen, um die dieser Character verschoben werden
				 * soll
				 */

				int keyShift = keyBuilder.getKey().get(keyBuilder.translate(round));
				keyShift %= abc.size();

				/*
				 * erzeugen den neuen Character basierend auf dem keyShift und dem momentanen
				 * Character
				 */

				int totalPosition = innerIterator - keyShift;

				if (totalPosition < 0) // Dieser Block code hat mich zwei Stunden gekostet
					totalPosition += abc.size();

				int newLetterPosition = totalPosition % abc.size();
				tempLetter = abc.get(newLetterPosition);

				/* breche den loop ab um das Ergebnis zu übergeben */
				break;
			}
		}

		return tempLetter;
	}
}
