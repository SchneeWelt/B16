package tools.txtWriter;

import java.util.ArrayList;

/**
 * Enthällt genau eine Methode. Diese Statische Methode teilt einen String in
 * mehrere Zeilen auf. Die Zeilen werden als ArrayListe zurückgegeben.
 */

public class ToLines
{
	/**
	 * Spaltet einen String message in mehrere Zeilen auf, die Zeilen werden als
	 * ArrayList zurückgeben.
	 * 
	 * @param message
	 * @param letters nach wie vielen buchstaben soll eine neue Zeile begonnen
	 *                werden.
	 * @return
	 */

	public final static ArrayList<String> toLines(int letters, String message)
	{
		/**
		 * Nach 50 Buchstaben setzte einen lineSeperator
		 */

		ArrayList<String> lines = new ArrayList<String>();

		/*
		 * Long wird nicht verwendet! System crash, wenn mehr als 65000 zeichen
		 * geschrieben werden!!!
		 */

		if (message.toCharArray().length < letters)
		{
			lines.add(message);
		} else
		{
			for (int i = 0; i < message.toCharArray().length; i++)
			{
				if (i % letters == 0 && i != 0)
				{
					int lastI = i - letters;
					lines.add(message.substring(lastI, i));
				}
			}
		}

		return lines;
	}

}
