package objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Lie�t input aus der Console aus, l�uft auf dem Main thread. Der neuste Inhalt
 * aus der Console kann mit readInput() aufgerufen werden.
 * 
 * Diese Klasse wartet solange, bis wieder etwas in die Konsole geschrieben wird
 * und anschlie�end die readInput() methode aufgerufen wird.
 */

public class ConsoleReader
{
	private String last = "";
	private BufferedReader reader;

	public ConsoleReader()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public final String readInput()
	{
		try
		{
			last = reader.readLine();
			return last;
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return "an error accoured!";
	}

	/**
	 * Gibt den String zur�ck, der zuletzt von der readInput methode aus der Console
	 * ausgelesen wurde.
	 */

	public final String getLast()
	{
		return last;
	}

	public final void close()
	{
		try
		{
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
