package objects.command;

import java.util.ArrayList;

/**
 * Beschreibt abstrakt jeden Befehl, der im CommandTerminal getätigt werden
 * kann. Der String command enthällt den Befehl, der für eine sub class gillt.
 */

public abstract class Command
{
	protected String command = "";

	public Command(String command)
	{
		this.command = command;
	}

	/**
	 * Führt einen Befehlt aus. Mit methoden aufruf wird eine Liste aus Strings
	 * übergeben, die als tags dienen. Element 0 aus der liste ist der erste tag.
	 * Theoretisch sind unendlich viele tags möglich. Tags definieren einen Befehl
	 * genauser
	 * 
	 * @param params
	 */

	public void executeCommand(ArrayList<String> params)
	{
	}

	/**
	 * Wird von der Help klasse aufgerufen. Diese Methode sollte einen String
	 * zurückgeben, dessen Inhalt genau die Syntax eines Commands erklärt.
	 * 
	 * @return
	 */

	public String getSpecificCommandInfo()
	{
		return "";
	}

	public final String getCommand()
	{
		return command;
	}
}
