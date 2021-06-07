package objects.core.keyWordGenerator;

import objects.core.database.ABC;
import tools.random.Randomizer;

/**
 * Erzeugt mit instanziierung einer Instanz dieser Klasse ein einmalieges
 * keyWord. Das keyWord kann über getter und setter gezielt verändert oder
 * erhalten werden. Über die Methode makeNew() ist es außerdem möglich ein neues
 * zufällieges KeyWord zu erzeugen.
 */

public class KeyWordGenerator
{
	private String keyWord = "";
	private ABC abc = new ABC();
	private Randomizer random = new Randomizer();

	public KeyWordGenerator(Behavior behavior)
	{
		makeNew(behavior);
	}

	/**
	 * Erzeugt ein neues zufällieges KeyWord
	 * 
	 * @param behavior
	 */

	public final void makeNew(Behavior behavior)
	{
		if (behavior.equals(Behavior.USE_TYPE_16))
		{
			keyWord = generateKeyWord(16, "");
		} else if (behavior.equals(Behavior.USE_TYPE_32))
		{
			keyWord = generateKeyWord(32, "");
		} else if (behavior.equals(Behavior.USE_TYPE_64))
		{
			keyWord = generateKeyWord(64, "");
		} else if (behavior.equals(Behavior.USE_TYPE_256))
		{
			keyWord = generateKeyWord(256, "");
		} else if (behavior.equals(Behavior.USE_TYPE_512))
		{
			keyWord = generateKeyWord(512, "");
		} else if (behavior.equals(Behavior.USE_TYPE_1024))
		{
			keyWord = generateKeyWord(1024, "");
		}
	}

	/**
	 * rekursieve erarbeitung des KeyWords.
	 * 
	 * @param generationLenght
	 * @param keyWord
	 * @return
	 */

	private final String generateKeyWord(int generationLenght, String keyWord)
	{
		/* abbruch sektion */
		if (generationLenght <= 0)
			return keyWord;

		/* tempKeyWord erweitern */
		int position = random.random(abc.getABC().size() - 1);
		Character c = abc.getABC().get(position);

		keyWord = keyWord.concat(c + "");

		return generateKeyWord(generationLenght - 1, keyWord);
	}

	/**
	 * Gibt das KeyWord zurück, welches in diesem Objekt erzeugt wurde.
	 */

	public final String getKeyWord()
	{
		return keyWord;
	}

	public final void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}
}
